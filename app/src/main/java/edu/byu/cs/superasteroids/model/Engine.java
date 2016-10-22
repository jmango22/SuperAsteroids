package edu.byu.cs.superasteroids.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Jon on 10/10/2016.
 */
public class Engine {
    private int base_speed;
    private int base_turn_rate;
    private String attach_point;
    private String image;
    private int imageWidth;
    private int imageHeight;

    public Engine(int base_speed, int base_turn_rate, String attach_point, String image, int imageWidth, int imageHeight) {
        this.base_speed = base_speed;
        this.base_turn_rate = base_turn_rate;
        this.attach_point = attach_point;
        this.image = image;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }

    public Engine(JSONObject engine) throws JSONException {
        this.base_speed = engine.getInt("baseSpeed");
        this.base_turn_rate = engine.getInt("baseTurnRate");
        this.attach_point = engine.getString("attachPoint");
        this.image = engine.getString("image");
        this.imageWidth = engine.getInt("imageWidth");
        this.imageHeight = engine.getInt("imageHeight");
    }

    public int getBase_speed() {
        return base_speed;
    }

    public void setBase_speed(int base_speed) {
        this.base_speed = base_speed;
    }

    public int getBase_turn_rate() {
        return base_turn_rate;
    }

    public void setBase_turn_rate(int base_turn_rate) {
        this.base_turn_rate = base_turn_rate;
    }

    public String getAttach_point() {
        return attach_point;
    }

    public void setAttach_point(String attach_point) {
        this.attach_point = attach_point;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }
}
