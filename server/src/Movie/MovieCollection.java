package Movie;

import Commands.MovieInserter;
import Commands.Uncoder;
import Commands.WriterFile;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Hashtable;
import java.util.Vector;

/**
 * The Movie collection type.
 */
public class MovieCollection {
    private static Vector<Movie> collection = new Vector<Movie>();
    private static LocalDate creationDate;
    /**
     * Instantiates a new Movie collection.
     */
    public MovieCollection() {
        creationDate = LocalDate.now();
    }
    public MovieCollection(String collectionPath) {
        try {
            if (collectionPath == null) throw new FileNotFoundException();
        } catch (FileNotFoundException ex) {
            System.err.println("Путь к файлу должен быть задан переменной окружения 'collection.'");
            System.exit(1);
        }
        Uncoder.fillCollection(collectionPath);
    }


    /**
     * Gets collection.
     *
     * @return the collection
     */
    public static Vector<Movie> getCollection() {
        return collection;
    }

    /**
     * Sets collection.
     *
     * @param collection the collection
     */
    public static void setCollection(Vector<Movie> collection) {
        MovieCollection.collection = collection;
    }

    /**
     * Gets creation date.
     *
     * @return the creation date
     */
    public static LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * Sets creation date.
     *
     * @param creationDate the creation date
     */
    public static void setCreationDate(LocalDate creationDate) {
        MovieCollection.creationDate = creationDate;
    }

    /**
     * Clear collection.
     */
    public void clear(){ collection.clear(); }

    /**
     * Get free id long.
     *
     * @return the long
     */
    public static long getFreeId(){
        long size = collection.size();
        Hashtable<Integer, String> busyIds = new Hashtable<Integer, String>();
        for (Movie movie: collection){
            busyIds.put(movie.getId(), "id");
        }
        int id = 1;
        while (true){
            if (busyIds.get(id) == null) {
                return id;
            }
            else id++;
        }
    }

    /**
     * Get size int.
     *
     * @return the int
     */
    public int getSize(){
        return collection.size();
    }

    /**
     * Gets info.
     *
     * @return the info
     */
    public String getInfo() {
        return "Тип коллекции: Vector\nРазмер коллекции: " + this.getSize() + "\nВремя последней инициализации: " + creationDate;
    }

    /**
     * Is id busy boolean.
     *
     * @param id the id
     * @return true if busy, false if empty
     */
    public boolean isIndexBusy(int id){
        for (Movie movie: collection)
            if (movie.getId() == id) return true;
        return false;
    }

    /**
     * Add new movie.
     *
     * @param movie the movie
     */
    public void add(Movie movie) {
        collection.add(movie);
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

}

