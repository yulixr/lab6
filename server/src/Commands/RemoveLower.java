package Commands;

import Movie.Movie;
import Movie.MovieCollection;

import java.net.Socket;
import java.util.Iterator;

/**
 * The Remove Lower type.
 *  Removes elements before the exact one.
 */
public class RemoveLower implements CommandableEx{
    @Override
    public String execute(Object arg, Socket clientSocket) {
        try {
            MovieCollection collection = new MovieCollection();
            if (collection.getSize() == 0) return ("Коллекция пустая.");
            else {
                int id = Integer.parseInt((String) arg);
                for(Iterator<Movie> it = (Iterator<Movie>) MovieCollection.getCollection().iterator(); it.hasNext();)
                {
                    Movie movie = (Movie) it.next();
                    if (movie.getId() < id)
                        it.remove();
                }
                return ("Удалено ниже [id: "+ id+"]");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getName() {
        return "removelower";
    }
}
