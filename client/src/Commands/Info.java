package Commands;

import Movie.MovieCollection;

/**
 * The Info type.
 */
public class Info implements CommandNoArg{
    @Override
    public String execute(Object o) {
      return null;
    }

    @Override
    public String getName() {
        return "info";
    }
}
