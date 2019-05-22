package com.huskehhh.assign;

import processing.core.PImage;

public class Gun implements GameObject {

    /**
     * Private variable declaration
     */
    private int x;
    private int y;
    private PImage pimage;

    /**
     * Constructor
     *
     * @param x axis variable
     * @param y axis variable
     * @param pimage variable
     */
    public Gun(int x, int y, PImage pimage) {
        this.x = x;
        this.y = y;
        this.pimage = pimage;

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
     * Public getImage return method
     *
     * @return PImage object
     */
    public PImage getImage() {
        return this.pimage;
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

}
