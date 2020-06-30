package ru.itmo.main;

import ru.itmo.common.classes.MusicBand;
import ru.itmo.common.exchange.ExchangeType;
import ru.itmo.common.exchange.request.Request;
import ru.itmo.common.exchange.response.Response;
import ru.itmo.connection.ConnectionManager;
import ru.itmo.connection.PortForwarder;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.*;



public class MainMultithreading {


    static String dataBaseUrl; // Адрес БД с проброшенным портом
    static final String USER = "s284704";
    static final String PASS = "hxy284";

    static ConcurrentSkipListMap<Integer, MusicBand> collection; // Коллекция
    
    static boolean isRunning;
    static final int sendResponsePoolCapacity = 8;

    static LinkedBlockingQueue<Request> requestsQueue;
    static LinkedBlockingQueue<Response> responsesQueue;

    static ConcurrentLinkedQueue<Connection> connectionsList; // Хранит подключения к БД

    static ConnectionManager connectionManagerReceiver; // Принимает Request на некоторый порт
    static ConnectionManager connectionManagerSender; // Посылает Response с другого порт

    
    public static void main(String[] args)  {

        connectionManagerReceiver = new ConnectionManager(44231);
        connectionManagerSender = new ConnectionManager(44444);

        connectionsList = new ConcurrentLinkedQueue<>();
        requestsQueue = new LinkedBlockingQueue<>();
        responsesQueue = new LinkedBlockingQueue<>();

        isRunning = true;


        try {
            
            dataBaseUrl = PortForwarder.forwardAnyPort(); // Пробрасывает порт
            collection = DataBaseManager.loadCollectionFromDataBase(getConnection()); // Загружает коллекцию из БД
            System.out.println("Collection form db was loaded successfully!");
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }


        runServer();
    }


    public static void runServer() {

        System.out.println("Server is running.");

        ForkJoinPool makeResponsePool = new ForkJoinPool();
        ExecutorService sendResponsePool = Executors.newFixedThreadPool(sendResponsePoolCapacity);


        Runnable sendResponseTask = () -> {

            Response responseToSend = responsesQueue.poll();

            if (responseToSend == null) return;

            try {
                connectionManagerSender.sendResponse(responseToSend);

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        };


        Runnable makeResponseTask = () -> {

            Response responseToMake;
            Connection currentConnection = getConnection();

            Request requestToProcess = requestsQueue.poll();

            if (requestToProcess == null) {
                return;
            }

            if (requestToProcess.getRequestType() == ExchangeType.COMMAND_EXCHANGE) {
                responseToMake = new Response(
                        CommandManager.executeCommand(collection, currentConnection, requestToProcess.getCommandRequest())
                );

            } else if (requestToProcess.getRequestType() == ExchangeType.SERVICE_EXCHANGE) {
                responseToMake = new Response(
                        UserManager.executeServiceRequest(currentConnection, requestToProcess.getServiceRequest())
                );

            } else {
                throw new IllegalArgumentException("Request type is neither 'SERVICE_EXCHANGE' nor 'COMMAND_EXCHANGE'.");
            }

            responseToMake.setClientInetAddress(requestToProcess.getClientInetAddress());
            responseToMake.setClientPort(requestToProcess.getClientPort());
            System.out.println(responseToMake);
            responsesQueue.add(responseToMake);

            returnConnection(currentConnection);

            sendResponsePool.submit(sendResponseTask);

        };


        Runnable receiveRequestTask = () -> {

            Request newRequest;

            while (isRunning) {
                try {

                    newRequest = connectionManagerReceiver.receiveRequest();
//                        System.out.println("\n\\\\\\\\\\\\\\\\\\\\Request\\\\\\\\\\\\\\\\\\\\");
//                        System.out.println(newRequest);
                    requestsQueue.add(newRequest);

                    makeResponsePool.submit(makeResponseTask);

                } catch (IOException | ClassNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }
        };


        new Thread(receiveRequestTask).start();

    }


    public synchronized static Connection getConnection() {

        if (connectionsList.isEmpty()) {
            try {
                connectionsList.add(DataBaseManager.connectDataBase(dataBaseUrl, USER, PASS));
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return connectionsList.poll();
    }


    public synchronized static void returnConnection(Connection connection) {
        connectionsList.add(connection);
    }



}



