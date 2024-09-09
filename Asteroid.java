/**
 * Asteroid Class.
 * 
 * @author marcus tran
 * @version 4/12/23
 */
public class Asteroid extends Enemy {
    public static final int ASTEROID_SPEED = 1;

    /**
     * The constructor for asteroid.
     * 
     * @param size size of the asteroid
     */
    public Asteroid(AsteroidSize size) {
        super(ASTEROID_SPEED, size.getRadius(), size.getPoints());
    }

    /**
     * This draws the asteroid.
     */
    public void draw() {
        // StdDraw.setPenRadius(0.02);
        StdDraw.circle(super.getPose().getX(), super.getPose().getY(), super.getRadius());
    }

}