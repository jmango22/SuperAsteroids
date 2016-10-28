package edu.byu.cs.superasteroids.model;

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

    public String toString() {
        return "cannonAttach: "+getCannonAttach()+" engineAttach: "+getEngineAttach()+" extraAttach: "+getExtraAttach()+" image: "+getImage()+
                " imageWidth: "+getImageWidth()+" imageHeight: "+getImageHeight();
    }

    // Getters and Setters
    public String getCannonAttach() {
        return cannonAttach;
    }

    public void setCannonAttach(String connonAttach) {
        this.cannonAttach = connonAttach;
    }

    public String getEngineAttach() {
        return engineAttach;
    }

    public void setEngineAttach(String engineAttach) {
        this.engineAttach = engineAttach;
    }

    public String getExtraAttach() {
        return extraAttach;
    }

    public void setExtraAttach(String extraAttach) {
        this.extraAttach = extraAttach;
    }
}
