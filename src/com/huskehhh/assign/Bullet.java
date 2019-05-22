package com.huskehhh.assign;

import java.util.ArrayList;
import java.util.ListIterator;

public class Bullet {

    /**
     * Declare private variables
     */
    private int x;
    private int y;
    private int speed;
    private int[] rgb;

    /**
     * List of all the bullets, used for checking collision on enemies
     */
    public static ArrayList<Bullet> bullets = new ArrayList<Bullet>();

    /**
     * Constructor
     *
     * @param x
     * @param y
     * @param speed
     */
    public Bullet(int x, int y, int speed, int[] rgb) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.rgb = rgb;

        bullets.add(this);
    }

    /**
     * Public getX return method
     *
     * @return x axis value
     */
    public int getX() {
        return this.x;
    }

    /**
     * Public getY return method
     *
     * @return y axis value
     */
    public int getY() {
        return this.y;
    }

    /**
     * Public getSpeed return method
     *
     * @return speed variable value
     */
    public int getSpeed() {
        return this.speed;
    }

    /**
     * Public getRgb return method
     *
     * @return rgb value in array form
     */
    public int[] getRgb() {
        return this.rgb;
    }

    /**
     * Public setX method used for altering the local x variable
     *
     * @param x = new x value
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Public setX method used for altering the local x variable
     *
     * @param y = new y value
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Public setSpeed method
     *
     * @param speed = new speed value
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Public setRgb method used for altering the local rgb variable
     *
     * @param rgb = new rgb value
     */
    public void setRgb(int[] rgb) {
        this.rgb = rgb;
    }

    /**
     * Method to determine whether or not x and y values surpass the boundary
     *
     * @return whether or not both values are ok
     */
    public boolean canMove() {
        return y - speed > 0;
    }

    /**
     * This method is used in order to handle the collision of bullets on the enemies
     *
     * @return whether or not bullet has collided with enemy
     */
    public boolean checkCollision() {
        ListIterator<Enemy> enemies = Enemy.enemies.listIterator();

        while (enemies.hasNext()) {
            Enemy enemy = enemies.next();
            if (ProcessUtility.isNear(getX(), getY(), enemy.getX(), enemy.getY(), 50)) {
                enemies.remove();
                return true;
            }
        }
        return false;
    }
}
