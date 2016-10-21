package edu.byu.cs.superasteroids.model;

/**
 * Created by Jon on 10/10/2016.
 * Contains information describing the asteroids in a level
 */
public class LevelAsteroid {
    private int number;
    private int asteroidId;

    public LevelAsteroid(int number, int asteroidId) {
        this.number = number;
        this.asteroidId = asteroidId;
    }

    public LevelAsteroid(LevelAsteroid asteroid) {
        this.number = asteroid.getNumber();
        this.asteroidId = asteroid.getAsteroidId();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getAsteroidId() {
        return asteroidId;
    }

    public void setAsteroidId(int asteroidId) {
        this.asteroidId = asteroidId;
    }
}
