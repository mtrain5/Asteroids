/**
 * NumericDisplay class.
 * 
 * @author marcus tran
 * @version 4/3/23
 */
public class NumericDisplay implements Drawable {
    private String prefix;
    private int value;
    private Point location;

    /**
     * The default constructor.
     * 
     * @param xPos xposition
     * @param yPos y position
     * @param prefix "score: "
     * @param value the score
     */
    public NumericDisplay(int xPos, int yPos, String prefix, int value) {
        this.location = new Point(xPos, yPos);
        this.value = value;
        this.prefix = prefix;
    }

    /**
     * Getter for location.
     * 
     * @return the location.
     */
    public Point getLocation() {
        return location;

    }

    /**
     * The getter for the score.
     * 
     * @return the score
     */
    public int getValue() {
        return value;
    }

    /**
     * Gives a new value.
     * 
     * @param value the value to replace with
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * The draw method.
     */
    public void draw() {
        StdDraw.textLeft(location.getX(), location.getY(), prefix + Integer.toString(value));
    }
}
