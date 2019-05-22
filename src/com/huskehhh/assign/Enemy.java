package com.huskehhh.assign;

import processing.core.PImage;

import java.util.ArrayList;

public class Enemy implements GameObject {

    /**
     * Private variable declaration
     */
    private int x;
    private int y;
    private int speed;
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
     * @param pimage variable
     */
    public Enemy(int x, int y, int speed, PImage pimage) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.pimage = pimage;

        enemies.add(this);

        drawObject();
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
     * Public setImage method used for changing the image of the enemy
     *
     * @param pimage = new image
     */
    public void setImage(PImage pimage) {
        this.pimage = pimage;
    }

    /**
     * Method to determine whether or not x and y values surpass the boundary
     *
     * @return whether or not both values are ok
     */
    @Override
    public boolean canMove() {
        return getY() < 720;
    }


}
