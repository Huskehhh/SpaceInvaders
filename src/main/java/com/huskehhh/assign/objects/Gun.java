package com.huskehhh.assign.objects;

import lombok.Getter;
import lombok.Setter;
import processing.core.PImage;

public class Gun implements GameObject {

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
    private PImage image;

    /**
     * Constructor
     *
     * @param x      axis variable
     * @param y      axis variable
     * @param pimage variable
     */
    public Gun(int x, int y, PImage pimage) {
        this.x = x;
        this.y = y;
        this.image = pimage;

        drawObject();
    }
}
