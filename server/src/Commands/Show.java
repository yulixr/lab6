package Commands;

import Movie.Movie;
import Movie.MovieCollection;

import java.net.Socket;

/**
 * The Show type.
 */
public class Show implements CommandNoArg {
    @Override
    public String execute(Object o, Socket clientSocket) {
        MovieCollection collection = new MovieCollection();
        if (collection.getSize() == 0) return ("Коллекция пустая.");
        else {
            String result="---------------------------\n";
            for (Movie movie : MovieCollection.getCollection()) {
                result+=(movie.getInfo())+"\n---------------------------\n";
            }
            return result;
        }
    }

    @Override
    public String getName() {
        return "show";
    }
}
