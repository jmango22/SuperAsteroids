package edu.byu.cs.superasteroids.model;

import android.graphics.PointF;

import edu.byu.cs.superasteroids.content.ContentManager;
import edu.byu.cs.superasteroids.core.GraphicsUtils;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;

/**
 * Created by jmeng2 on 10/31/16.
 */
public class Laser extends Model {
    private float velocityX;
    private float velocityY;

    public Laser(Cannon cannon, float velocityX, float velocityY) {
        setImage(cannon.getAttackImage());
        setImageWidth(cannon.getAttackImageWidth());
        setImageHeight(cannon.getAttackImageHeight());

        this.velocityX = velocityX*2;
        this.velocityY = velocityY*2;

        PointF emitPoint = cannon.getEmitPointCordinate();
        this.setScaleX(cannon.getScaleX());
        this.setScaleY(cannon.getScaleY());

        PointF bodyCenter = cannon.getCenter();

        PointF partOffset = GraphicsUtils.subtract(emitPoint, bodyCenter);
        PointF bodyLocation = cannon.getLocation();

        //PointF rotatedPartOffset = GraphicsUtils.rotate(partOffset, GraphicsUtils.degreesToRadians(cannon.getRotationDegrees()));

        PointF partLocation = GraphicsUtils.add(bodyLocation, partOffset);

        this.setPosX(partLocation.x);
        this.setPosY(partLocation.y);
        this.setRotationDegrees(cannon.getRotationDegrees());

        loadImage();
    }

    public void update() {
        setPosX(getPosX()+velocityX);
        setPosY(getPosY()+velocityY);
    }

    public boolean isOffScreen() {
        if((getPosX() > DrawingHelper.getGameViewWidth()) || (getPosX() < 0)) {
            return true;
        }
        if((getPosY() > DrawingHelper.getGameViewHeight()) || (getPosY() < 0)) {
            return true;
        }
        return false;
    }
}
