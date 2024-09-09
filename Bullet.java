/**
 * This is the bullet class.
 * 
 * @author marcus tran
 * @version 4/9/23
 */
public class Bullet extends GameElement {
    public static final double BULLET_RADIUS = 1.5;
    public static final int BULLET_SPEED = 20;
    public static final int BULLET_DURATION = 20;
    private int counter;

    /**
     * This is the default constructor.
     * 
     * @param pose The position to make the bullet.
     */
    public Bullet(Pose pose) {
        super(pose, new Vector2D(pose.getHeading(), BULLET_SPEED), BULLET_RADIUS);
        counter = BULLET_DURATION;
    }

    /**
     * This updates the bullet throughout the game.
     */
    public void update() {
        super.update();
        counter--;
        if (counter <= 0) {
            this.setDestroyed(true);
        }
    }

    /**
     * This method draws it.
     */
    public void draw() {
        Pose pose = getPose();
        StdDraw.filledCircle(pose.getX(), pose.getY(), BULLET_RADIUS);
    }

}