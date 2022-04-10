package Commands;

import Movie.Movie;
import Movie.MovieCollection;

/**
 * The Show type.
 */
public class Show implements CommandNoArg {
    @Override
    public String execute(Object o) {
        return null;
    }

    @Override
    public String getName() {
        return "show";
    }
}
