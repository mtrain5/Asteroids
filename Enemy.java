/**
 * The enemy class.
 * 
 * @author marcus tran
 * @version 4/12/23
 */
abstract class Enemy extends GameElement {
    protected int points;

    /**
     * Creates a new enemy.
     * 
     * @param speed how fast it is moving
     * @param radius the radius
     * @param points point value
     */
    public Enemy(double speed, double radius, int points) {
        super(new Pose(GameDriver.GENERATOR.nextDouble() * GameDriver.SCREEN_WIDTH,
                GameDriver.GENERATOR.nextDouble() * GameDriver.SCREEN_HEIGHT, 0),
                new Vector2D(Math.random() * 360, speed), radius);
        this.points = points;
    }

    /**
     * Returns the points.
     * 
     * @return points the point value.
     */
    public int getPoints() {
        return points;
    }
}