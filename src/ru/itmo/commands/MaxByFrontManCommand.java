package ru.itmo.commands;

import ru.itmo.common.classes.MusicBand;
import ru.itmo.common.classes.Person;

import java.util.concurrent.ConcurrentSkipListMap;

public class MaxByFrontManCommand extends Command{

    public static String syntaxDescription =
                    "\nCommand: max_by_front_man" +
                    "\nDescription: Prints element, which field 'frontMan' is the greatest." +
                    "\nNumber of arguments: 0\n";


    /**
     * @param collection
     * @return
     */
    public static String execute(ConcurrentSkipListMap<Integer, MusicBand> collection) {

        checkCollectionForEmptiness(collection);

        return collection.values().stream().max((mb1, mb2) -> Person.compare(mb1.getFrontMan(), mb2.getFrontMan())).toString();
    }

}
