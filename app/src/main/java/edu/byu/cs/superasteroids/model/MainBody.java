package edu.byu.cs.superasteroids.model;

import android.graphics.PointF;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Set;

import edu.byu.cs.superasteroids.content.ContentManager;
import edu.byu.cs.superasteroids.database.SuperAsteroids_DAO;

/**
 * Created by Jon on 10/10/2016.
 * Contains information describing a main body part of the ship
 */
public class MainBody extends Model {
    private String cannonAttach;
    private String engineAttach;
    private String extraAttach;

    public MainBody(String cannonAttach, String engineAttach, String extraAttach, String image, int imageWidth, int imageHeight) {
        setCannonAttach(cannonAttach);
        setEngineAttach(engineAttach);
        setExtraAttach(extraAttach);
        setImage(image);
        setImageWidth(imageWidth);
        setImageHeight(imageHeight);
    }

    public MainBody(JSONObject body) throws JSONException {
        setCannonAttach(body.getString("cannonAttach"));
        setEngineAttach(body.getString("engineAttach"));
        setExtraAttach(body.getString("extraAttach"));
        setImage(body.getString("image"));
        setImageWidth(body.getInt("imageWidth"));
        setImageHeight(body.getInt("imageHeight"));
    }

    public void draw(float posX, float posY, float rotationDegrees) {
        this.setPosX(posX);
        this.setPosY(posY);
        this.setRotationDegrees(rotationDegrees);

        this.draw();
    }

    /*
    public String toString() {
        return "cannonAttach: "+getCannonAttach()+" engineAttach: "+getEngineAttach()+" extraAttach: "+getExtraAttach()+" image: "+getImage()+
                " imageWidth: "+ getCenter().x+" imageHeight: "+getCenter().y;
    }
    */

    // Getters and Setters
    public PointF getCannonAttach() {
        String[] cannon = this.cannonAttach.split(",");
        Float cannonX = Float.parseFloat(cannon[0]);
        Float cannonY = Float.parseFloat(cannon[1]);
        return new PointF(cannonX, cannonY);
    }

    public void setCannonAttach(String cannonAttach) {
        this.cannonAttach = cannonAttach;
    }

    public PointF getEngineAttach() {
        String[] engine = this.engineAttach.split(",");
        Float engineX = Float.parseFloat(engine[0]);
        Float engineY = Float.parseFloat(engine[1]);
        return new PointF(engineX, engineY);
    }

    public void setEngineAttach(String engineAttach) {
        this.engineAttach = engineAttach;
    }

    public PointF getExtraAttach() {
        String[] extraPart = this.extraAttach.split(",");
        Float extraPartX = Float.parseFloat(extraPart[0]);
        Float extraPartY = Float.parseFloat(extraPart[1]);
        return new PointF(extraPartX, extraPartY);
    }

    public void setExtraAttach(String extraAttach) {
        this.extraAttach = extraAttach;
    }
}
