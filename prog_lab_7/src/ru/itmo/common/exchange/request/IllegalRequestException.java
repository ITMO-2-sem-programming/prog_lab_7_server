package ru.itmo.common.exchange.request;



public class IllegalRequestException extends RuntimeException {


    public IllegalRequestException() {}

    public IllegalRequestException(String message) {
        super(message);
    }
}
