//package ru.itmo.main;
//
//
//import ru.itmo.common.classes.*;
//import ru.itmo.connection.*;
//
//import ru.itmo.common.exchange.request.Request;
//
//import ru.itmo.common.exchange.response.Response;
//
//import ru.itmo.personalExceptions.DBException;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.SQLException;
//
//import java.util.concurrent.*;
//
//public class MainMultithreading {
//
//    static int numberOfRequestsToSaveTheCollection;
//    static String filePath;
//    static Connection connection;
//    static ConcurrentLinkedQueue<Connection> connectionsList;
//    static final int INITIAL_CAPACITY_OF_CONNECTIONS_LIST = 1;
//    public static ConcurrentLinkedQueue<Request> requestsList;
//    public static ConcurrentLinkedQueue<Response> responsesList;
//    static final int NUMBER_OF_THREADS = 8;
//    static final String dataBaseUrl = PortForwarder.forwardPort();
//    static final String USER = "s284704";
//    static final String PASS = "hxy284";
//
//
//
//
//    public static void main(String[] args) throws IOException, SQLException {
//
//
//
////        String filePath = System.getenv("COLLECTION_PATH");
////        if (filePath == null) {
////            System.out.println("Error: Env var 'COLLECTION_PATH' doesn't exist.");
////            return;
////        }
////        filePath = "collection.json";
//
////
////
////        FileManager fileManager;
////        TreeMap<Integer, MusicBand> collectionFromFile = new TreeMap<>();
//
//
//
//
////            fileManager = new FileManager();
////            collection = fileManager.getCollectionFromFile(filePath);
////            CommandManager.setFileManager(fileManager); //Is needed only for printing collection's DateOfInitialisation when running command "info
////            CommandManager.setFilePath(filePath);
//
//
//        requestsList = new ConcurrentLinkedQueue<>();
//        responsesList = new ConcurrentLinkedQueue<>();
//
//        ConcurrentSkipListMap<Integer, MusicBand> collection;
//        try {
//            connectionsList = new ConcurrentLinkedQueue<>();
//            for (int i = 0; i < INITIAL_CAPACITY_OF_CONNECTIONS_LIST; i ++) {
//                connectionsList.add(DataBaseManager.connectDataBase(dataBaseUrl, USER, PASS));
//            }
//            connection = getConnection(); /// replace every 'connection' with getConnection() method.
//            collection = DataBaseManager.loadCollectionFromDataBase(connection);
//            System.out.println("Collection was loaded successfully!");
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return;
//        }
//
//
//
//
//
//        //--------------------------------------------
//
//
////        MusicBand musicBand = new MusicBand(1, "Beatles", new Coordinates(161, 16), 7, 3, MusicGenre.PROGRESSIVE_ROCK, new Person("John Lennon", 195L, Color.RED, Country.GERMANY, new Location(1, 2, "Scene")));
////        MusicBand musicBand2 = new MusicBand(1, "HritlesP", new Coordinates(161, 16), 7, 3, MusicGenre.PROGRESSIVE_ROCK, null);
////        User user = new User("maxi", "30981ab525a917193e15ed1e632f409ad5dd237c3cd9f4df65cd2a9e");
////
////        InsertCommand.execute(collectionFromFile, connection, user, musicBand2);
////        DataBaseManager.addMusicBandToCollectionTable(connection, musicBand2);
////            System.out.println(DataBaseManager.getMusicBandByIDFromCollectionTable(connection, 9));
//
////        System.out.println(DataBaseManager.getMusicBandByID(connection, 99));
//
//        ConnectionManager connectionManager = new ConnectionManager(9100);
////        while (true) {
////            runCommand(collection, connectionManager, connection);
////        }
//        runCommand(collection, connectionManager, connection);
//
//    }
//
//
//    public static void runCommand(ConcurrentSkipListMap<Integer, MusicBand> collection, ConnectionManager connectionManager, Connection connection) {
//
//        Request request;
//        Response response = new Response();
//
//        ForkJoinPool executeRequestPool = new ForkJoinPool();
//        ExecutorService sendResponsePool = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
//
//
////        new Thread(new GetRequestTask(connectionManager));
////        Future<Response> responseFuture = executeCommandPool.submit(new ExecuteRequestTask(collection, connection, requestsList.peek()));
////        if (responseFuture.isDone()) {
////            sendResponsePool.submit(new SendResponseTask(connectionManager, ))
////        }
//
//        Runnable sendResponseTask = new Runnable() {
//            @Override
//            public void run() {
//                Response currentResponse = responsesList.poll();
//
//                if (currentResponse == null) return;
//
//                try {
//                    connectionManager.sendResponse(currentResponse);
//                } catch (IOException e) {
//                    System.out.println(e.getMessage());
//                }
//            }
//        };
//
//
//        Runnable executeRequestTask = new Runnable() {
//            @Override
//            public void run() {
//                Request currentRequest = requestsList.poll();
//
//                if (currentRequest == null) return;
//                Connection currentConnection = getConnection();
//                if (currentRequest.getRequestType() == RequestType.COMMAND_REQUEST) {
//                    responsesList.add(CommandManager.executeCommand(collection, currentConnection, currentRequest.getUser(), currentRequest.getCommandRequest()));
//
//                } else if (currentRequest.getRequestType() == RequestType.SERVICE_REQUEST) {
//                    responsesList.add(UserManager.executeServiceRequest(currentConnection, currentRequest.getUser(), currentRequest.getServiceRequest()));
//                }
//                returnConnection(currentConnection);
//                sendResponsePool.submit(sendResponseTask);
//            }
//        };
//
//
//        Runnable gerRequestTask = new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    requestsList.add(connectionManager.receiveRequest());
//                    executeRequestPool.submit(executeRequestTask);
//                } catch (IOException | ClassNotFoundException e) {
//                    System.out.println(e.getMessage());
//                }
//            }
//        };
//
//        for (int i = 0; i < 8; i++) {
//            new Thread(gerRequestTask).start();
//        }
//
//
//
//
//
//
//
//
////        try {
////            request = (Request) Serializer.toObject(connectionManager.receive());
////            System.out.println("I ve got rqObject.");
////
////            if (request.getRequestType() == RequestType.COMMAND_REQUEST) {
////
////                connectionManager.send(Serializer.toByteArray(
////                        new Response().setCommandResponse(
////                                CommandManager.executeCommand(collection, connection, request.getUser(), request.getCommandRequest()))
////                        )
////                );
////
////
////            } else if (request.getRequestType() == RequestType.SERVICE_REQUEST) {
////                response = new Response();
////
////                try {
////                    response = new Response().setServiceResponse(
////                            UserManager.executeServiceRequest(connection, request.getUser(), request.getServiceRequest()
////                            )
////                    );
////
////                } catch (DBException e) {
////                    response.getServiceResponse().setErrorMessage(e.getMessage());
////                    System.out.println(e.getMessage());
////                }
////
////                connectionManager.send(Serializer.toByteArray(response));
////            }
////
////
////        } catch (IOException | ClassNotFoundException e) { // Exception e  --make later
////            e.printStackTrace();
////            response.getCommandResponse().setMessage("Error: Command wasn't executed. Reason: 'ServerError'.");
////            try {
////                connectionManager.send(Serializer.toByteArray(response));
////            }catch (IOException ioe) {
////                System.out.println(ioe.getMessage());
////            }
////
////        }
//    }
////
//
//
//
//    public synchronized static Connection getConnection() {
//        if (connectionsList.isEmpty()) {
//            try {
//                connectionsList.add(DataBaseManager.connectDataBase(dataBaseUrl, USER, PASS));
//            } catch (SQLException e) {
//                throw new DBException(e.getMessage());
//            }
//        }
//
//        return connectionsList.poll();
//    }
//
//
//    public synchronized static void returnConnection(Connection connection) {
//        connectionsList.add(connection);
//    }
//
//}
//
//
