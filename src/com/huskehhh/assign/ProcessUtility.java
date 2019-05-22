package com.huskehhh.assign;

import java.util.Random;

public class ProcessUtility {

    // PApplet object instance allows us to call Processing methods from another class
    private static ShooterGame instance = ShooterGame.instance;

    /**
     * Method to handle the Processing hook for the Enemy object
     *
     * @param enemy parsed Enemy object
     */
    public static void enemyHook(Enemy enemy) {

        int rgb[] = enemy.getRgb();

        instance.fill(rgb[0], rgb[1], rgb[2]);
        instance.image(enemy.getImage(), enemy.getX(), enemy.getY());
    }

    /**
     * Method to handle the Processing hook for the Bullet object
     *
     * @param bullet parsed Bullet object
     */
    public static void bulletHook(Bullet bullet) {

        int[] rgb = bullet.getRgb();

        instance.fill(rgb[0], rgb[1], rgb[2]);
        instance.ellipse(bullet.getX(), bullet.getY(), 50, 50);
    }

    /**
     * Method to handle the Processing hook for the Gun object
     *
     * @param gun parsed Gun object
     */
    public static void gunHook(Gun gun) {
        int[] rgb = gun.getRgb();

        instance.fill(rgb[0], rgb[1], rgb[2]);
        instance.image(gun.getImage(), gun.getX(), gun.getY());
    }

    /**
     * 
     * @param x value to check
     * @param y value to check
     * @param x1 value to check
     * @param y1 value to check
     * @param distance permitted distance between the two points
     * @return whether distance is acceptable
     */
    public static boolean isNear(int x, int y, int x1, int y1, int distance) {
        return Math.sqrt((x1 - x) * (x1 - x) + (y1 - y) * (y1 - y)) < distance;
    }

    /**
     * Method in order to determine a freshly generated random number per call
     *
     * @param bound boundary integer of the random output
     * @return randomised integer within 0-bound
     */
    public static int random(int bound) {
        return new Random().nextInt(bound);
    }

}
