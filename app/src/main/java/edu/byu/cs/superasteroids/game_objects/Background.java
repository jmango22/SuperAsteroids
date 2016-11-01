package edu.byu.cs.superasteroids.game_objects;

import edu.byu.cs.superasteroids.content.ContentManager;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;

/**
 * Created by jmeng2 on 10/31/16.
 */
public class Background {
    String image;
    int width;
    int height;
    int imageId;

    public Background() {
        image = "images/space.bmp";
    }

    public void loadImage(ContentManager content) {
        imageId = content.getInstance().loadImage(image);
    }

    public void unloadImage(ContentManager content) {
        content.getInstance().unloadImage(imageId);
    }

    public void draw() {
        DrawingHelper.drawImage(imageId, ViewPort.getViewInWorld(), ViewPort.getViewPort());
    }
}
