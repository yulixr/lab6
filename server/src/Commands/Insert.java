package Commands;

import Movie.Movie;
import Movie.MovieCollection;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.net.Socket;

/**
 * The type Insert.
 */
public class Insert implements CommandableEx {

    @Override
    public String execute(Object o, Socket clientSocket) throws IOException {
            XmlMapper mapper = new XmlMapper();
            mapper.registerModule(new JavaTimeModule());
            //mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            //mapper.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Movie movie = mapper.readValue((String) o, Movie.class);
            MovieCollection collection = new MovieCollection();
            collection.add(movie);
            collection.save();
        return ("Вы добавили фильм в коллекцию");
    }

    @Override
    public String getName() {
        return "add";
    }
}