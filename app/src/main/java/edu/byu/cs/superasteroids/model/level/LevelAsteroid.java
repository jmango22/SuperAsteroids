package edu.byu.cs.superasteroids.model.level;

import org.json.JSONException;
import org.json.JSONObject;

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

        //System.out.print("LevelAsteroid Created!\t");
    }

    public LevelAsteroid(JSONObject asteroid) throws JSONException {
        this.number = asteroid.getInt("number");
        this.asteroidId = asteroid.getInt("asteroidId");
    }

    public int getNumber() {
        return number;
    }

    public int getAsteroidId() {
        return asteroidId;
    }

}
