package Commands;

import Movie.Movie;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.*;

import java.io.*;
import java.util.Vector;

/**
 * The Writer file type.
 */
public class WriterFile {
    static String filename;

    /**
     * Sets filename.
     *
     * @param filename the filename
     */
    public static void setFilename(String filename) {
        WriterFile.filename = filename;
    }
    /**
     * Writing to XML file.
     *
     * @param movies the movies
     * @throws FileNotFoundException the file not found exception
     */
    public static void writeToXMLusingJDOM(Vector<Movie> movies) throws FileNotFoundException, IOException {
        Document doc = new Document();
        doc.setRootElement(new Element("Movies"));
        for (Movie movie : movies) {
            Element movieElement = new Element("Movie");
            movieElement.setAttribute("id",String.valueOf(movie.getId()));
            movieElement.addContent(new Element("Name").setText("" + movie.getName()));
            movieElement.addContent(new Element("Coordinatex").setText("" + movie.getCoordinates().getX()));
            movieElement.addContent(new Element("Coordinatey").setText("" + movie.getCoordinates().getY()));
            movieElement.addContent(new Element("Date").setText("" + movie.getCreationDate()));
            movieElement.addContent(new Element("Oscars").setText("" + movie.getOscarsCount()));
            movieElement.addContent(new Element("GoldenPalms").setText("" + movie.getGoldenPalmCount()));
            movieElement.addContent(new Element("Genre").setText("" + movie.getMovieGenre()));
            movieElement.addContent(new Element("Mpaa").setText("" + movie.getMpaaRating()));
            movieElement.addContent(new Element("NameofCreator").setText("" + movie.getDirector().getName()));
            movieElement.addContent(new Element("BirthofCreator").setText("" + movie.getDirector().getBirthday()));
            movieElement.addContent(new Element("EyeColorofCreator").setText("" + movie.getDirector().getEyeColor()));
            movieElement.addContent(new Element("HairColorofCreator").setText("" + movie.getDirector().getHairColor()));
            doc.getRootElement().addContent(movieElement);
        }
        XMLOutputter xmlWriter = new XMLOutputter(Format.getPrettyFormat());
        xmlWriter.output(doc, new FileOutputStream(filename));
        System.out.println("Записано в файл");
    }
    /**
     * Writing to XML file.
     *
     * @param movie the movie
     * @throws FileNotFoundException the file not found exception
     */
    public static void writeToXMLusingJDOMoneMovie(Movie movie) throws FileNotFoundException, IOException {
        Document doc = new Document();
        doc.setRootElement(new Element("Movies"));

            Element movieElement = new Element("Movie");
            movieElement.setAttribute("id",String.valueOf(movie.getId()));
            movieElement.addContent(new Element("Name").setText("" + movie.getName()));
            movieElement.addContent(new Element("Coordinatex").setText("" + movie.getCoordinates().getX()));
            movieElement.addContent(new Element("Coordinatey").setText("" + movie.getCoordinates().getY()));
            movieElement.addContent(new Element("Date").setText("" + movie.getCreationDate()));
            movieElement.addContent(new Element("Oscars").setText("" + movie.getOscarsCount()));
            movieElement.addContent(new Element("GoldenPalms").setText("" + movie.getGoldenPalmCount()));
            movieElement.addContent(new Element("Genre").setText("" + movie.getMovieGenre()));
            movieElement.addContent(new Element("Mpaa").setText("" + movie.getMpaaRating()));
            movieElement.addContent(new Element("NameofCreator").setText("" + movie.getDirector().getName()));
            movieElement.addContent(new Element("BirthofCreator").setText("" + movie.getDirector().getBirthday()));
            movieElement.addContent(new Element("EyeColorofCreator").setText("" + movie.getDirector().getEyeColor()));
            movieElement.addContent(new Element("HairColorofCreator").setText("" + movie.getDirector().getHairColor()));
            doc.getRootElement().addContent(movieElement);
        XMLOutputter xmlWriter = new XMLOutputter(Format.getPrettyFormat());
        xmlWriter.output(doc, new FileOutputStream(filename));
        System.out.println("Записано в файл");
    }

    /**
     * Write to file.
     *
     * @param movies the movies
     * @throws FileNotFoundException the file not found exception
     */
    public static void writeLabToFile(Vector<Movie> movies) throws FileNotFoundException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for(Movie movie : movies)
            {
                writer.write(movie.getInfo());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
