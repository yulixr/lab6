package Commands;

import Movie.*;

import java.net.Socket;
import java.util.Vector;

/**
 * The Clear type.
 */
public class Clear implements CommandNoArg {

    @Override
    public String execute(Object o, Socket clientSocket) {
        MovieCollection collection = new MovieCollection();
        if (collection.getSize()==0) return "Коллекция и так пустая.";
        collection.clear();
        return ("Коллекция очищена.");
    }

    @Override
    public String getName() {
        return "clear";
    }
}