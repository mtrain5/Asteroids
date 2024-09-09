/**
 * The GameElement class.
 * 
 * @author marcus tran
 * @version 4/3/23
 */
abstract class GameElement implements Updatable, Drawable {
    protected Pose pose;
    protected Vector2D velocity;
    protected double radius;
    protected boolean destroyed;

    /**
     * The default constructor.
     * 
     * @param pose the position basically
     * @param velocity how fast its moving
     * @param radius the radius of the object
     */
    public GameElement(Pose pose, Vector2D velocity, double radius) {
        this.pose = pose;
        this.velocity = velocity;
        this.radius = radius;
        this.destroyed = false;
    }

    /**
     * Getter method for pose.
     * 
     * @return the pose
     */
    public Pose getPose() {
        return pose;
    }

    /**
     * Getter method for velocity.
     * 
     * @return the velocity
     */
    public Vector2D getVelocity() {
        return velocity;
    }

    /**
     * Getter method for radius.
     * 
     * @return the radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Getter method for if its destroyed.
     * 
     * @return boolean
     */
    public boolean isDestroyed() {
        return destroyed;
    }

    /**
     * Sets the boolean.
     * 
     * @param destroyed yes or no
     * @return boolean
     */
    public boolean setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
        return destroyed;
    }

    /**
     * Checks for a collision by using the distance formula, but idk if thats what they want (should work).
     * 
     * @param other the other object to check.
     * @return boolean
     */
    public boolean checkCollision(GameElement other) {

        double distance = Math.sqrt(Math.pow(this.pose.getX() - other.getPose().getX(), 2)
                + Math.pow(this.pose.getY() - other.getPose().getY(), 2));

        double collisionRadiusSum = this.radius + other.getRadius();

        return (distance < collisionRadiusSum);
    }

    /**
     * The update method should wrap the object around the edges if it goes off and constantly updates.
     */
    public void update() {
        Pose newPose = pose.move(velocity);

        // wrap around right and left edges
        double x = newPose.getX();
        if (x < 0) {
            newPose = newPose.newX(GameDriver.SCREEN_WIDTH);
        } else if (x > GameDriver.SCREEN_WIDTH) {
            newPose = newPose.newX(0);
        }

        // wrap around top and bottom edges
        double y = newPose.getY();
        if (y < 0) {
            newPose = newPose.newY(GameDriver.SCREEN_HEIGHT);
        } else if (y > GameDriver.SCREEN_HEIGHT) {
            newPose = newPose.newY(0);
        }

        pose = newPose;
    }
}