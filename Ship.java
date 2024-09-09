/**
 * Ship Class.
 * 
 * @author marcus tran
 * @version 4/9/23
 */
public class Ship extends GameElement {
    public static final int SHIP_WIDTH = 10;
    public static final int SHIP_HEIGHT = 20;
    public static final double SHIP_TURN = 0.1;
    public static final double SHIP_MOVE = 0.1;
    public static final double FRICTION = 0.02;

    /**
     * The default constructor.
     */
    public Ship() {
        super(new Pose(GameDriver.SCREEN_WIDTH / 2, GameDriver.SCREEN_HEIGHT / 2, Math.PI / 2), new Vector2D(0, 0),
                SHIP_HEIGHT / 2);
    }

    /**
     * This method makes the ship turn.
     */
    public void turnLeft() {
        double newHeading = getPose().getHeading() + SHIP_TURN;
        super.pose = super.pose.newHeading(newHeading);
    }

    /**
     * This method makes the ship turn to the other direction.
     */
    public void turnRight() {
        double newHeading = getPose().getHeading() - SHIP_TURN;
        super.pose = super.pose.newHeading(newHeading);
    }

    /**
     * This method makes the ship go faster.
     */
    public void thrust() {
        double newVelocityX = super.velocity.getX() + SHIP_MOVE * Math.cos(getPose().getHeading());
        double newVelocityY = super.velocity.getY() + SHIP_MOVE * Math.sin(getPose().getHeading());
        super.velocity = Vector2D.fromXY(newVelocityX, newVelocityY);
    }

    /**
     * This method updates the posiition of the ship.
     */
    public void update() {
        double speed = getVelocity().getMagnitude();
        if (speed > 0) {
            super.update();
            double newSpeed = Math.max(0, speed - FRICTION);
            super.velocity = super.velocity.newMagnitude(newSpeed);
            // super.velocity = super.velocity.newHeading(getVelocity().getHeading() * ratio);
        }
    }

    /**
     * This draws it.
     */
    public void draw() {
        GameUtils.drawPoseAsTriangle(getPose(), SHIP_WIDTH, SHIP_HEIGHT);
    }
}