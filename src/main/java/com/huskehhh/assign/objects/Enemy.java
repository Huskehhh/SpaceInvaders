package com.huskehhh.assign.objects;

import lombok.Getter;
import lombok.Setter;
import processing.core.PImage;

import java.util.ArrayList;

public class Enemy implements GameObject {

    /**
     * Private variable declaration
     */
    @Getter
    @Setter
    private int x;
    @Getter
    @Setter
    private int y;
    @Getter
    @Setter
    private int speed;
    @Getter
    @Setter
    private PImage image;

    /**
     * List of all the enemies, used for checking collision on bullets
     */
    public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    /**
     * Constructor
     *
     * @param x      axis variable
     * @param y      axis variable
     * @param speed  of the shark variable
     * @param pimage variable
     */
    public Enemy(int x, int y, int speed, PImage pimage) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.image = pimage;

        enemies.add(this);

        drawObject();
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
