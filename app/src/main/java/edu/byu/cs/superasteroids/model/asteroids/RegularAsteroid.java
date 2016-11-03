package edu.byu.cs.superasteroids.model.asteroids;

import android.graphics.Rect;

/**
 * Created by Jon on 11/2/2016.
 */

public class RegularAsteroid extends Asteroid {
    public RegularAsteroid(Asteroid parent, float posX, float posY) {
        super(parent, posX, posY);
    }

    public RegularAsteroid(Asteroid asteroid) {
        super(asteroid);
    }

}
