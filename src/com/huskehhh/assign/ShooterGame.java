package com.huskehhh.assign;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ListIterator;

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

    /**
     * Variable used in order to control the difficulty as the game progresses
     */
    private static int level = 1;
    public static int lives = 2;

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
        backgroundImage = loadImage("images/background.png");

        instance = this;
    }

    /**
     * Default draw() method called by Processing automatically
     */
    public void draw() {
        // Set background
        noStroke();
        background(backgroundImage);

        // Call compartmentalised methods
        if (lives > 0) {
            handleShooter();
            handleLevelProgression();
            handleEnemyDrawing();
            handleBulletCollision();
            handleBulletMovement();
            updateLevelText();
        } else {
            background(0);
            fill(255, 0, 0);
            textSize(100);
            textAlign(CENTER);
            text("Game over", 640, 360);
        }

        updateHearts();
    }

    /**
     * Default method used to handle Mouse input that is called by Processing automatically.
     */
    public void mousePressed() {
        if (mousePressed) {
            int[] rgb = {100, 100, 100};
            ProcessUtility.bulletHook(new Bullet(gun.getX() + 30, gun.getY(), level * 2, rgb));
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
            ProcessUtility.gunHook(gun);
        } else {
            int[] rgb = {200, 200, 200};
            gun = new Gun(mouseX, mouseY, 5, rgb, gunImage);
        }
    }

    /**
     * Compartmentalised method used in order to keep draw() clean
     * This method is used in order to handle the movement of the enemies
     * <p>
     */
    private void handleLevelProgression() {

        if (Integer.numberOfTrailingZeros(gun.getDamage() * level) <= 1) {
            gun.setDamage(gun.getDamage() + 1);
        }

        ListIterator<Enemy> iterator = Enemy.enemies.listIterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            if (!enemy.canMove()) {
                if (lives >= 0) lives--;
                iterator.remove();
            }
            enemy.setY(enemy.getY() + enemy.getSpeed());
            ProcessUtility.enemyHook(enemy);
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
            ProcessUtility.bulletHook(bullet);
        }
    }

    /**
     * Compartmentalised method used in order to keep draw() clean
     * This method is used in order to handle the creation of enemies
     * <p>
     */
    private void handleEnemyDrawing() {
        if (Enemy.enemies.size() <= 0) {
            int[] rgb = {50, 50, 50};
            for (int i = 0; i <= ProcessUtility.random(level); i++) {
                ProcessUtility.enemyHook(new Enemy(ProcessUtility.random(1200), 5, (level / 5) + 1, rgb, ProcessUtility.random(level), enemyImage));
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

            ListIterator<Enemy> enemies = Enemy.enemies.listIterator();
            ListIterator<Bullet> bullets = Bullet.bullets.listIterator();


            while (bullets.hasNext()) {
                Bullet bullet = bullets.next();
                while (enemies.hasNext()) {
                    Enemy enemy = enemies.next();

                    if (ProcessUtility.isNear(bullet.getX(), bullet.getY(), enemy.getX(), enemy.getY(), 70)) {

                        enemies.remove();
                        bullets.remove();

                        if (lives < 5) lives++;

                        if(Enemy.enemies.size() == 0) level++;

                        break;
                    }
                }

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


}
