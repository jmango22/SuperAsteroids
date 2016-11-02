package edu.byu.cs.superasteroids.model.level;

import android.graphics.PointF;

import org.json.JSONException;
import org.json.JSONObject;

import edu.byu.cs.superasteroids.content.ContentManager;
import edu.byu.cs.superasteroids.database.SuperAsteroids_DAO;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;

/**
 * Created by Jon on 10/10/2016.
 * Contains information describing a level background object
 */
public class LevelObject {
    private String position;
    private int objectId;
    private double scale;
    private BackgroundObject bgObject;

    public LevelObject(String position, double scale, int objectId) {
        this.position = position;
        this.scale = scale;
        this.objectId = objectId;

        this.bgObject = SuperAsteroids_DAO.getInstance().getBackgroundObject(objectId);
    }

    public LevelObject(JSONObject level) throws JSONException {
        this.position = level.getString("position");
        this.scale = level.getDouble("scale");
        this.objectId = level.getInt("objectId");
    }

    public void loadImage(ContentManager content) {
        bgObject.loadImage(content);
    }

    public void unloadImage(ContentManager content) {
        bgObject.unloadImage(content);
    }

    public void draw() {
        String[] drawPosition = position.split(",");
        PointF drawPoint = new PointF(Float.parseFloat(drawPosition[0]), Float.parseFloat(drawPosition[1]));
        drawPoint = ViewPort.worldToView(drawPoint);
        DrawingHelper.drawImage(bgObject.getImageId(), drawPoint.x, drawPoint.y, 0f, (float)scale, (float)scale, 255);
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
