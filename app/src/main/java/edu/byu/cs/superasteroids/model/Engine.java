package edu.byu.cs.superasteroids.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Jon on 10/10/2016.
 */
public class Engine extends Model {
    private int base_speed;
    private int base_turn_rate;
    private String attach_point;

    public Engine(int base_speed, int base_turn_rate, String attach_point, String image, int imageWidth, int imageHeight) {
        setBase_speed(base_speed);
        setBase_turn_rate(base_turn_rate);
        setAttach_point(attach_point);
        setImage(image);
        setImageWidth(imageWidth);
        setImageHeight(imageHeight);
    }

    public Engine(JSONObject engine) throws JSONException {
        setBase_speed(engine.getInt("baseSpeed"));
        setBase_turn_rate(engine.getInt("baseTurnRate"));
        setAttach_point( engine.getString("attachPoint"));
        setImage(engine.getString("image"));
        setImageWidth(engine.getInt("imageWidth"));
        setImageHeight(engine.getInt("imageHeight"));
    }

    public String toString() {
        return "baseSpeed: "+getBase_speed()+" baseTurnRate: "+getBase_turn_rate()+" attachPoint: "+getAttach_point()+" image: "+getImage()+" imageWidth: "+getImageWidth()+
                " imageHeight: "+getImageHeight();
    }
    // Getters and Setters
    public int getBase_speed() { return base_speed; }

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
}