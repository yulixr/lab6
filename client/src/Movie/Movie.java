package Movie;

import java.time.LocalDateTime;

/**
 * The Movie.Movie type.
 */
public class Movie implements Comparable{
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int oscarsCount; //Значение поля должно быть больше 0
    private long goldenPalmCount; //Значение поля должно быть больше 0
    private MovieGenre genre; //Поле может быть null
    private MpaaRating mpaaRating; //Поле может быть null
    private Person director; //Поле не может быть null

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets coordinates.
     *
     * @return the coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Sets coordinates.
     *
     * @param coordinates the coordinates
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Gets creation date.
     *
     * @return the creation date
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Sets creation date.
     *
     * @param creationDate the creation date
     */
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Gets number of oscars.
     *
     * @return the number of oscars
     */
    public int getOscarsCount() {
        return oscarsCount;
    }

    /**
     * Sets number of oscars.
     *
     * @param oscarsCount the number of oscars
     */
    public void setOscarsCount(int oscarsCount) {
        this.oscarsCount = oscarsCount;
    }

    /**
     * Gets number of golden palms.
     *
     * @return the number of golden palms
     */
    public long getGoldenPalmCount() {
        return goldenPalmCount;
    }

    /**
     * Sets number of golden palms.
     *
     * @param goldenPalmCount the number of golden palms
     */
    public void setGoldenPalmCount(long goldenPalmCount) {
        this.goldenPalmCount = goldenPalmCount;
    }

    /**
     * Gets movie genre.
     *
     * @return the genre
     */
    public MovieGenre getMovieGenre() {
        return genre;
    }

    /**
     * Sets movie genre.
     *
     * @param genre the genre
     */
    public void setMovieGenre(MovieGenre genre) {
        this.genre = genre;
    }

    /**
     * Gets rating.
     *
     * @return the mpaarating
     */
    public MpaaRating getMpaaRating() {
        return mpaaRating;
    }

    /**
     * Sets mpaa rating.
     *
     * @param mpaaRating the raping
     */
    public void setMpaaRating(MpaaRating mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    /**
     * Gets director.
     *
     * @return the director
     */
    public Person getDirector() {
        return director;
    }
    public void setDirector(Person director){ this.director = director;};
    /**
     * Sets the director name.
     *
     * @param name the director name
     */
    public void setDirectorName(String name) {
        this.director.setName(name);
    }
    /**
     * Sets the director birthday.
     *
     * @param birthday the director birthday
     */
    public void setDirectorBirthday(LocalDateTime birthday) {
        this.director.setBirthday(birthday);
    }
    /**
     * Sets the director eye color.
     *
     * @param color the director eye color
     */
    public void setDirectorEyeColor(Color color) {
        this.director.setEyeColor(color);
    }
    /**
     * Sets the director hair color.
     *
     * @param color the director hair color
     */
    public void setDirectorHairColor(Color color) {
        this.director.setHairColor(color);
    }

    /**
     * Gets info about movie.
     *
     * @return the info
     */
    public String getInfo() {
        return "Фильм [id:" + id + "]:\n " + "Название: " + name + "\n Координаты:\n    x: " + coordinates.getX() +
                "\n    y: " + coordinates.getY() +  "\n  Дата cоздания: " + creationDate +"\n  Оскары: "+ oscarsCount +
                "\n  Золотые пальмы: "+ goldenPalmCount+ "\n  Жанр: " + genre +
                "\n  Рейтинг: "+ mpaaRating + "\n  Имя создателя: " + director.getName() + "\n  Дата рождения создателя: " +
                director.getBirthday() + "\n  Цвет глаз создателя: " + director.getEyeColor() + "\n  Цвет волос создателя: " + director.getHairColor();
    }

    @Override
    public int compareTo(Object o) {
        Movie movie = (Movie)o;
        return (int) (this.getOscarsCount()-movie.getOscarsCount());
    }
}
