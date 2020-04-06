package pro.husk.spaceinvaders;

import pro.husk.spaceinvaders.objects.Bullet;
import pro.husk.spaceinvaders.objects.Enemy;
import pro.husk.spaceinvaders.objects.Gun;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.concurrent.ThreadLocalRandom;

public class ShooterGame extends PApplet {

    // Local instances
    public static ShooterGame instance;
    private static Gun gun;

    /**
     * Image objects
     */
    private PImage gunImage;
    private PImage enemyImage;
    private PImage heartsImage;
    private PImage backgroundImage;
    private PImage bulletImage;

    /**
     * Variables used in order to control the difficulty as the game progresses
     */
    private int level = 1;
    private int lives = 5;
    private int enemiesKilled = 0;

    /**
     * Default setup/settings method called by Processing automatically
     */
    public void settings() {
        size(1280, 720);

        // Load images
        gunImage = loadImage("images/gun.png");
        enemyImage = loadImage("images/enemy.png");
        heartsImage = loadImage("images/heart.png");
        backgroundImage = loadImage("images/background.jpg");
        bulletImage = loadImage("images/bullet.png");

        instance = this;
    }

    /**
     * Default draw() method called by Processing automatically
     */
    public void draw() {
        // Set background
        noStroke();
        background(backgroundImage);
        noCursor();


        // Call compartmentalised methods
        if (lives > 0) {
            handleShooter();
            handleLevelProgression();
            handleEnemyDrawing();
            handleBulletCollision();
            handleBulletMovement();
            handleGunEnemyCollision();
            updateLevelText();
        } else {
            background(0);
            fill(255, 0, 0);
            textSize(100);
            textAlign(CENTER);
            text("Game over", 640, 360);
            textSize(25);
            fill(7, 235, 205);
            text("Level: " + level, 640, 500);
            text("Enemies killed: " + enemiesKilled, 640, 550);
        }

        updateHearts();
    }

    /**
     * Default method used to handle Mouse input that is called by Processing automatically.
     */
    public void mousePressed() {
        if (mousePressed) {
            new Bullet(gun.getX(), gun.getY(), level * 2, bulletImage).drawObject();
        }
    }

    /**
     * This method handles the creation and interaction of the shooter character
     */
    private void handleShooter() {
        if (mouseY < 500) mouseY = 500;
        if (gun != null) {
            gun.setX(mouseX);
            gun.setY(mouseY);
            gun.drawObject();
        } else {
            gun = new Gun(mouseX, mouseY, gunImage);
        }
    }

    /**
     * This method is used in order to handle the movement of the enemies
     */
    private void handleLevelProgression() {
        for (Enemy enemy : Enemy.getEnemies()) {
            if (!enemy.canMove()) {
                if (lives >= 0) lives--;
                Enemy.getEnemies().remove(enemy);
            }
            enemy.setY(enemy.getY() + enemy.getSpeed());
            enemy.drawObject();
        }
    }

    /**
     * This method is used in order to handle the movement of the bullets
     */
    private void handleBulletMovement() {
        for (Bullet bullet : Bullet.getBullets()) {
            if (!bullet.canMove()) Bullet.getBullets().remove(bullet);
            bullet.setY(bullet.getY() - bullet.getSpeed());
            bullet.drawObject();
        }
    }

    /**
     * This method is used in order to handle the creation of enemies
     */
    private void handleEnemyDrawing() {
        if (Enemy.getEnemies().size() <= 0) {
            for (int i = 0; i <= random(level); i++) {
                new Enemy(random(1200), 1, Math.round((level / 5) + 1), enemyImage);
            }
        }
    }

    /**
     * This method is used in order to handle the collision of bullets on the enemies
     */
    private void handleBulletCollision() {
        if (Bullet.getBullets().size() != 0) {
            for (Bullet bullet : Bullet.getBullets()) {
                if (bullet.checkCollision()) {
                    enemiesKilled++;
                    if (Enemy.getEnemies().size() == 0) {
                        if (lives < 5) lives++;
                        level++;
                    }
                }
            }
        }
    }

    /**
     * This method is used in order to handle the collision of the gun on the enemies
     */
    private void handleGunEnemyCollision() {
        if (gun != null) {
            if (gun.checkCollision()) {
                lives--;
            }
        }
    }

    /**
     * Method to update the hearts on the screen
     */
    private void updateHearts() {
        int heartX = 1000;
        int heartY = 600;

        for (int i = 0; i < lives; i++) {
            image(heartsImage, heartX + (50 * i), heartY);
        }
    }

    /**
     * Method to update the level text on the screen
     */
    private void updateLevelText() {
        fill(0, 255, 255);
        textSize(25);
        textAlign(LEFT);
        text("Level: " + level, 50, 50);
    }

    /**
     * Default main method
     */
    public static void main(String[] args) {
        PApplet.main("pro.husk.spaceinvaders.ShooterGame");
    }

    /**
     * Method in order to determine a freshly generated random number per call
     *
     * @param bound boundary integer of the random output
     * @return randomised integer within 0-bound
     */
    private static int random(int bound) {
        return ThreadLocalRandom.current().nextInt(bound);
    }

    /**
     * @param x        value to check
     * @param y        value to check
     * @param x1       value to check
     * @param y1       value to check
     * @param distance permitted distance between the two points
     * @return whether distance is acceptable
     */
    public static boolean isNear(int x, int y, int x1, int y1, int distance) {
        return Math.sqrt((x1 - x) * (x1 - x) + (y1 - y) * (y1 - y)) < distance;
    }
}