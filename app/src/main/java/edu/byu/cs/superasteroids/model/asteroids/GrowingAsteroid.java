package edu.byu.cs.superasteroids.model.asteroids;

import edu.byu.cs.superasteroids.model.level.ViewPort;

/**
 * Created by Jon on 11/2/2016.
 */

public class GrowingAsteroid extends Asteroid {
    public GrowingAsteroid(String name, String image, int imageWidth, int imageHeight, String type) {
        super(name, image, imageWidth, imageHeight, type);
        this.setHealth(1);
    }

    @Override
    public void update() {
        if((getPosX() > ViewPort.getWorldWidth()) || (getPosX() < 0)) {
            setVelX(getVelX()*-1);
        }
        if((getPosY()> ViewPort.getWorldHeight()) || (getPosY() < 0)) {
            setVelY(getVelY()*-1);
        }
        setScaleX(getScaleX()*1.01f);
        setScaleY(getScaleY()*1.01f);
    }
}
