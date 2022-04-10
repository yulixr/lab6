package Commands;

import Movie.Movie;
import Movie.MovieCollection;

import java.util.Iterator;

/**
 * The Remove Lower type.
 *  Removes elements before the exact one.
 */
public class RemoveLower implements CommandableEx{
    @Override
    public String execute(Object arg) {

        return null;
    }

    @Override
    public String getName() {
        return "removelower";
    }
}
