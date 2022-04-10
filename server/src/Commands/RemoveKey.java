package Commands;

import Movie.Movie;
import Movie.MovieCollection;

import java.net.Socket;
import java.util.Iterator;

/**
 * The Remove key type.
 * Remove element by key
 */
public class RemoveKey implements CommandableEx {

    @Override
    public String execute(Object arg, Socket clientSocket) {
        try {
            MovieCollection collection = new MovieCollection();
            if (collection.getSize() == 0) return ("Коллекция пустая.");
            if (collection.isIndexBusy(Integer.parseInt((String) arg))) {
                int id=Integer.parseInt((String) arg);
                for(Iterator<Movie> it = (Iterator<Movie>) MovieCollection.getCollection().iterator(); it.hasNext();)
                {
                    Movie movie = (Movie) it.next();
                    if (movie.getId() == id)
                        it.remove();
                }
                return "Удален фильм [id: "+ id+"]";
            } else return ("Элемента с таким id не существует.");
        } catch (Exception e) {
            return ("Элемента с таким id не существует.");
        }
    }

    @Override
    public String getName() {
        return "removekey";
    }
}
