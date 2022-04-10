package Commands;

import Movie.MovieCollection;

import java.io.IOException;

/**
 * The type Save.
 */
public class Save implements CommandNoArg {
    @Override
    public String execute(Object o) throws IOException {
        return null;
    }

    @Override
    public String getName() {
        return "save";
    }
}