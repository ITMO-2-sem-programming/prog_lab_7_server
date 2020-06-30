package ru.itmo.common;

import java.io.Serializable;


public class User implements Serializable {


    private String login;
    private String password;

//    public static final String LOGIN_DEFAULT = "login";
//    public static final String PASSWORD_DEFAULT = "password";



    public User() {
//        login = LOGIN_DEFAULT;
//        password = PASSWORD_DEFAULT;
    }


    public User(String login, String password) {
        setLogin(login);
        setPassword(password);
    }




    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format(
                "User {" +
                "\n            login = .. '%s'" +
                "\n            password = '%s'" +
                "\n        }",
                login, password);
    }
}
