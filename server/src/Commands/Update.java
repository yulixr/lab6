package Commands;

import Manager.CollectionManager;
import Movie.Movie;
import Movie.MovieCollection;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.net.Socket;

/**
 * The type Update.
 */
public class Update implements CommandableEx {
    CollectionManager manager;

    @Override
    public String execute(Object arg, Socket clientSocket) {
        MovieCollection collection = new MovieCollection();
        try {
            XmlMapper mapper = new XmlMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Movie movie = mapper.readValue((String) arg, Movie.class);
            if (collection.isIndexBusy(movie.getId())) {
                int id=movie.getId();
                Movie movieToDelete = MovieCollection.getCollection().stream().filter(o->o.getId()==id).findFirst().get();
                collection.add(movie);
                MovieCollection.getCollection().remove(movieToDelete);
                collection.save();
                return ("Фильм [id:" + id + "] успешно обновлен.");
            } else return ("Элемента с таким id не существует.");
        } catch (Exception e) {
            return ("Элемента с таким id не существует.");
        }
//        MovieCollection collection = new MovieCollection();
//        try {
//            if (collection.isIndexBusy(Integer.parseInt((String) arg))) {
//                int id=Integer.parseInt((String) arg);
//                Movie movieToDelete = MovieCollection.getCollection().stream().filter(o->o.getId()==id).findFirst().get();
//                collection.add((new MovieInserter()).create( (String) arg));
//                MovieCollection.getCollection().remove(movieToDelete);
//                return ("Фильм [id:" + arg + "] успешно обновлен.");
//            } else return ("Элемента с таким id не существует.");
//        } catch (Exception e) {
//            return ("Элемента с таким id не существует.");
//        }
    }

    @Override
    public String getName() {
        return "update";
    }
}