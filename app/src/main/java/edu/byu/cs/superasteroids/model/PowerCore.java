package edu.byu.cs.superasteroids.model;

/**
 * Created by Jon on 10/10/2016.
 * Contains information describing a power core part of the ship
 */
public class PowerCore {
    private int cannonBoost;
    private int engineBoost;
    private String image;

    public PowerCore(int cannonBoost, int engineBoost, String image) {
        this.cannonBoost = cannonBoost;
        this.engineBoost = engineBoost;
        this.image = image;
    }

    public PowerCore(PowerCore core) {
        this.cannonBoost = core.getCannonBoost();
        this.engineBoost = core.getEngineBoost();
        this.image = core.getImage();
    }

    public int getCannonBoost() {
        return cannonBoost;
    }

    public void setCannonBoost(int cannonBoost) {
        this.cannonBoost = cannonBoost;
    }

    public int getEngineBoost() {
        return engineBoost;
    }

    public void setEngineBoost(int engineBoost) {
        this.engineBoost = engineBoost;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
