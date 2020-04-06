package pro.husk.spaceinvaders.objects;

import pro.husk.spaceinvaders.ShooterGame;
import processing.core.PImage;

public interface GameObject {

    int getX();

    int getY();

    PImage getImage();

    void setX(int x);

    void setY(int y);

    /**
     * This method is used in order to handle the collision of bullets on the enemies
     *
     * @return whether or not bullet has collided with enemy
     */
    default boolean checkCollision() {
        for (Enemy enemy : Enemy.getEnemies()) {
            if (ShooterGame.isNear(getX(), getY(), enemy.getX(), enemy.getY(), 40)) {
                Enemy.getEnemies().remove(enemy);
                return true;
            }
        }
        return false;
    }

    /**
     * Method to handle the drawing of the object using the instance of the ShooterGame (Processing hook)
     */
    default void drawObject() {
        ShooterGame.instance.image(getImage(), getX(), getY());
    }

    /**
     * Default canMove() method, implemented individually.
     *
     * @return true if unimplemented by child
     */
    default boolean canMove() {
        return true;
    }

    /**
     * Method used to delete respective object from tracked list
     */
    void delete();
}