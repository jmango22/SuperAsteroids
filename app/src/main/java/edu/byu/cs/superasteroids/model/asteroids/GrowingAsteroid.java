package edu.byu.cs.superasteroids.model.asteroids;

import android.graphics.Rect;

import java.util.List;
import java.util.Set;

import edu.byu.cs.superasteroids.model.level.ViewPort;
import edu.byu.cs.superasteroids.model.ship.Laser;

/**
 * Created by Jon on 11/2/2016.
 */

public class GrowingAsteroid extends Asteroid {
    public GrowingAsteroid(Asteroid parent, float posX, float posY) {
        super(parent, posX, posY);
    }

    public GrowingAsteroid(Asteroid asteroid) {
        super(asteroid);
    }

    @Override
    public void update() {
        super.update();
        if((getScaleX() < 3f) && (getScaleY() < 3f)) {
            setScaleX(getScaleX() + .001f);
            setScaleY(getScaleY() + .001f);
        }
    }

}
