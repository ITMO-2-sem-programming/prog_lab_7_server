package ru.itmo.common.exchange.response;


import ru.itmo.common.User;

import java.io.Serializable;


public class ServiceResponse implements Serializable {


    private User user;
    private ServiceResponseStatus status;
    private String message;



    public ServiceResponse() {}


    public ServiceResponse(User user, ServiceResponseStatus status) {
        setUser(user);
        setStatus(status);
    }


    public ServiceResponse(User user, ServiceResponseStatus status, String message) {
        setUser(user);
        setStatus(status);
        setMessage(message);
    }




    public User getUser() {
        return user;
    }

    public ServiceResponse setUser(User user) {
        this.user = user;
        return this;
    }

    public ServiceResponseStatus getStatus() {
        return status;
    }

    public ServiceResponse setStatus(ServiceResponseStatus status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ServiceResponse setMessage(String message) {
        this.message = message;
        return this;
    }


    @Override
    public String toString() {
        return String.format(
                "ServiceResponse {" +
                "\n        user = .. '%s" +
                "\n        status =  '%s'" +
                "\n        message = '%s'" +
                "\n    }",
                user, status, message);
    }
}
