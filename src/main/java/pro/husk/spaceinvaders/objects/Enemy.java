package pro.husk.spaceinvaders.objects;

import lombok.Getter;
import lombok.Setter;
import pro.husk.spaceinvaders.ShooterGame;
import processing.core.PImage;

import java.util.ArrayList;

public class Enemy implements GameObject {

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
    @Getter
    private static final ArrayList<Enemy> enemies = new ArrayList<Enemy>();

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

    @Override
    public void delete() {
        enemies.remove(this);
    }

    @Override
    public void tick() {
        if (!canMove()) {
            int lives = ShooterGame.getInstance().getLives();
            if (lives >= 0) ShooterGame.getInstance().setLives(lives - 1);
            delete();
        }
        setY(getY() + getSpeed());
        drawObject();
    }
}