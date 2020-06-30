package ru.itmo.common.exchange.request;

import ru.itmo.common.User;
import ru.itmo.common.classes.MusicBand;

import java.io.Serializable;


public class CommandRequest implements Serializable {


    private User user;
    private String commandName;
    private String argumentKey;
    private MusicBand argumentMusicBand;

//    private final User USER_DEFAULT_VALUE = new User();
//    public static final String COMMAND_NAME_DEFAULT = "command";
//    public static final String ARGUMENT_KEY_DEFAULT = "argument";


    public CommandRequest() {
//        user = new User();
//        commandName = COMMAND_NAME_DEFAULT;
//        argumentKey = ARGUMENT_KEY_DEFAULT;
//        argumentMusicBand = new MusicBand();
    }


    public CommandRequest(User user, String commandName, String argumentKey, MusicBand argumentMusicBand) {
        setUser(user);
        setCommandName(commandName);
        setArgumentKey(argumentKey);
        setArgumentMusicBand(argumentMusicBand);
    }




    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCommandName() {
        return commandName;
    }

    public CommandRequest setCommandName(String commandName) {
        this.commandName = commandName;
        return this;
    }

    public String getArgumentKey() {
        return argumentKey;
    }

    public CommandRequest setArgumentKey(String argumentKey) {
        this.argumentKey = argumentKey;
        return this;
    }

    public MusicBand getArgumentMusicBand() {
        return argumentMusicBand;
    }

    public CommandRequest setArgumentMusicBand(MusicBand argumentElement) {
        this.argumentMusicBand = argumentElement;
        return this;
    }

    @Override
    public String toString() {
        return String.format(
                "CommandRequest {" +
                "\n      user =  ........... '%s" +
                "\n      commandName = ..... '%s'" +
                "\n      argumentKey = ....  '%s'" +
                "\n      argumentMusicBand = '%s'" +
                "\n    }" +
                "\n", user, commandName, argumentKey, argumentMusicBand);
    }
}
