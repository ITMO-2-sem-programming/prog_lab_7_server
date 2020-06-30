package ru.itmo.common.exchange.response;

import ru.itmo.common.User;

import java.io.Serializable;



public class CommandResponse implements Serializable {


    private User user;
    private String message;



    public CommandResponse() {}


    public CommandResponse(User user) {
        setUser(user);
    }


    public CommandResponse(User user, String message) {
        setUser(user);
        setMessage(message);
    }




    public User getUser() {
        return user;
    }

    public CommandResponse setUser(User user) {
        this.user = user;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommandResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        return String.format(
                "CommandResponse {" +
                "\n      user = .. '%s" +
                "\n      message = '%s'" +
                "\n    }" +
                "\n",
                user, message);
    }
}
