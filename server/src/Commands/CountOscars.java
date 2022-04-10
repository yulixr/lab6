package Commands;

import Movie.Movie;
import Movie.MovieCollection;

import java.net.Socket;
import java.util.Iterator;

/**
 * The Count oscars type.
 */
public class CountOscars implements CommandableEx{
    @Override
    public String execute(Object arg, Socket clientSocket) {
            int count;
            MovieCollection collection = new MovieCollection();
            if (collection.getSize() == 0) return ("Коллекция пустая.");
            else
                count = (int) MovieCollection.getCollection().stream().filter(x -> x.getOscarsCount() == Integer.parseInt((String) arg)).count();
                return ("Фильмов с таким количеством оскаров: "+ count+"");
    }

    @Override
    public String getName() {
        return "count_by_oscars_count";
    }
}
