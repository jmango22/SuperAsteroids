package edu.byu.cs.superasteroids.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Jon on 10/10/2016.
 * Contains information describing a level background object
 */
public class LevelObject {
    private String position;
    private int objectId;
    private double scale;

    public LevelObject(String position, double scale, int objectId) {
        this.position = position;
        this.scale = scale;
        this.objectId = objectId;

        System.out.print("LevelObject Created!\t");
    }

    public LevelObject(JSONObject level) throws JSONException {
        this.position = level.getString("position");
        this.scale = level.getDouble("scale");
        this.objectId = level.getInt("objectId");
    }

    public double getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
