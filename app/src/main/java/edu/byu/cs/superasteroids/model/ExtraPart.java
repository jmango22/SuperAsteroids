package edu.byu.cs.superasteroids.model;

import android.graphics.PointF;

import org.json.JSONException;
import org.json.JSONObject;

import edu.byu.cs.superasteroids.content.ContentManager;
import edu.byu.cs.superasteroids.core.GraphicsUtils;

/**
 * Created by Jon on 10/10/2016.
 */
public class ExtraPart extends Model{

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

}
