package edu.byu.cs.superasteroids.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Jon on 10/10/2016.
 */
public class ExtraPart extends Model{
    private String attach_point;

    public ExtraPart(String attach_point, String image, int imageWidth, int imageHeight) {
        setAttach_point(attach_point);
        setImage(image);
        setImageWidth(imageWidth);
        setImageHeight(imageHeight);
    }

    public ExtraPart(JSONObject extraPart) throws JSONException {
        setAttach_point(extraPart.getString("attachPoint"));
        setImage(extraPart.getString("image"));
        setImageWidth(extraPart.getInt("imageWidth"));
        setImageHeight(extraPart.getInt("imageHeight"));
    }

    public String toString() {
        return " attachPoint: "+getAttach_point()+" image: "+getImage()+" imageWidth: "+getImageWidth()+" imageHeight: "+getImageHeight();
    }

    // Getters and Setters
    public String getAttach_point() { return attach_point; }

    public void setAttach_point(String attach_point) {
        this.attach_point = attach_point;
    }
}
