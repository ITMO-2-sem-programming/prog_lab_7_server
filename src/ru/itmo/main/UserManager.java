package ru.itmo.main;

import ru.itmo.common.User;
import ru.itmo.common.exchange.request.*;
import ru.itmo.common.exchange.response.*;

import ru.itmo.personalExceptions.DBException;

import java.sql.Connection;



public class UserManager {


    public static ServiceResponse executeServiceRequest(Connection connection, ServiceRequest serviceRequest) {

        ServiceResponse serviceResponse = new ServiceResponse();

        User user = serviceRequest.getUser();

        if (serviceRequest.getStatus() == ServiceRequestStatus.REGISTRATION) {
            try {
                int id = DataBaseManager.addUser(connection, user);
                DataBaseManager.addUserToAccessTable(connection, id);
                serviceResponse.setStatus(ServiceResponseStatus.REGISTERED);

            } catch (DBException e) {
                serviceResponse.setMessage(e.getMessage());
                serviceResponse.setStatus(ServiceResponseStatus.ERROR);
            }


        } else if (serviceRequest.getStatus() == ServiceRequestStatus.AUTHORISATION) {
            User userFromDB;

            try {
                userFromDB = DataBaseManager.getUserByUserName(connection, user.getLogin());

                if (userFromDB == null) {
                    serviceResponse.setMessage("Error: No user with the specified user name.");
                    serviceResponse.setStatus(ServiceResponseStatus.ERROR);

                } else if ( ! user.getPassword().equals(userFromDB.getPassword())) {
                    serviceResponse.setMessage("Error: Wrong password.");
                    serviceResponse.setStatus(ServiceResponseStatus.ERROR);

                } else {
                    serviceResponse.setStatus(ServiceResponseStatus.AUTHORISED);
                }

            } catch (DBException e) {
                serviceResponse.setMessage(e.getMessage());
                serviceResponse.setStatus(ServiceResponseStatus.ERROR);
            }


        }

        return serviceResponse;
    }


//    public static Response executeServiceRequest(Connection connection, Request request) {
//        return executeServiceRequest(connection, request.getUser(), request.getServiceRequest());
//    } // Что насчет исключений ***

}