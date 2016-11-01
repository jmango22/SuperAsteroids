package edu.byu.cs.superasteroids.model;

import android.graphics.PointF;

import java.io.IOError;
import java.io.IOException;

import edu.byu.cs.superasteroids.content.ContentManager;
import edu.byu.cs.superasteroids.content.Sound;
import edu.byu.cs.superasteroids.core.GraphicsUtils;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;

/**
 * Created by jmeng2 on 10/31/16.
 */
public class Laser {
    float posX;
    float posY;
    float rotationDegrees;
    private float velocityX;
    private float velocityY;
    String image;
    String sound;
    int imageId;
    int soundId;
    int imageWidth;
    int imageHeight;
    float scaleX;
    float scaleY;

    public Laser(Cannon cannon, float velocityX, float velocityY) {
        image = cannon.getAttackImage();
        imageWidth = cannon.getAttackImageWidth();
        imageHeight = cannon.getAttackImageHeight();
        sound = cannon.getAttackSound();

        scaleX = cannon.getScaleX();
        scaleY = cannon.getScaleY();

        this.velocityX = velocityX*2f;
        this.velocityY = velocityY*2f;

        PointF attachPoint = new PointF(((float)imageWidth*1.5f), ((float)imageHeight));
        PointF partCenter = new PointF((((float)imageWidth)/2f),(((float)imageHeight)/2f));

        PointF bodyAttach = cannon.getEmitPointCordinate();
        PointF bodyCenter = cannon.getCenter();

        // Adjust the image coordinates to the scaled size
        attachPoint = getScaledPoint(attachPoint);
        partCenter = getScaledPoint(partCenter);

        bodyAttach = getScaledPoint(bodyAttach);
        bodyCenter = getScaledPoint(bodyCenter);

        // offset and rotation equations
        PointF partOffset = GraphicsUtils.add(GraphicsUtils.subtract(bodyAttach, bodyCenter), GraphicsUtils.subtract(partCenter, attachPoint));
        PointF bodyLocation = cannon.getLocation();

        PointF rotatedPartOffset = GraphicsUtils.rotate(partOffset, GraphicsUtils.degreesToRadians(cannon.getRotationDegrees()));

        PointF partLocation = GraphicsUtils.add(bodyLocation, rotatedPartOffset);

        posX = partLocation.x;
        posY = partLocation.y;
        rotationDegrees = cannon.getRotationDegrees();

        imageId = ContentManager.getInstance().loadImage(image);
    }

    private PointF getScaledPoint(PointF original) {
        return new PointF(original.x*scaleX, original.y*scaleY);
    }

    public void update() {
        posX = posX+velocityX;
        posY = posY+velocityY;
    }

    public void draw() {

        DrawingHelper.drawImage(imageId, posX, posY, rotationDegrees, scaleX, scaleY, 255);
    }

    public boolean isOffScreen() {
        if((posX > DrawingHelper.getGameViewWidth()) || (posX < 0)) {
            return true;
        }
        if((posY > DrawingHelper.getGameViewHeight()) || (posY < 0)) {
            return true;
        }
        return false;
    }

    private void playSound() throws IOException {
        if(sound != null) {
            ContentManager content = ContentManager.getInstance();
            soundId = content.loadSound(sound);
            content.playSound(soundId, 1.0f, 1.0f);
            content.unloadSound(soundId);
        }
    }
}
