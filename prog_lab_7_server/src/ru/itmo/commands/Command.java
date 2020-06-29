package ru.itmo.commands;

import ru.itmo.common.classes.MusicBand;
import ru.itmo.personalExceptions.InvalidCommandException;

import java.util.concurrent.ConcurrentSkipListMap;

public abstract class Command {


    protected static void checkCollectionForEmptiness(ConcurrentSkipListMap<Integer, MusicBand> collection) {
    if (collection.size() == 0)
        throw new InvalidCommandException("Error: Collection is empty. Impossible to run the command.");
    }
}
