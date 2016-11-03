package edu.byu.cs.superasteroids.model.asteroids;

import java.util.List;
import java.util.Set;

import edu.byu.cs.superasteroids.model.level.ViewPort;
import edu.byu.cs.superasteroids.model.ship.Laser;

/**
 * Created by Jon on 11/2/2016.
 */

public class GrowingAsteroid extends Asteroid {
    public GrowingAsteroid(Asteroid asteroid) {
        super(asteroid);
        this.setHealth(1);
    }

    @Override
    public void update(List<Asteroid> asteroids, Set<Laser> lasers) {
        super.update(asteroids, lasers);
        if((getScaleX() < 3f) && (getScaleY() < 3f)) {
            setScaleX(getScaleX() + .001f);
            setScaleY(getScaleY() + .001f);
        }
    }
}
