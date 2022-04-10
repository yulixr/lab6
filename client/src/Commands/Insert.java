package Commands;

import Movie.MovieCollection;

/**
 * The type Insert.
 */
public class Insert implements CommandNoArg {

    @Override
    public String execute(Object o) {
        return null;
    }

    @Override
    public String getName() {
        return "insert";
    }
}