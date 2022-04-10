package Movie;

/**
 * The type Movie.Coordinates.
 */
public class Coordinates {
    private long x;  //Значение поля должно быть больше -296
    private Float y; //Поле не может быть null

    /**
     * Gets x.
     *
     * @return the x
     */
    public long getX() {
        return x;
    }

    /**
     * Sets x.
     *
     * @param x the x
     */
    public void setX(long x) {
        this.x = x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public Float getY() {
        return y;
    }

    /**
     * Sets y.
     *
     * @param y the y
     */
    public void setY(Float y) {
        this.y = y;
    }


}
