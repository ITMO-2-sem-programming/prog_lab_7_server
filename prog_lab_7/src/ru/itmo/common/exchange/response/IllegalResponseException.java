package ru.itmo.common.exchange.response;



public class IllegalResponseException extends RuntimeException {


    public IllegalResponseException() {}

    public IllegalResponseException(String message) {
        super(message);
    }
}
