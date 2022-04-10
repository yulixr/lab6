package Commands;

import Movie.Movie;
import Movie.MovieCollection;

import java.util.Iterator;

/**
 * The Remove Greater type.
 *  Removes elements after the exact one.
 */
public class RemoveGreater implements CommandableEx{
    @Override
    public String execute(Object arg) {

        return null;
    }

    @Override
    public String getName() {
        return "removegreater";
    }
}
