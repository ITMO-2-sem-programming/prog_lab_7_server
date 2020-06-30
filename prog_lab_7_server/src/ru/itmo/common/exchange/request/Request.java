package ru.itmo.common.exchange.request;

import ru.itmo.common.exchange.ExchangeType;

import java.io.Serializable;
import java.net.InetAddress;


public class Request implements Serializable {


//    private User user;
    private InetAddress clientInetAddress;
    private int clientPort;

    private ExchangeType requestType;
    private CommandRequest commandRequest;
    private ServiceRequest serviceRequest;



    public Request() {}


    public Request(CommandRequest commandRequest) {
        this.setCommandRequest(commandRequest);
    }


    public Request(ServiceRequest serviceRequest) {
        this.setServiceRequest(serviceRequest);
    }




//    public User getUser() {
//        if (commandRequest != null) return commandRequest.getUser();
//        if (serviceRequest != null) return serviceRequest.getUser();
//        return null;
//    }

    public ExchangeType getRequestType() {
        return requestType;
    }

    public CommandRequest getCommandRequest() {
        return commandRequest;
    }

    public Request setCommandRequest(CommandRequest commandRequest) {
        this.commandRequest = commandRequest;
        this.serviceRequest = null;
        this.requestType = ExchangeType.COMMAND_EXCHANGE;

        return this;
    }

    public ServiceRequest getServiceRequest() {
        return serviceRequest;
    }

    public Request setServiceRequest(ServiceRequest serviceRequest) {
        this.serviceRequest = serviceRequest;
        this.commandRequest = null;
        this.requestType = ExchangeType.SERVICE_EXCHANGE;

        return this;
    }

    public InetAddress getClientInetAddress() {

        return clientInetAddress;
    }

    public Request setClientInetAddress(InetAddress clientInetAddress) {
        this.clientInetAddress = clientInetAddress;

        return this;
    }

    public int getClientPort() {

        return clientPort;
    }

    public Request setClientPort(int clientPort) {
        this.clientPort = clientPort;

        return this;
    }

    @Override
    public String toString() {
        return String.format(
                "Request {" +
                "\n    clientInetAddress = '%s'" +
                "\n    clientPort = ...... '%s'" +
                "\n    requestType = ..... '%s'" +
                "\n    serviceRequest = .. '%s" +
                "\n    commandRequest = .. '%s" +
                "\n}" +
                "\n",
                clientInetAddress, clientPort, requestType, serviceRequest, commandRequest);
    }
}
