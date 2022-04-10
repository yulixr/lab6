package Manager;


import Commands.*;
import Movie.Movie;
import Movie.MovieCollection;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Класс CollectionManager обеспечивает доступ к коллекции.
 */
public final class CollectionManager {
    private MovieCollection movies;
    {
        movies = new MovieCollection();
    }

    /**
     * Предоставляет доступ к коллекции
     * @param collectionPath путь к файлу
     */
    public CollectionManager(String collectionPath) {
        try {
            if (collectionPath == null) throw new FileNotFoundException();
        } catch (FileNotFoundException ex) {
            System.err.println("Путь к файлу должен быть задан переменной окружения .'");
            System.exit(1);
        }
        Uncoder.fillCollection(collectionPath);
    }

    /**
     * Записывает элементы коллекции в файл.
     */
    public void save() {
        try {
            WriterFile.writeToXMLusingJDOM(MovieCollection.getCollection());
            System.out.println("Сохранено");
        } catch (Exception ex) {
            System.out.println("Возникла непредвиденная ошибка. Коллекция не может быть сохранена.");
        }
    }

    public MovieCollection getCollection() {
        return movies;
    }


}