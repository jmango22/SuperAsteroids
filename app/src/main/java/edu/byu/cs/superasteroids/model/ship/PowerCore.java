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

    @Override
    public boolean equals(Object o) {
        return (((PowerCore) o).getImage().equals(this.image));
    }

    public int getCannonBoost() {
        return cannonBoost;
    }

    public int getEngineBoost() {
        return engineBoost;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String toString() {
        return "cannonBoost: "+getCannonBoost()+" engineBoost: "+getEngineBoost()+" image: "+getImage();
    }

}
