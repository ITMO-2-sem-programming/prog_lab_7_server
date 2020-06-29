//package ru.itmo.deprecated;
//
//
//import ru.itmo.connection.ConnectionManager;
//import ru.itmo.connection.Serializer;
//import ru.itmo.common.exchange.response.Response;
//
//import java.io.IOException;
//import java.net.InetAddress;
//
//
//
//public class SendResponseTask implements Runnable {
//
//
//    private ConnectionManager connectionManager;
//    private Response response;
//    private InetAddress clientInetAddress;
//    private int clientPort;
//
//
//
//    public SendResponseTask(ConnectionManager connectionManager,
//                            Response response,
//                            InetAddress clientInetAddress,
//                            int clientPort) {
//
//        this.connectionManager = connectionManager;
//        this.response = response;
//        this.clientInetAddress = clientInetAddress;
//        this.clientPort = clientPort;
//    }
//
//
//
//
//    @Override
//    public void run() {
//
//        try {
//            connectionManager.send(clientInetAddress, clientPort, Serializer.toByteArray(response));
//
//        } catch (IOException e) {
//            throw new IllegalArgumentException(e.getMessage());
//        }
//    }
//
//
//
//
//    public ConnectionManager getConnectionManager() {
//        return connectionManager;
//    }
//
//    public SendResponseTask setConnectionManager(ConnectionManager connectionManager) {
//        this.connectionManager = connectionManager;
//        return this;
//    }
//
//    public Response getResponse() {
//        return response;
//    }
//
//    public SendResponseTask setResponse(Response response) {
//        this.response = response;
//        return this;
//    }
//
//    public InetAddress getClientInetAddress() {
//        return clientInetAddress;
//    }
//
//    public SendResponseTask setClientInetAddress(InetAddress clientInetAddress) {
//        this.clientInetAddress = clientInetAddress;
//        return this;
//    }
//
//    public int getClientPort() {
//        return clientPort;
//    }
//
//    public SendResponseTask setClientPort(int clientPort) {
//        this.clientPort = clientPort;
//        return this;
//    }
//}
