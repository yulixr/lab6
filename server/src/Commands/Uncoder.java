package Commands;

import Movie.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Vector;

/**
 * The type Uncoder.
 */
public class Uncoder {
    /**
     * Gets tag by value
     * @param tag the tag
     * @param element the element
     */
    private static String getTagValue(String tag, Element element)
    {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }
    /**
     * Gets movie by node
     * @param node the node
     * @return Movie
     */
    public static Movie getMovie(Node node){
        Movie movie = new Movie();
        Coordinates coord = new Coordinates();
        Person director = new Person();
        if (node.getNodeType() == Node.ELEMENT_NODE){
            Element element = (Element) node;
            movie.setId(Integer.parseInt(element.getAttribute("id")));
            movie.setName(getTagValue("Name", element));
            coord.setX(Long.parseLong(getTagValue("Coordinatex", element)));
            coord.setY(Float.parseFloat(getTagValue("Coordinatey", element)));
            movie.setCoordinates(coord);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime formatDateTime = LocalDateTime.parse(getTagValue("Date", element));
            movie.setCreationDate(formatDateTime);
            movie.setOscarsCount(Integer.parseInt(getTagValue("Oscars", element)));
            movie.setGoldenPalmCount(Long.parseLong(getTagValue("GoldenPalms", element)));
            if (Objects.equals(getTagValue("Genre", element), "null"))
                movie.setMovieGenre(null);
            else
                movie.setMovieGenre(MovieGenre.valueOf(getTagValue("Genre", element)));
            if (Objects.equals(getTagValue("Mpaa", element), "null"))
                movie.setMpaaRating(null);
            else
                movie.setMpaaRating(MpaaRating.valueOf(getTagValue("Mpaa", element)));
            director.setName(getTagValue("NameofCreator", element));
            if (Objects.equals(getTagValue("BirthofCreator", element), "null"))
                director.setBirthday(null);
            else{
                formatDateTime = LocalDateTime.parse(getTagValue("BirthofCreator", element));
                director.setBirthday(formatDateTime);
            }
            if (Objects.equals(getTagValue("EyeColorofCreator", element), "null"))
                director.setEyeColor(null);
            else
                director.setEyeColor(Color.valueOf(getTagValue("EyeColorofCreator", element)));
            director.setHairColor(Color.valueOf(getTagValue("HairColorofCreator", element)));
            movie.setDirector(director);
        }
        return movie;
   }
    /**
     * Fill collection.
     *
     * @param filename the filename
     */
    public static void fillCollection(String filename){
        Vector<Movie> collection = new Vector<>();
        File xmlFile = new File(filename);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            System.out.println("Корень " + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("Movie");

            for (int i = 0; i < nodeList.getLength(); i++) {
                collection.add(getMovie(nodeList.item(i)));
            }
            MovieCollection.setCollection(collection);
            System.out.println("Коллекция успешно заполнена");
            WriterFile.setFilename(filename);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
