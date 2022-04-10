package Commands;

import Movie.Movie;
import Movie.MovieCollection;

import java.util.Iterator;

/**
 * The Count oscars type.
 */
public class CountOscars implements CommandableEx{
    @Override
    public String execute(Object arg) {
        return null;
    }

    @Override
    public String getName() {
        return "count_by_oscars_count";
    }
}
