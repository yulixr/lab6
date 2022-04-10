package Commands;

import Movie.Movie;
import Movie.MovieCollection;

import java.util.Iterator;

/**
 * The Remove key type.
 * Remove element by key
 */
public class RemoveKey implements CommandableEx {

    @Override
    public String execute(Object arg) {
        return null;
    }

    @Override
    public String getName() {
        return "removekey";
    }
}
