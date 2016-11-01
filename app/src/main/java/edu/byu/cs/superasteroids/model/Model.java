package edu.byu.cs.superasteroids.model;

import android.graphics.PointF;

import java.lang.annotation.Inherited;

import edu.byu.cs.superasteroids.content.ContentManager;
import edu.byu.cs.superasteroids.core.GraphicsUtils;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;

/**
 * Created by Jon on 10/22/2016.
 */
public class Model {
    private String image;
    private int imageWidth;
    private int imageHeight;
    private String attachPoint;

    private int imageId;
    private float posX;
    private float posY;
    private float rotationDegrees = 0f;

    private float scaleX;
    private float scaleY;
    private int alpha = 255;

    // Used by the MainBody and by the Other parts to draw themselves
    public void draw() {
        DrawingHelper.drawImage(imageId, posX, posY, rotationDegrees, scaleX, scaleY, alpha);
    }

    // Uses the Main Body position, size, and rotation to attach the Cannon, ExtraPart, and Engine
    public void draw(MainBody mainBody, PointF bodyAttach) {
        this.setScaleX(mainBody.getScaleX());
        this.setScaleY(mainBody.getScaleY());

        PointF bodyCenter = mainBody.getCenter();

        PointF partOffset = GraphicsUtils.add(GraphicsUtils.subtract(bodyAttach, bodyCenter), GraphicsUtils.subtract(this.getCenter(), this.getAttachPoint()));
        PointF bodyLocation = mainBody.getLocation();

        PointF rotatedPartOffset = GraphicsUtils.rotate(partOffset, GraphicsUtils.degreesToRadians(mainBody.getRotationDegrees()));

        PointF partLocation = GraphicsUtils.add(bodyLocation, rotatedPartOffset);

        this.setPosX(partLocation.x);
        this.setPosY(partLocation.y);
        this.setRotationDegrees(mainBody.getRotationDegrees());
        this.draw();
    }

    public void loadImage() {
        imageId = ContentManager.getInstance().loadImage(image);
    }

    public void unloadImage() {
        ContentManager.getInstance().unloadImage(imageId);
        imageId = -1;
    }

    @Override
    public String toString() {
        return "image: "+image+" imageWidth: "+imageWidth+" imageHeight: "+imageHeight;
    }

    public String getAttachPointString() {
        return attachPoint;
    }

    public PointF getAttachPoint() {
        String[] attach = attachPoint.split(",");
        Float attachX = (Float.parseFloat(attach[0]))*scaleX;
        Float attachY = (Float.parseFloat(attach[1]))*scaleY;
        return new PointF(attachX, attachY);
    }

    public void setAttachPoint(String attachPoint) {
        this.attachPoint = attachPoint;
    }

    public PointF getLocation() {
        return new PointF(posX, posY);
    }

    public PointF getCenter() {
        Float x = (((float)imageWidth)/2f)*scaleX;
        Float y = (((float)imageHeight)/2f)*scaleY;
        return new PointF(x, y);
    }

    public int getImageId() {
        return imageId;
    }

    public float getRotationDegrees () {
        return rotationDegrees;
    }

    public float getScaleX () {
        return scaleX;
    }

    public float getScaleY () {
        return scaleY;
    }

    public int getAlpha () {
        return alpha;
    }

    public String getImage() {
        return image;
    }

    public void setPosX (float posX) {
        this.posX = posX;
    }

    public float getPosX() { return posX; }

    public float getPosY() { return posY; }

    public void setPosY (float posY) {
        this.posY = posY;
    }

    public void setRotationDegrees(float rotationDegrees) {
        this.rotationDegrees = rotationDegrees;
    }

    public void setScaleX(float scaleX) {
        this.scaleX = scaleX;
    }

    public void setScaleY(float scaleY) {
        this.scaleY = scaleY;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Model)) {
            return false;
        }
        else if((!(((Model) o).getImage().equals(this.image)))
                || (((Model) o).getCenter() != this.getCenter())) {
            return false;
        }
        else {
            return true;
        }
    }
}
