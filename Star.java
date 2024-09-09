import java.awt.Color;

/**
 * Star class.
 * 
 * @author marcus tran
 * @version 4/3/23
 */
public class Star implements Drawable {
    public static final int STAR_RADIUS = 1;
    private Point location;

    /**
     * The constructor method.
     * 
     * @param x the xposition
     * @param y the yposition
     */
    public Star(double x, double y) {
        this.location = new Point(x, y);
    }

    /**
     * Getter method for location.
     * 
     * @return the location
     */
    public Point getLocation() {
        return location;
    }

    /**
     * Draw method which draws the star.
     */
    public void draw() {
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.filledCircle(location.getX(), location.getY(), STAR_RADIUS);
    }
}
