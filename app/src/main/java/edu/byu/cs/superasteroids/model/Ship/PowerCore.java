package edu.byu.cs.superasteroids.model.ship;

import org.json.JSONException;
import org.json.JSONObject;

import edu.byu.cs.superasteroids.content.ContentManager;

/**
 * Created by Jon on 10/10/2016.
 * Contains information describing a power core part of the ship
 */
public class PowerCore {
    private int cannonBoost;
    private int engineBoost;
    private String image;

    private int imageId;

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

    public void loadImage() {
        imageId = ContentManager.getInstance().loadImage(image);
    }

    public int getImageId() {
        return imageId;
    }

    public void unloadImage() {
        ContentManager.getInstance().unloadImage(imageId);
        imageId = -1;
    }

    public String toString() {
        return "cannonBoost: "+getCannonBoost()+" engineBoost: "+getEngineBoost()+" image: "+getImage();
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof PowerCore)) {
            return false;
        }
        else if(!(((PowerCore) o).getImage().equals(this.image))) {
            return false;
        }
        else {
            return true;
        }
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
