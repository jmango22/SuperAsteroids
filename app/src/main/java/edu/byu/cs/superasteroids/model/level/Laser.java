package edu.byu.cs.superasteroids.model.level;

import android.graphics.PointF;
import android.graphics.Rect;
import android.provider.Settings;

import java.util.List;

import edu.byu.cs.superasteroids.content.ContentManager;
import edu.byu.cs.superasteroids.core.GraphicsUtils;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;
import edu.byu.cs.superasteroids.model.asteroids.Asteroid;
import edu.byu.cs.superasteroids.model.level.ViewPort;
import edu.byu.cs.superasteroids.model.ship.Cannon;
import edu.byu.cs.superasteroids.model.ship.StarShip;

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
    }

    private PointF getScaledPoint(PointF original) {
        return new PointF(original.x*scaleX, original.y*scaleY);
    }

    public void update() {
        if (adjusting > 0) {
            startingValues();
            adjusting = adjusting - 1;
        } else {
            posX = posX + velocityX;
            posY = posY + velocityY;
        }
    }

    public void draw() {
        if (adjusting <= 0) {
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

        this.velocityX = ((float)Math.sin(angle))*10.0f;
        this.velocityY = (((float)Math.cos(angle))*10.0f)*-1;
    }

    private void startingValues() {
        Cannon cannon = StarShip.getInstance().getCannon();
        this.scaleX = cannon.getScaleX();
        this.scaleY = cannon.getScaleY();

        setVelocities();

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

    public Rect getWorldRect() {
        float radius = (imageWidth/2f)*scaleX;
        PointF world =  ViewPort.viewToWorld(new PointF(posX, posY));
        float left = world.x - radius;
        float right = world.x +radius;
        float top = world.y - radius;
        float bottom = world.y + radius;
        return new Rect((int)left, (int)top, (int)right, (int)bottom);
    }

    public boolean testCollision(Asteroid asteroid) {
        if(asteroid.getWorldRect().intersect(getWorldRect())) {
            return true;
        }
        else {
            return false;
        }
    }

    public String toString() {
        return "imageId: "+imageId+" posX: "+posX+" posY: "+posY+" rotationDegrees: "+rotationDegrees+" scaleX: "+scaleX+" scaleY: "+scaleY;
    }
}
