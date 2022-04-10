package Commands;

import Movie.MovieCollection;

import java.io.IOException;
import java.net.Socket;

/**
 * The type Save.
 */
public class Save implements CommandNoArg {
    @Override
    public String execute(Object o, Socket clientSocket) throws IOException {
        WriterFile.writeToXMLusingJDOM(MovieCollection.getCollection());
        return ("Сохранено");
    }

    @Override
    public String getName() {
        return "save";
    }
}