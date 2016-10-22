package edu.byu.cs.superasteroids.model;

import org.json.JSONException;
import org.json.JSONObject;

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

    public PowerCore(JSONObject core) throws JSONException{
        this.cannonBoost = core.getInt("cannonBoost");
        this.engineBoost = core.getInt("engineBoost");
        this.image = core.getString("image");
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
