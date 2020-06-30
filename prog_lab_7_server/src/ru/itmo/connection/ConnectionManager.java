package ru.itmo.connection;

import ru.itmo.common.exchange.request.Request;
import ru.itmo.common.exchange.response.Response;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

// Server
public class ConnectionManager {

    private final DatagramSocket serverSocket;
    private DatagramPacket packet;

//    private InetAddress clientInetAddress;
//    private int clientPort;

    private int bufferSize;


    public ConnectionManager(int serverPort) {

        bufferSize = 65536;
        try {
            serverSocket = new DatagramSocket(serverPort);

        } catch (SocketException e) {
            throw new IllegalArgumentException("Error: Can't connect to specified port or get LocalHost address.");
        }

    }


//    public void send(byte[] bytes) throws IOException {
//        packet = new DatagramPacket(bytes, bytes.length, clientInetAddress, clientPort);
//        serverSocket.send(packet);
//    }


    public void sendResponse(Response response) throws IOException {
        send(response.getClientInetAddress(), response.getClientPort(), Serializer.toByteArray(response));
        System.out.println(
                "The response was sent." +
                "\n    Address : " + response.getClientInetAddress() +
                "\n    Port : .. " + response.getClientPort());

    }


    public void send(InetAddress clientInetAddress, int clientPort, byte[] bytes) throws IOException {
        packet = new DatagramPacket(bytes, bytes.length, clientInetAddress, clientPort);
        serverSocket.send(packet);
    }


    public void sendResponseAndCloseSocket(Response response) throws IOException {
        send(response.getClientInetAddress(), response.getClientPort(), Serializer.toByteArray(response));
        System.out.println(
                "The response was sent." +
                        "\n    Address : " + response.getClientInetAddress() +
                        "\n    Port : " + response.getClientPort());
        serverSocket.close();

    }


//    public byte[] receive() throws IOException {
//
//        byte[] buffer = new byte[bufferSize];
//
//        packet = new DatagramPacket(buffer, buffer.length);
//        serverSocket.receive(packet);
//
//        clientInetAddress = packet.getAddress();
//        clientPort = packet.getPort();
//
//        return buffer;
//    }


    public Request receiveRequest() throws IOException, ClassNotFoundException {

        byte[] buffer = new byte[bufferSize];

        packet = new DatagramPacket(buffer, buffer.length);
        serverSocket.receive(packet);


        System.out.println(
                "Received Request:" +
                "\n    Request address : " + packet.getAddress() +
                "\n    Request port : .. " + packet.getPort());

        Request request = (Request) Serializer.toObject(buffer);

        request.setClientInetAddress(packet.getAddress());
        request.setClientPort(packet.getPort());

//        System.out.println("Rq address : " + request.getClientInetAddress());
//        System.out.println("Rq port : " + request.getClientPort());

        return request;
    }


    public int getBufferSize() {
        return bufferSize;
    }


    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }
}
