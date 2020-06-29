package ru.itmo.commands;

import ru.itmo.common.classes.MusicBand;
import ru.itmo.main.DataBaseManager;
import ru.itmo.common.User;

import java.sql.Connection;
import java.util.concurrent.ConcurrentSkipListMap;

public class InsertCommand extends Command {

    public static String syntaxDescription =
            "\nCommand: insert <key> {element}" +
                    "\nDescription: Adds new element with the following key (if the key doesn't exist)." +
                    "\nNumber of arguments: 2" +
                    "\n   First argument:  key     (Integer)" +
                    "\n   Second argument: element (MusicBand)\n";


    /**
     * @param collection
     */
    public static String execute(ConcurrentSkipListMap<Integer, MusicBand> collection, Connection connection, User user, MusicBand musicBand) {

//        if (collection.containsKey(key))
//            throw new InvalidCommandException("Error: Collection already contains musicBand with key: " + key + ". Command is impossible.");


       int musicBandID = DataBaseManager.addMusicBand(connection, musicBand);
        DataBaseManager.addOwnedMusicBandIDByUserID(connection, DataBaseManager.getUserIDByUserName(connection, user.getLogin()), musicBandID);

        musicBand.setId(musicBandID);
        collection.put(musicBandID, musicBand);

        return String.format("Reply: MusicBand was successfully added with ID : '%s' .", musicBandID);
    }
}