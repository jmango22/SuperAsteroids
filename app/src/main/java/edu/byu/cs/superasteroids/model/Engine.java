package edu.byu.cs.superasteroids.model;

import org.json.JSONException;
import org.json.JSONObject;

import edu.byu.cs.superasteroids.content.ContentManager;

/**
 * Created by Jon on 10/10/2016.
 */
public class Engine extends Model {
    private int baseSpeed;
    private int baseTurnRate;
    private String attachPoint;

    public Engine(int baseSpeed, int baseTurnRate, String attachPoint, String image, int imageWidth, int imageHeight) {
        setBaseSpeed(baseSpeed);
        setBaseTurnRate(baseTurnRate);
        setAttachPoint(attachPoint);
        setImage(image);
        setImageWidth(imageWidth);
        setImageHeight(imageHeight);
    }

    public Engine(JSONObject engine) throws JSONException {
        setBaseSpeed(engine.getInt("baseSpeed"));
        setBaseTurnRate(engine.getInt("baseTurnRate"));
        setAttachPoint( engine.getString("attachPoint"));
        setImage(engine.getString("image"));
        setImageWidth(engine.getInt("imageWidth"));
        setImageHeight(engine.getInt("imageHeight"));
    }

    public String toString() {
        return "baseSpeed: "+getBaseSpeed()+" baseTurnRate: "+getBaseTurnRate()+" attachPoint: "+getAttachPoint()+" image: "+getImage()+" imageWidth: "+getImageWidth()+
                " imageHeight: "+getImageHeight();
    }
    // Getters and Setters
    public int getBaseSpeed() { return baseSpeed; }

    public void setBaseSpeed(int baseSpeed) {
        this.baseSpeed = baseSpeed;
    }

    public int getBaseTurnRate() {
        return baseTurnRate;
    }

    public void setBaseTurnRate(int baseTurnRate) {
        this.baseTurnRate = baseTurnRate;
    }

    public String getAttachPoint() {
        return attachPoint;
    }

    public void setAttachPoint(String attach_point) {
        this.attachPoint = attachPoint;
    }
}