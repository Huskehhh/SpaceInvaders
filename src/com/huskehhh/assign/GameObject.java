package com.huskehhh.assign;

import processing.core.PImage;

import java.util.ListIterator;

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

    default void drawObject() {
        ShooterGame.instance.image(getImage(), getX(), getY());
    }

    default boolean canMove() {
        return true;
    }

}
