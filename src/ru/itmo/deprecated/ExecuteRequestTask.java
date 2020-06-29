//package ru.itmo.deprecated;
//
//import ru.itmo.common.classes.MusicBand;
//import ru.itmo.common.exchange.request.Request;
//import ru.itmo.common.exchange.request.RequestType;
//import ru.itmo.common.exchange.response.Response;
//import ru.itmo.main.UserManager;
//import ru.itmo.main.CommandManager;
//
//import java.sql.Connection;
//import java.util.concurrent.Callable;
//import java.util.concurrent.ConcurrentSkipListMap;
//
//
//
//public class ExecuteRequestTask implements Callable<Response> {
//
//
//    private ConcurrentSkipListMap<Integer, MusicBand> collection;
//    private Connection connection;
//    private Request request;
//
//
//
//    public ExecuteRequestTask() {}
//
//    public ExecuteRequestTask(ConcurrentSkipListMap<Integer, MusicBand> collection,
//                              Connection connection,
//                              Request request) {
//
//        this.collection = collection;
//        this.connection = connection;
//        this.request = request;
//    }
//
//
//
//    @Override
//    public Response call() {
//
//        if (request.getRequestType() == RequestType.COMMAND_REQUEST) {
//            return new Response()
//                    .setCommandResponse(CommandManager.executeCommand(collection, connection, request.getUser(), request.getCommandRequest()))
//                    .setUser(request.getUser());
//
//        } else if (request.getRequestType() == RequestType.SERVICE_REQUEST) {
//            return new Response()
//            .setServiceResponse(UserManager.executeServiceRequest(connection, request.getUser(), request.getServiceRequest()))
//                    .setUser(request.getUser());
//
//        } else throw new IllegalArgumentException("Request_Type is neither 'COMMAND_REQUEST' nor 'SERVICE_REQUEST'.");
//    }
//
//
//
//
//    public ConcurrentSkipListMap<Integer, MusicBand> getCollection() {
//        return collection;
//    }
//
//    public void setCollection(ConcurrentSkipListMap<Integer, MusicBand> collection) {
//        this.collection = collection;
//    }
//
//    public Connection getConnection() {
//        return connection;
//    }
//
//    public void setConnection(Connection connection) {
//        this.connection = connection;
//    }
//
//    public Request getRequest() {
//        return request;
//    }
//
//    public void setRequest(Request request) {
//        this.request = request;
//    }
//}
