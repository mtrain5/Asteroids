
import java.util.Random;

/**
 * The saucer class.
 * 
 * @author marcus tran
 * @version 4/12/23
 */
public class Saucer extends Enemy {

    public static final int HALF_WIDTH = 10;
    public static final int HALF_HEIGHT = 5;
    public static final int SAUCER_SPEED = 2;
    public static final int SAUCER_POINTS = 400;
    public static final double SPAWN_PROB = 0.002;
    public static final double TURN_PROB = 0.05;

    /**
     * The default constructor for saucer.
     */
    public Saucer() {
        super(SAUCER_SPEED, Math.sqrt(2 * HALF_WIDTH * HALF_HEIGHT), SAUCER_POINTS);
        double x = Math.random() * GameDriver.SCREEN_WIDTH;
        double y = Math.random() * GameDriver.SCREEN_HEIGHT;
        pose = new Pose(x, y, 0);
    }

    /**
     * Updates the saucer and moves it randomly.
     */
    public void update() {

        velocity = velocity.newMagnitude(SAUCER_SPEED);

        pose = pose.move(velocity);

        // check if saucer has gone off screen
        double x = pose.getX();
        double y = pose.getY();
        Random random = new Random();
        int check = random.nextInt(101);

        if (x < -HALF_WIDTH || x > GameDriver.SCREEN_WIDTH + HALF_WIDTH || y < -HALF_HEIGHT
                || y > GameDriver.SCREEN_HEIGHT + HALF_HEIGHT) {
            this.setDestroyed(true);
        }

        if (check < TURN_PROB * 100) {
            double newAngle = Math.random() * 2 * Math.PI;
            velocity = velocity.newHeading(newAngle);
        }
    }

    /**
     * Draws the object.
     */
    public void draw() {

        StdDraw.rectangle(pose.getX(), pose.getY(), HALF_WIDTH, HALF_HEIGHT);
    }
}