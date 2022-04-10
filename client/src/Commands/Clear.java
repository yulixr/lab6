package Commands;

import Movie.MovieCollection;

/**
 * The Clear type.
 */
public class Clear implements CommandNoArg {

    @Override
    public String execute(Object o) {
        return null;
    }

    @Override
    public String getName() {
        return "clear";
    }
}