package edu.byu.cs.superasteroids.model.ship;

import android.graphics.PointF;
import android.provider.Settings;

import edu.byu.cs.superasteroids.content.ContentManager;
import edu.byu.cs.superasteroids.core.GraphicsUtils;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;
import edu.byu.cs.superasteroids.model.level.ViewPort;

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
    int adjusting = 4;

    public Laser() {
        Cannon cannon = StarShip.getInstance().getCannon();
        image = cannon.getAttackImage();
        imageWidth = cannon.getAttackImageWidth();
        imageHeight = cannon.getAttackImageHeight();
        sound = cannon.getAttackSound();
        imageId = ContentManager.getInstance().loadImage(image);

        //startingValues();
    }

    private PointF getScaledPoint(PointF original) {
        return new PointF(original.x*scaleX, original.y*scaleY);
    }

    public void update() {
        if(adjusting > 0) {
            startingValues();
            adjusting = adjusting - 1;
        }
        else {
            posX = posX + velocityX;
            posY = posY + velocityY;
        }
    }

    public void draw() {
        if(adjusting <= 0) {
            System.out.println("Drawing laser...");
            System.out.println(toString());
            DrawingHelper.drawImage(imageId, posX, posY, rotationDegrees, scaleX, scaleY, 255);
        }
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

    private void setVelocities() {
        Cannon cannon = StarShip.getInstance().getCannon();

        double angle = GraphicsUtils.degreesToRadians((double)cannon.getRotationDegrees());

        this.velocityX = ((float)Math.sin(angle))*15.0f;
        this.velocityY = (((float)Math.cos(angle))*15.0f)*-1;
    }

    private void startingValues() {
        Cannon cannon = StarShip.getInstance().getCannon();
        this.scaleX = cannon.getScaleX();
        this.scaleY = cannon.getScaleY();

        setVelocities();

        System.out.println("VelX: "+velocityX);
        System.out.println("VelY: "+velocityY);

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
        rotationDegrees = StarShip.getInstance().getMainBody().getRotationDegrees();
    }

    public String toString() {
        return "imageId: "+imageId+" posX: "+posX+" posY: "+posY+" rotationDegrees: "+rotationDegrees+" scaleX: "+scaleX+" scaleY: "+scaleY;
    }

    /*
    private void playSound() throws IOException {
        if(sound != null) {
            ContentManager content = ContentManager.getInstance();
            soundId = content.loadSound(sound);
            content.playSound(soundId, 1.0f, 1.0f);
            content.unloadSound(soundId);
        }
    }
    */
}
