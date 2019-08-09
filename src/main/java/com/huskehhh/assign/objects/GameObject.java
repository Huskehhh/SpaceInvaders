package com.huskehhh.assign.objects;

import com.huskehhh.assign.ShooterGame;
import processing.core.PImage;

import java.util.ListIterator;

public interface GameObject {

    /**
     * Public getX return method
     *
     * @return x axis value
     */
    int getX();

    /**
     * Public getY return method
     *
     * @return y axis value
     */
    int getY();

    /**
     * Public getImage return method
     *
     * @return PImage object
     */
    PImage getImage();

    /**
     * Public setX method used for altering the local x variable
     *
     * @param x = new x value
     */
    void setX(int x);

    /**
     * Public setX method used for altering the local x variable
     *
     * @param y = new y value
     */
    void setY(int y);


    /**
     * This method is used in order to handle the collision of bullets on the enemies
     *
     * @return whether or not bullet has collided with enemy
     */
    default boolean checkCollision() {
        ListIterator<Enemy> enemies = Enemy.enemies.listIterator();

        while (enemies.hasNext()) {
            Enemy enemy = enemies.next();
            if (ShooterGame.isNear(getX(), getY(), enemy.getX(), enemy.getY(), 40)) {
                enemies.remove();
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
     * @return true if unimplemented by child
     */
    default boolean canMove() {
        return true;
    }

}
