package edu.byu.cs.superasteroids.model.ship;

import android.graphics.PointF;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Jon on 10/10/2016.
 * Contains information describing a cannon part of the ship
 */
public class Cannon extends ShipModelPart {
    private String emitPoint;
    private String attackImage;
    private int attackImageWidth;
    private int attackImageHeight;
    private String attackSound;
    private int damage;

    public Cannon(String attachPoint, String emitPoint, String image, int imageWidth, int imageHeight, String attackImage, int attackImageWidth, int attackImageHeight, String attackSound, int damage) {
        setAttachPoint(attachPoint);
        setEmitPoint(emitPoint);
        setImage(image);
        setImageWidth(imageWidth);
        setImageHeight(imageHeight);
        setAttackImage(attackImage);
        setAttackImageWidth(attackImageWidth);
        setAttackImageHeight(attackImageHeight);
        setAttackSound(attackSound);
        setDamage(damage);
    }

    public Cannon(JSONObject cannon) throws JSONException {
        setAttachPoint(cannon.getString("attachPoint"));
        setEmitPoint(cannon.getString("emitPoint"));
        setImage(cannon.getString("image"));
        setImageWidth(cannon.getInt("imageWidth"));
        setImageHeight(cannon.getInt("imageHeight"));
        setAttackImage(cannon.getString("attackImage"));
        setAttackImageWidth(cannon.getInt("attackImageWidth"));
        setAttackImageHeight(cannon.getInt("attackImageHeight"));
        setAttackSound(cannon.getString("attackSound"));
        setDamage(cannon.getInt("damage"));
    }

    // Getters and Setters


    public String getEmitPoint() {
        return emitPoint;
    }

    public PointF getEmitPointCordinate() {
        String[] emit = emitPoint.split(",");
        float emitX = Float.parseFloat(emit[0]);
        float emitY = Float.parseFloat(emit[1]);
        return new PointF(emitX, emitY);
    }

    public void setEmitPoint(String emitPoint) {
        this.emitPoint = emitPoint;
    }

    public String getAttackImage() {
        return attackImage;
    }

    public void setAttackImage(String attackImage) {
        this.attackImage = attackImage;
    }

    public int getAttackImageWidth() {
        return attackImageWidth;
    }

    public void setAttackImageWidth(int attackImageWidth) {
        this.attackImageWidth = attackImageWidth;
    }

    public int getAttackImageHeight() {
        return attackImageHeight;
    }

    public void setAttackImageHeight(int attackImageHeight) {
        this.attackImageHeight = attackImageHeight;
    }

    public String getAttackSound() {
        return attackSound;
    }

    public void setAttackSound(String attackSound) {
        this.attackSound = attackSound;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
