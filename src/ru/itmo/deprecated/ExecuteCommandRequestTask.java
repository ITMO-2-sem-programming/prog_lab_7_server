//package ru.itmo.deprecated;
//
//import ru.itmo.common.classes.MusicBand;
//import ru.itmo.common.User;
//import ru.itmo.common.exchange.request.CommandRequest;
//import ru.itmo.common.exchange.response.CommandResponse;
//import ru.itmo.main.CommandManager;
//
//import java.sql.Connection;
//import java.util.concurrent.Callable;
//import java.util.concurrent.ConcurrentSkipListMap;
//
//
//
//public class ExecuteCommandRequestTask implements Callable<CommandResponse> {
//
//
//    private ConcurrentSkipListMap<Integer, MusicBand> collection;
//    private Connection connection;
//    private User user;
//    private CommandRequest commandRequest;
//
//
//
//    public ExecuteCommandRequestTask(ConcurrentSkipListMap<Integer, MusicBand> collection, Connection connection, User user, CommandRequest commandRequest) {
//        this.collection = collection;
//        this.connection = connection;
//        this.user = user;
//        this.commandRequest = commandRequest;
//    }
//
//
//
//
//    @Override
//    public CommandResponse call() {
//        return CommandManager.executeCommand(collection, connection, user, commandRequest);
//    }
//
//
//
//
//    public ConcurrentSkipListMap<Integer, MusicBand> getCollection() {
//        return collection;
//    }
//
//    public ExecuteCommandRequestTask setCollection(ConcurrentSkipListMap<Integer, MusicBand> collection) {
//        this.collection = collection;
//        return this;
//    }
//
//    public Connection getConnection() {
//        return connection;
//    }
//
//    public ExecuteCommandRequestTask setConnection(Connection connection) {
//        this.connection = connection;
//        return this;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public CommandRequest getCommandRequest() {
//        return commandRequest;
//    }
//
//    public ExecuteCommandRequestTask setCommandRequest(CommandRequest commandRequest) {
//        this.commandRequest = commandRequest;
//        return this;
//    }
//}
