package Commands;

import Movie.Movie;
import Movie.MovieCollection;

import java.net.Socket;
import java.util.Iterator;

/**
 * The Remove by MpaaRating type.
 * Remove element by MpaaRating
 */
public class RemoveByMpaa implements CommandableEx{
    @Override
    public String execute(Object arg, Socket clientSocket) {
        try {
            MovieCollection collection = new MovieCollection();
            if (collection.getSize() == 0) return ("Коллекция пустая.");
            else {
                String rat = (String) arg;
                for(Iterator<Movie> it = (Iterator<Movie>) MovieCollection.getCollection().iterator(); it.hasNext();)
                {
                    Movie movie = (Movie) it.next();
                    if (movie.getMpaaRating().toString().equals(rat.toUpperCase()))
                        it.remove();
                }
                return ("Удалены фильмы с рейтингом "+ rat);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getName() {
        return "remove_all_by_mpaa_rating";
    }
}
