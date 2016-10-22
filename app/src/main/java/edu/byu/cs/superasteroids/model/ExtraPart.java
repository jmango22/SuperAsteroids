package edu.byu.cs.superasteroids.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Jon on 10/10/2016.
 */
public class ExtraPart {
    private String attach_point;
    private String image;
    private int imageWidth;
    private int imageHeight;

    public ExtraPart(String attach_point, String image, int imageWidth, int imageHeight) {
        this.attach_point = attach_point;
        this.image = image;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }

    public ExtraPart(JSONObject extraPart) throws JSONException {
        this.attach_point = extraPart.getString("attachPoint");
        this.image = extraPart.getString("image");
        this.imageWidth = extraPart.getInt("imageWidth");
        this.imageHeight = extraPart.getInt("imageHeight");
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
