package Commands;

import Movie.Movie;
import Movie.MovieCollection;

import java.net.Socket;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * The Show Oscars Elements.
 *  Shows elements with exact oscars number.
 */
public class ShowOscarsElements implements CommandableEx{
    @Override
    public String execute(Object arg, Socket clientSocket) {
        try {
            String s = "Вывожу элементы с количеством оскаров " + (String)arg + "\n---------------------------\n";
            StringBuilder stringBuilder = new StringBuilder(s);
            MovieCollection collection = new MovieCollection();
            if (collection.getSize() == 0) return ("Коллекция пустая.");
            else {
                AtomicBoolean was = new AtomicBoolean(false);
                MovieCollection.getCollection().stream().filter(x -> x.getOscarsCount().toString().equals(arg)).
                        forEach(x -> {
                            was.set(true);
                            stringBuilder.append(x.getInfo() + "\n---------------------------\n");
                        });
                if (!was.get()) return ("Нет таких элементов в коллекции");
                else return stringBuilder.toString();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getName() {
        return "filter_by_oscars_count";
    }
}
