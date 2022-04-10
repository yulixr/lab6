package Commands;

import Movie.Movie;
import Movie.MovieCollection;

import java.util.Iterator;

/**
 * The Show Oscars Elements.
 *  Shows elements with exact oscars number.
 */
public class ShowOscarsElements implements CommandableEx{
    @Override
    public String execute(Object arg) {

        return null;
    }

    @Override
    public String getName() {
        return "filter_by_oscars_count";
    }
}
