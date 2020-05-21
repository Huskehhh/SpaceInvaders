package pro.husk.spaceinvaders.objects;

import lombok.Getter;
import lombok.Setter;
import processing.core.PImage;

import java.util.ArrayList;

public class Bullet implements GameObject {

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
     * List of all the bullets, used for checking collision on enemies
     */
    @Getter
    private static final ArrayList<Bullet> bullets = new ArrayList<Bullet>();

    /**
     * Constructor
     *
     * @param x      axis variable
     * @param y      axis variable
     * @param speed  variable
     * @param pimage variable
     */
    public Bullet(int x, int y, int speed, PImage pimage) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.image = pimage;

        bullets.add(this);
    }

    /**
     * Method to determine whether or not x and y values surpass the boundary
     *
     * @return whether or not both values are ok
     */
    @Override
    public boolean canMove() {
        return y - speed > 0;
    }

    @Override
    public void delete() {
        bullets.remove(this);
    }

    @Override
    public void tick() {
        if (!canMove()) {
            delete();
        } else {
            setY(getY() - getSpeed());
            drawObject();
        }
    }
}