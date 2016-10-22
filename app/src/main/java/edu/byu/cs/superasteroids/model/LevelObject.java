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
    private float scale;

    public LevelObject(String position, float scale, int objectId) {
        this.position = position;
        this.scale = scale;
        this.objectId = objectId;
    }

    public LevelObject(JSONObject level) throws JSONException {
        this.position = level.getString("position");
        this.scale = level.getInt("scale");
        this.objectId = level.getInt("objectId");
    }

    public float getScale() {
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
