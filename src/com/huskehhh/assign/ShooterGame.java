package com.huskehhh.assign;

import com.huskehhh.assign.objects.Bullet;
import com.huskehhh.assign.objects.Enemy;
import com.huskehhh.assign.objects.Gun;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ListIterator;
import java.util.Random;

public class ShooterGame extends PApplet {

    /**
     * Declare instances of objects
     */
    public static ShooterGame instance;
    private static Gun gun;

    /**
     * Declare image objects for object creation
     */
    private PImage gunImage;
    private PImage enemyImage;
    private PImage heartsImage;
    private PImage backgroundImage;
    private PImage bulletImage;

    /**
     * Variable used in order to control the difficulty as the game progresses
     */
    private int level = 1;
    private int lives = 5;
    private int enemiesKilled = 0;

    /**
     * Variables used in order to control the drawing location of the heart
     */
    private int heartX = 1000;
    private int heartY = 600;

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
     * Compartmentalised method used in order to keep draw() clean.
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
     * Compartmentalised method used in order to keep draw() clean
     * This method is used in order to handle the movement of the enemies
     * <p>
     */
    private void handleLevelProgression() {
        ListIterator<Enemy> iterator = Enemy.enemies.listIterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            if (!enemy.canMove()) {
                if (lives >= 0) lives--;
                iterator.remove();
            }
            enemy.setY(enemy.getY() + enemy.getSpeed());
            enemy.drawObject();
        }
    }

    /**
     * Compartmentalised method used in order to keep draw() clean
     * This method is used in order to handle the movement of the bullets
     * <p>
     */
    private void handleBulletMovement() {
        ListIterator<Bullet> iterator = Bullet.bullets.listIterator();
        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();
            if (!bullet.canMove()) iterator.remove();
            bullet.setY(bullet.getY() - bullet.getSpeed());
            bullet.drawObject();
        }
    }

    /**
     * Compartmentalised method used in order to keep draw() clean
     * This method is used in order to handle the creation of enemies
     * <p>
     */
    private void handleEnemyDrawing() {
        if (Enemy.enemies.size() <= 0) {
            for (int i = 0; i <= random(level); i++) {
                new Enemy(random(1200), 1, Math.round((level / 5) + 1), enemyImage);
            }
        }
    }

    /**
     * Compartmentalised method used in order to keep draw() clean
     * This method is used in order to handle the collision of bullets on the enemies
     * <p>
     */
    private void handleBulletCollision() {
        if (Bullet.bullets.size() != 0) {
            ListIterator<Bullet> bullets = Bullet.bullets.listIterator();
            while (bullets.hasNext()) {
                Bullet bullet = bullets.next();
                if (bullet.checkCollision()) {
                    bullets.remove();
                    if (Enemy.enemies.size() == 0) {
                        if (lives < 5) lives++;
                        level++;
                        enemiesKilled++;
                    }
                }
            }
        }
    }

    /**
     * Compartmentalised method used in order to keep draw() clean
     * This method is used in order to handle the collision of the gun on the enemies
     * <p>
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
        PApplet.main("com.huskehhh.assign.ShooterGame");
    }

    /**
     * Method in order to determine a freshly generated random number per call
     *
     * @param bound boundary integer of the random output
     * @return randomised integer within 0-bound
     */
    private static int random(int bound) {
        return new Random().nextInt(bound);
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
