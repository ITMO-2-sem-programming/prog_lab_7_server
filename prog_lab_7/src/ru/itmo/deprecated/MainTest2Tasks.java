//package ru.itmo.main;
//
//import ru.itmo.common.classes.MusicBand;
//import ru.itmo.common.exchange.ExchangeType;
//import ru.itmo.common.exchange.request.Request;
//import ru.itmo.common.exchange.response.Response;
//import ru.itmo.connection.ConnectionManager;
//import ru.itmo.connection.PortForwarder;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.concurrent.*;
//
//public class MainTest2Tasks {
//
//
//        static Connection connection;
//        static String dataBaseUrl;
//        static final String USER = "s284704";
//        static final String PASS = "hxy284";
//
//        static boolean isRunning;
//        static final int sendResponsePoolCapacity = 8;
//
//        static LinkedBlockingQueue<Request> requestsList;
//        static LinkedBlockingQueue<Response> responsesList;
//
//        static ConcurrentLinkedQueue<Connection> connectionsList;
//
//        static ConnectionManager connectionManager; // 'volatile' не помогло
//
//
//        public static void main(String[] args) throws IOException, SQLException {
//
//
//            connectionsList = new ConcurrentLinkedQueue<>();
//            requestsList = new LinkedBlockingQueue<>();
//            responsesList = new LinkedBlockingQueue<>();
//
//            isRunning = true;
//            ConcurrentSkipListMap<Integer, MusicBand> collection;
//
//
//
//            try {
//
////            for (int i = 0; i < INITIAL_CAPACITY_OF_CONNECTIONS_LIST; i ++) {
////                connectionsList.add(DataBaseManager.connectDataBase(USER, PASS));
////            }
//                dataBaseUrl = PortForwarder.forwardAnyPort();
//                collection = DataBaseManager.loadCollectionFromDataBase(getConnection());
//                System.out.println("Collection form db was loaded successfully!");
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//                return;
//            }
//
//
//
//            runCommand(collection, connectionManager, connection);
//
//        }
//
//
//        public static void runCommand(ConcurrentSkipListMap<Integer, MusicBand> collection, ConnectionManager connectionManager, Connection connection) {
//
//
//            ForkJoinPool makeResponsePool = new ForkJoinPool();
//            ExecutorService sendResponsePool = Executors.newFixedThreadPool(sendResponsePoolCapacity);
//
//
//            Runnable sendResponseTask = new Runnable() {
//                @Override
//                public void run() {
//                    Response responseToSend = responsesList.poll();
//                    if (responseToSend == null) return;
//                    try {
//                        connectionManager.sendResponse(responseToSend);
//                    } catch (IOException e) {
//                        System.out.println(e.getMessage());
//                    }
//                }
//            };
//
//
//            Runnable makeResponseTask = new Runnable() {
//                @Override
//                public void run() {
//                    ConnectionManager connectionManager = new ConnectionManager(44231);
//
//
//                    Request newRequest;
//
//                    while (isRunning) {
//
//                        try {
//
//                            newRequest = connectionManager.receiveRequest();
////                        System.out.println("\n\\\\\\\\\\\\\\\\\\\\Request\\\\\\\\\\\\\\\\\\\\");
////                        System.out.println(newRequest);
//                            requestsList.add(newRequest);
//
//
//                        } catch (IOException | ClassNotFoundException e) {
//                            System.out.println(e.getMessage());
//                        }
//
//
//                        Response responseToMake;
//                        Connection currentConnection = getConnection();
//
//                        Request requestToProcess = requestsList.poll();
//
//                        if (requestToProcess == null) {
//                            return;
//                        }
//
//                        if (requestToProcess.getRequestType() == ExchangeType.COMMAND_EXCHANGE) {
//                            responseToMake = new Response(
//                                    CommandManager.executeCommand(collection, currentConnection, requestToProcess.getCommandRequest())
//                            );
//
//
//                        } else if (requestToProcess.getRequestType() == ExchangeType.SERVICE_EXCHANGE) {
//                            responseToMake = new Response(
//                                    UserManager.executeServiceRequest(currentConnection, requestToProcess.getServiceRequest())
//                            );
//
//                        } else {
//                            throw new IllegalArgumentException("Request type is neither 'SERVICE_EXCHANGE' nor 'COMMAND_EXCHANGE'.");
//                        }
//
//                        Response fakeResponse = new Response();
//                        fakeResponse.setClientInetAddress(requestToProcess.getClientInetAddress());
//                        fakeResponse.setClientPort(requestToProcess.getClientPort());
//
//
//                        responseToMake.setClientInetAddress(requestToProcess.getClientInetAddress());
//                        responseToMake.setClientPort(requestToProcess.getClientPort());
////                System.out.println("\n******************Response*************");
////                System.out.println(responseToMake);
//                        responsesList.add(responseToMake);
////                responsesList.add(fakeResponse);
//                        returnConnection(currentConnection);
//
//                        sendResponsePool.submit(sendResponseTask);
//                    }
//                }
//            };
//
//
////            Runnable receiveRequestTask = new Runnable() {
////                @Override
////                public void run() {
////                    Request newRequest;
////
////                    while (isRunning) {
////
////                        try {
////
////                            newRequest = connectionManager.receiveRequest();
//////                        System.out.println("\n\\\\\\\\\\\\\\\\\\\\Request\\\\\\\\\\\\\\\\\\\\");
//////                        System.out.println(newRequest);
////                            requestsList.add(newRequest);
////                            makeResponsePool.submit(makeResponseTask);
////
////                        } catch (IOException | ClassNotFoundException e) {
////                            System.out.println(e.getMessage());
////                        }
////                    }
////                }
////            };
//
//            new Thread(makeResponseTask).start();
//
//
//        }
//
//        public synchronized static Connection getConnection() {
//            if (connectionsList.isEmpty()) {
//                try {
//                    connectionsList.add(DataBaseManager.connectDataBase(dataBaseUrl, USER, PASS));
//                } catch (SQLException e) {
//                    System.out.println(e.getMessage());
//                }
//            }
//
//            return connectionsList.poll();
//        }
//
//
//        public synchronized static void returnConnection(Connection connection) {
//            connectionsList.add(connection);
//        }
//
//
//
//    }
//
//
//
