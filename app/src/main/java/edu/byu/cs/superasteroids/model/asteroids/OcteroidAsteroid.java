package edu.byu.cs.superasteroids.model.asteroids;

import android.graphics.Rect;

/**
 * Created by Jon on 11/2/2016.
 */

public class OcteroidAsteroid extends Asteroid {
    public OcteroidAsteroid(Asteroid parent, float posX, float posY) {
        super(parent, posX, posY);
        setScaleX(parent.getScaleX()/8f);
        setScaleY(parent.getScaleY()/8f);
    }

    public OcteroidAsteroid(Asteroid asteroid) {
        super(asteroid);
    }
}
