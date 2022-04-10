package Commands;

import Movie.*;
import Manager.*;
import java.net.Socket;

/**
 * The Info type.
 */
public class Info implements CommandNoArg{
    @Override
    public String execute(Object o, Socket clientSocket) {
        MovieCollection collection = new MovieCollection();
        return (collection.getInfo());
    }

    @Override
    public String getName() {
        return "info";
    }
}
