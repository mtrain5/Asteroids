import java.util.ArrayList;
import java.util.Iterator;

/**
 * Main class for Asteroids.
 * 
 * @author CS159 Instructors
 * @version s23
 */
public class AsteroidsGame implements Playable {

    public static final int LIVES = 3;
    private ArrayList<Drawable> drawElements;
    private ArrayList<Updatable> updateElements;
    private ArrayList<GameElement> shipAndBullets;

    private NumericDisplay score;
    private NumericDisplay lives;
    private Ship ship;
    private ArrayList<GameElement> enemies;

    /**
     * Constructs all game elements.
     */
    public AsteroidsGame() {
        StdDraw.setTitle("Generic Space Rock Shooter");

        drawElements = new ArrayList<>();
        updateElements = new ArrayList<>();
        enemies = new ArrayList<>();
        shipAndBullets = new ArrayList<>();

        // add background elements
        final int TOP = GameDriver.SCREEN_HEIGHT;
        score = new NumericDisplay(40, TOP - 20, "Score: ", 0);
        lives = new NumericDisplay(40, TOP - 40, "Lives: ", LIVES);
        score.draw();
        lives.draw();
        drawElements.add(score);
        drawElements.add(lives);
    }

    /**
     * Creates and adds 100 stars with random locations.
     */
    private void newStars() {
        Star s;
        for (int i = 0; i < 100; i++) {
            s = new Star(randomXPosition(), randomYPosition());
            drawElements.add(s);
        }
    }

    /**
     * Starts a new game with 10 asteroids.
     */
    public void startGame() {
        newStars();
        newShip();
        for (int i = 0; i < 10; i++) {
            newEnemies();
        }
        draw();
    }

    /**
     * Adds a new enemy to the game.
     * 
     * @param enemy object to add
     */
    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
        drawElements.add(enemy);
        updateElements.add(enemy);
    }

    /**
     * Starts the first level with 10 asteroids.
     */
    private void newEnemies() {
        Asteroid a = new Asteroid(AsteroidSize.randomSize());
        drawElements.add(a);
        updateElements.add(a);
        enemies.add(a);

    }

    /**
     * Starts the game with a ship.
     */
    private void newShip() {
        Ship s = new Ship();
        ship = s;
        shipAndBullets.add(ship);
        drawElements.add(ship);
        updateElements.add(ship);
    }

    /**
     * Detects collisions and destroys them.
     */
    public void handleCollisions() {
        for (int i = 0; i < shipAndBullets.size(); i++) {
            for (int j = 0; j < enemies.size(); j++) {
                GameElement element1 = shipAndBullets.get(i);
                GameElement element2 = enemies.get(j);
                if (element1.checkCollision(element2)) {
                    element1.setDestroyed(true);
                    element2.setDestroyed(true);
                    if (element1 instanceof Bullet) {
                        if (element2 instanceof Asteroid) {
                            int v = ((Asteroid) element2).getPoints();
                            score.setValue(score.getValue() + v);
                        } else if (element2 instanceof Saucer) {
                            int v = ((Saucer) element2).getPoints();
                            score.setValue(score.getValue() + v);
                        }

                    } else {
                        lives.setValue(lives.getValue() - 1);
                        shipAndBullets.remove(element1);
                        updateElements.remove(element1);
                        drawElements.remove(element1);
                        if (lives.getValue() >= 0) {
                            newShip();
                        }

                    }
                }
            }
        }
    }

    /**
     * Removes the bullets objects.
     */
    public void removeDestroyedBullets() {
        Iterator<GameElement> it = shipAndBullets.iterator();
        while (it.hasNext()) {
            GameElement b = it.next();
            if (b instanceof Bullet) {
                if (b.isDestroyed()) {
                    it.remove();
                    updateElements.remove(b);
                    drawElements.remove(b);
                }
            }
        }
    }

    /**
     * Removes the enemy objects.
     */
    public void removeDestroyedEnemies() {
        Iterator<GameElement> it = enemies.iterator();
        while (it.hasNext()) {
            GameElement b = it.next();
            if (b instanceof Asteroid || b instanceof Saucer) {
                if (b.isDestroyed()) {
                    it.remove();
                    updateElements.remove(b);
                    drawElements.remove(b);

                }
            }
        }
    }

    /**
     * Handle keyboard input from the game and move the ship and shoot bullets if the corresponding keys are pressed.
     */
    private void handleKeyboardInput() {
        if (GameDriver.spacePressed()) {
            Bullet newBullet = new Bullet(ship.getPose());
            shipAndBullets.add(newBullet);
            drawElements.add(newBullet);
            updateElements.add(newBullet);
        }

        if (GameDriver.leftPressed()) {
            for (GameElement element : shipAndBullets) {
                if (element instanceof Ship) {
                    ((Ship) element).turnLeft();
                }
            }
        }

        if (GameDriver.rightPressed()) {
            for (GameElement element : shipAndBullets) {
                if (element instanceof Ship) {
                    ((Ship) element).turnRight();
                }
            }
        }

        if (GameDriver.upPressed()) {
            for (GameElement element : shipAndBullets) {
                if (element instanceof Ship) {
                    ((Ship) element).thrust();
                }
            }
        }

    }

    @Override
    public void update() {
        // freeze simulation if game is over
        if (lives.getValue() <= 0) {
            return;
        }

        // update everything (including newest bullet)
        handleKeyboardInput();
        for (Updatable item : updateElements) {
            item.update();

        }

        // TODO You will need these in Part 3
        handleCollisions();
        removeDestroyedBullets();
        removeDestroyedEnemies();

        if (Math.random() < Saucer.SPAWN_PROB) {
            Saucer saucer = new Saucer();
            addEnemy(saucer);
        }

        if (enemies.isEmpty()) {
            for (int i = 0; i < 10; i++) {
                newEnemies();
            }
        }
    }

    @Override
    public void draw() {
        for (int i = 0; i < drawElements.size(); i++) {
            drawElements.get(i).draw();
        }

    }

    /**
     * Generates a random X position on the screen.
     * 
     * @return the x position
     */
    protected static double randomXPosition() {
        return GameDriver.GENERATOR.nextDouble() * GameDriver.SCREEN_WIDTH;
    }

    /**
     * Generates a random Y position on the screen.
     * 
     * @return the y position
     */
    protected static double randomYPosition() {
        return GameDriver.GENERATOR.nextDouble() * GameDriver.SCREEN_HEIGHT;
    }

    /**
     * Generates a random heading from the interval [0, 2*PI).
     * 
     * @return the heading
     */
    protected static double randomHeading() {
        return GameDriver.GENERATOR.nextDouble() * 2 * Math.PI;
    }

}