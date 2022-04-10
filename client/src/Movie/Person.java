package Movie;
/**
 * The person class type
 */
public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.time.LocalDateTime birthday; //Поле может быть null
    private Color eyeColor; //Поле может быть null
    private Color hairColor; //Поле не может быть null
    /**
     * Sets name of director
     * @param name the name
     */
    public void setName(String name){ this.name = name;}
    /**
     * Gets name of director
     * @return name the name
     */
    public String getName(){return this.name;}
    /**
     * Sets birthday of director
     * @param birthday the birthday
     */
    public void setBirthday(java.time.LocalDateTime birthday){ this.birthday = birthday;}
    /**
     * Gets birthday of director
     * @return birthday the birthday
     */
    public java.time.LocalDateTime getBirthday(){return this.birthday;}
    /**
     * Sets eyecolor of director
     * @param eyeColor the color
     */
    public void setEyeColor(Color eyeColor){this.eyeColor = eyeColor;}
    /**
     * Gets eyecolor of director
     * @return eyecolor the eye color
     */
    public Color getEyeColor(){return this.eyeColor;}
    /**
     * Sets hair color of director
     * @param hairColor the haircolor
     */
    public void setHairColor(Color hairColor){this.hairColor = hairColor;}
    /**
     * Gets haircolor of director
     * @return haircolor the hair color
     */
    public Color getHairColor(){return this.hairColor;}
}
