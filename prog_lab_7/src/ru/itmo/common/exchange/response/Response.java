package ru.itmo.common.exchange.response;
import ru.itmo.common.User;
import ru.itmo.common.exchange.ExchangeType;

import java.io.Serializable;
import java.net.InetAddress;


public class Response implements Serializable {


    private InetAddress clientInetAddress;
    private int clientPort;
    private ExchangeType responseType;
    private CommandResponse commandResponse;
    private ServiceResponse serviceResponse;



    public Response() {}


    public Response(CommandResponse commandResponse) {
        setCommandResponse(commandResponse);
    }


    public Response(ServiceResponse serviceResponse) {
        setServiceResponse(serviceResponse);
    }


    public Response(InetAddress clientInetAddress, int clientPort, CommandResponse commandResponse) {
        setClientInetAddress(clientInetAddress);
        setClientPort(clientPort);
        setCommandResponse(commandResponse);
    }


    public Response(InetAddress clientInetAddress, int clientPort, ServiceResponse serviceResponse) {
        setClientInetAddress(clientInetAddress);
        setClientPort(clientPort);
        setServiceResponse(serviceResponse);
    }




//    public User getUser() {
//        if (commandResponse != null) return commandResponse.getUser();
//        if (serviceResponse != null) return serviceResponse.getUser();
//        return null;
//    }

    public InetAddress getClientInetAddress() {

        return clientInetAddress;
    }

    public Response setClientInetAddress(InetAddress clientInetAddress) {
        this.clientInetAddress = clientInetAddress;

        return this;
    }

    public int getClientPort() {

        return clientPort;
    }

    public Response setClientPort(int clientPort) {
        this.clientPort = clientPort;

        return this;
    }

    public ExchangeType getResponseType() {
        return responseType;
    }

    public CommandResponse getCommandResponse() {
        return commandResponse;
    }

    public Response setCommandResponse(CommandResponse commandResponse) {
        this.commandResponse = commandResponse;
        this.serviceResponse = null;
        this.responseType = ExchangeType.COMMAND_EXCHANGE;

        return this;
    }

    public ServiceResponse getServiceResponse() {
        return serviceResponse;
    }

    public Response setServiceResponse(ServiceResponse serviceResponse) {
        this.serviceResponse = serviceResponse;
        this.commandResponse = null;
        this.responseType = ExchangeType.SERVICE_EXCHANGE;
        return this;
    }


    @Override
    public String toString() {
        return String.format(
                "Response {" +
                "\n    clientInetAddress = '%s'" +
                "\n    clientPort = ...... '%s'" +
                "\n    responseType = .... '%s'" +
                "\n    serviceResponse = . '%s" +
                "\n    commandResponse = . '%s" +
                "\n}" +
                "\n",
                clientInetAddress, clientPort, responseType, serviceResponse, commandResponse);
    }
}
