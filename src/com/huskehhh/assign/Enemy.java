package com.huskehhh.assign;

import processing.core.PImage;

import java.util.ArrayList;

public class Enemy {

    /**
     * Private variable declaration
     */
    private int x;
    private int y;
    private int speed;
    private int[] rgb;
    private int health;
    private PImage pimage;

    /**
     * List of all the enemies, used for checking collision on bullets
     */
    public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    /**
     * Constructor
     *
     * @param x     axis variable
     * @param y     axis variable
     * @param speed of the shark variable
     */
    public Enemy(int x, int y, int speed, int[] rgb, int health, PImage pimage) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.rgb = rgb;
        this.health = health;
        this.pimage = pimage;

        enemies.add(this);
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
     * Public getHealth return method
     *
     * @return health of the enemy
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Public getImage return method
     *
     * @return PImage object
     */
    public PImage getImage() {
        return this.pimage;
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
     * Public setRgb method used for altering the local rgb variable
     *
     * @param rgb = new rgb value
     */
    public void setRgb(int[] rgb) {
        this.rgb = rgb;
    }

    /**
     * Public setHealth method used for altering the health value of an enemy
     *
     * @param health
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Public setImage method used for changing the image of the enemy
     * @param pimage
     */
    public void setImage(PImage pimage) {
        this.pimage = pimage;
    }

    /**
     * Method to determine whether or not x and y values surpass the boundary
     *
     * @return whether or not both values are ok
     */
    public boolean canMove() {
        return getY() < 720;
    }


}
