package Commands;

import Movie.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The Movie inserter type.
 */
public class MovieInserter {

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Create movie.
     *
     * @param id the id
     * @return the movie
     */
    public Movie create(String id) {
        Movie movie = new Movie();
        Person director = new Person();
        if(id.equals("free")) {
            movie.setId((int) MovieCollection.getFreeId());
        }
        else{
            movie.setId(Integer.parseInt((String) id));
        }
        this.setName(movie);
        Coordinates coordinates = new Coordinates();
        this.setCoordinateX(coordinates);
        this.setCoordinateY(coordinates);
        movie.setCoordinates(coordinates);
        this.setOscarsCount(movie);
        this.setGoldenPalmsCount(movie);
        this.setMovieGenre(movie);
        this.setMpaaRating(movie);
        this.setDirectorName(director);
        this.setDirectorBirthday(director);
        this.setDirectorEyeColor(director);
        this.setDirectorHairColor(director);
        movie.setCreationDate(LocalDateTime.now());
        movie.setDirector(director);
        return movie;
    }

    /**
     * Sets name.
     *
     * @param movie the movie
     */
    public void setName(Movie movie) {
        System.out.println("Введите название фильма.");
        System.out.print("- ");
        String name = scanner.nextLine();
        if (name.equals("") || name.equals("null")) {
            System.out.println("Имя не может быть пустым");
            this.setName(movie);
        } else
        {
            movie.setName(name);
        }
    }

    /**
     * Sets coordinate x.
     *
     * @param coords the coords
     */
    public void setCoordinateX(Coordinates coords) {
        try {
            System.out.println("Введите координату x:");
            System.out.print("- ");
            String x = scanner.nextLine();
            if (x.equals("") || x.equals(null)) this.setCoordinateX(coords);
            else {
                long xn = Long.parseLong(x);
                if (xn > -296) {
                    coords.setX(xn);
                } else {
                    System.out.println("Минимум -295");
                    this.setCoordinateX(coords);
                }
                coords.setX(xn);
            }
        } catch (InputMismatchException | NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Значение должно быть типа:\"long\".");
            this.setCoordinateX(coords);
        }
    }

    /**
     * Sets coordinate y.
     *
     * @param coords the coords
     */
    public void setCoordinateY(Coordinates coords) {
        try {
            System.out.println("Введите координату y:");
            System.out.print("- ");
            String y = scanner.nextLine();
            if (y.equals("") || y.equals(null)) this.setCoordinateY(coords);
            else {
                float yn = Float.parseFloat(y);
                coords.setY(yn);
            }
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Значение должно быть типа:\"float\".");
            this.setCoordinateY(coords);
        }
    }

    /**
     * Sets oscars number.
     *
     * @param movie the movie
     */
    public void setOscarsCount(Movie movie) {
        try {
            System.out.println("Введите количество оскаров:");
            System.out.print("- ");
            String x = scanner.nextLine();
            int xn = Integer.parseInt(x);
            if (xn<=0) {
                System.out.println("Значение поля должно быть больше 0,попробуйте ещё раз.");
                this.setOscarsCount(movie);
            }
            else movie.setOscarsCount(xn);
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Значение должно быть типа:\"int\".");
            this.setOscarsCount(movie);
        }
    }
    /**
     * Sets golden palms number.
     *
     * @param movie the movie
     */
    public void setGoldenPalmsCount(Movie movie) {
        try {
            System.out.println("Введите количество золотых пальм:");
            System.out.print("- ");
            String x = scanner.nextLine();
            long xn = Long.parseLong(x);
            if (xn<=0) {
                System.out.println("Значение поля должно быть больше 0,попробуйте ещё раз.");
                this.setGoldenPalmsCount(movie);
            }
            else movie.setGoldenPalmCount(xn);
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Значение должно быть типа:\"long\".");
            this.setGoldenPalmsCount(movie);
        }
    }

    /**
     * Sets movie genre.
     *
     * @param movie the movie
     */
    public void setMovieGenre(Movie movie) {
        System.out.println("Введите жанр,регистр не важен.(COMEDY, MUSICAL, ADVENTURE):");
        System.out.print("- ");
        String genre = scanner.nextLine().toUpperCase();
        try {
            if (genre.equals("") || genre.equals("NULL"))
                movie.setMovieGenre(null);
            else
                movie.setMovieGenre(MovieGenre.valueOf(genre));
        } catch (Exception e) {
            System.out.println("Значение должно соответствовать перечислинным типам. Введите значение:");
            this.setMovieGenre(movie);
        }
    }

    /**
     * Sets mpaa rating.
     *
     * @param movie the movie
     */
    public void setMpaaRating(Movie movie) {
        System.out.println("Введите рейтинг,регистр не важен.(G, PG, PG_13, R, NC_17):");
        System.out.print("- ");
        String rat = scanner.nextLine().toUpperCase();
        try {
            if (rat.equals("") || rat.equals("NULL"))
                movie.setMpaaRating(null);
            else
                movie.setMpaaRating(MpaaRating.valueOf(rat));
        } catch (Exception e) {
            System.out.println("Значение должно соответствовать перечислинным типам. Введите значение:");
            this.setMpaaRating(movie);
        }
    }
    /**
     * Sets director name
     *
     * @param director the director
    */
    public void setDirectorName(Person director)
    {
        System.out.println("Введите имя режиссера:");
        System.out.print("- ");
        String rat = scanner.nextLine();
        if (rat.equals("") || rat.equals("null")) {
            System.out.println("Имя не может быть пустым");
            this.setDirectorName(director);
        } else
        {
            director.setName(rat);
        }
    }

    /**
     * Sets director birthday
     *
     * @param director the director
     */
    public void setDirectorBirthday(Person director)
    {
        System.out.println("Введите дату рождения в формате yyyy-MM-dd HH:mm :");
        System.out.print("- ");
        String rat = scanner.nextLine();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime formatDateTime;
            if (rat.equals("")) {
                //formatDateTime = LocalDateTime.parse("0000-00-00 00:00", formatter);
                director.setBirthday(null);
            } else {
                formatDateTime = LocalDateTime.parse(rat, formatter);
                director.setBirthday(formatDateTime);
            }
        }
        catch (Exception e)
        {
            System.out.println("Неверный формат ввода");
            this.setDirectorBirthday(director);
        }
    }
    /**
     * Sets director eye color
     *
     * @param director the director
     */
    public void setDirectorEyeColor(Person director) {
        System.out.println("Введите цвет глаз, регистр не важен (BLUE, YELLOW, ORANGE, BROWN):");
        System.out.print("- ");
        String rat = scanner.nextLine().toUpperCase();
        try {
            if (rat.equals("")) {
                director.setEyeColor(null);
            }
            else
                director.setEyeColor(Color.valueOf(rat));
        } catch(Exception e){
                System.out.println("Значение должно соответствовать перечисленным типам, введите значение:");
                this.setDirectorEyeColor(director);
        }
    }

    /**
     * Sets director hair color
     *
     * @param director the director
     */
    public void setDirectorHairColor(Person director)
    {
        System.out.println("Введите цвет волос, регистр не важен (GREEN, BLACK, BLUE, YELLOW, WHITE):");
        System.out.print("- ");
        String rat = scanner.nextLine().toUpperCase();
        try {
            director.setHairColor(Color.valueOf(rat));
        } catch (Exception e) {
            System.out.println("Значение должно соответствовать перечисленным типам, введите значение:");
            this.setDirectorHairColor(director);
        }
    }

}
