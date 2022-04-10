package Commands;

import Movie.Movie;
import Movie.MovieCollection;

import java.util.Iterator;

/**
 * The Remove by MpaaRating type.
 * Remove element by MpaaRating
 */
public class RemoveByMpaa implements CommandableEx{
    @Override
    public String execute(Object arg) {

        return null;
    }

    @Override
    public String getName() {
        return "remove_all_by_mpaa_rating";
    }
}
