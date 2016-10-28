package edu.byu.cs.superasteroids.model;

import org.json.JSONException;
import org.json.JSONObject;

import edu.byu.cs.superasteroids.content.ContentManager;

/**
 * Created by Jon on 10/10/2016.
 */
public class ExtraPart extends Model{
    private String attachPoint;

    public ExtraPart(String attachPoint, String image, int imageWidth, int imageHeight) {
        setAttachPoint(attachPoint);
        setImage(image);
        setImageWidth(imageWidth);
        setImageHeight(imageHeight);
    }

    public ExtraPart(JSONObject extraPart) throws JSONException {
        setAttachPoint(extraPart.getString("attachPoint"));
        setImage(extraPart.getString("image"));
        setImageWidth(extraPart.getInt("imageWidth"));
        setImageHeight(extraPart.getInt("imageHeight"));
    }


    public String toString() {
        return " attachPoint: "+getAttachPoint()+" image: "+getImage()+" imageWidth: "+getImageWidth()+" imageHeight: "+getImageHeight();
    }

    // Getters and Setters
    public String getAttachPoint() { return attachPoint; }

    public void setAttachPoint(String attach_point) {
        this.attachPoint = attach_point;
    }
}
