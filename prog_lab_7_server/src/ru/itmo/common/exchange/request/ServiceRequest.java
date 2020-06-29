package ru.itmo.common.exchange.request;

import ru.itmo.common.User;

import java.io.Serializable;
import java.net.InetAddress;


public class ServiceRequest implements Serializable {


    private User user;
    private ServiceRequestStatus status;



    public ServiceRequest() {}


    public ServiceRequest(User user) {
        setUser(user);
    }


    public ServiceRequest(User user, ServiceRequestStatus status) {
        setUser(user);
        setStatus(status);
    }




    public User getUser() {
        return user;
    }

    public ServiceRequest setUser(User user) {
        this.user = user;
        return this;
    }

    public ServiceRequestStatus getStatus() {
        return status;
    }

    public ServiceRequest setStatus(ServiceRequestStatus status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return String.format(
                "ServiceRequest {" +
                "\n        user = . '%s" +
                "\n        status = '%s'" +
                "\n    }",
                user, status);

    }
}
