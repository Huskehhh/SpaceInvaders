package com.huskehhh.assign;

import processing.core.PImage;

public class Gun {

    /**
     * Private variable declaration
     */
    private int x;
    private int y;
    private int damage;
    private int[] rgb;
    private PImage pimage;

    /**
     * Constructor
     *
     * @param x axis variable
     * @param y axis variable
     */
    public Gun(int x, int y, int damage, int[] rgb, PImage pimage) {
        this.x = x;
        this.y = y;
        this.damage = damage;
        this.rgb = rgb;
        this.pimage = pimage;

        ProcessUtility.gunHook(this);
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
     * Public getDamage return method
     *
     * @return damage value
     */
    public int getDamage() {
        return this.damage;
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
     * Public setRgb method used for altering the local rgb variable
     *
     * @param rgb = new rgb value
     */
    public void setRgb(int[] rgb) {
        this.rgb = rgb;
    }

    /**
     * Public setImage method used for changing the image of the gun
     *
     * @param pimage
     */
    public void setImage(PImage pimage) {
        this.pimage = pimage;
    }

    /**
     * Public setDamage method used for changing the damage variable
     *
     * @param damage = new damage value
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

}
