package pro.husk.spaceinvaders.objects;

import lombok.Getter;
import lombok.Setter;
import pro.husk.spaceinvaders.ShooterGame;
import processing.core.PImage;

public class Gun implements GameObject {

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

    @Override
    public void delete() {
        // do nothing
    }

    @Override
    public void tick() {
        // do nothing
    }
}