import java.util.Random;

/**
 * AsteroidSize class.
 * 
 * @author marcus tran
 * @version 4/12/23
 */
public enum AsteroidSize {
    SMALL(10, 200), MEDIUM(20, 100), LARGE(30, 50);

    private int radius;
    private int points;

    /**
     * Creates the asteroid.
     * 
     * @param radius radius of asteroid
     * @param points value of asteroid
     */
    AsteroidSize(int radius, int points) {
        this.radius = radius;
        this.points = points;
    }

    /**
     * Gets the radius.
     * 
     * @return radius the radius
     */
    public int getRadius() {
        return radius;
    }

    /**
     * Gets the point values.
     * 
     * @return points the point value
     */
    public int getPoints() {
        return points;
    }

    /**
     * Generates a random size.
     * 
     * @return random size
     */
    public static AsteroidSize randomSize() {
        Random random = GameDriver.GENERATOR;
        double randomValue = random.nextDouble();
        if (randomValue < 0.25) {
            return SMALL;
        } else if (randomValue < 0.5) {
            return MEDIUM;
        } else {
            return LARGE;
        }
    }
}
