package edu.byu.cs.superasteroids.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Jon on 10/10/2016.
 * Contains information describing a cannon part of the ship
 */
public class Cannon {
    private String attachPoint;
    private String emitPoint;
    private String image;
    private int imageWidth;
    private int imageHeight;
    private String attackImage;
    private int attackImageWidth;
    private int attackImageHeight;
    private String attackSound;
    private int damage;

    public Cannon(String attachPoint, String emitPoint, String image, int imageWidth, int imageHeight, String attackImage, int attackImageWidth, int attackImageHeight, String attackSound, int damage) {
        this.attachPoint = attachPoint;
        this.emitPoint = emitPoint;
        this.image = image;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.attackImage = attackImage;
        this.attackImageWidth = attackImageWidth;
        this.attackImageHeight = attackImageHeight;
        this.attackSound = attackSound;
        this.damage = damage;
    }

    public Cannon(JSONObject cannon) throws JSONException{
        this.attachPoint = cannon.getString("attachPoint");
        this.emitPoint = cannon.getString("emitPoint");
        this.image = cannon.getString("image");
        this.imageWidth = cannon.getInt("imageWidth");
        this.imageHeight = cannon.getInt("imageHeight");
        this.attackImage = cannon.getString("attackImage");
        this.attackImageWidth = cannon.getInt("attackImageWidth");
        this.attackImageHeight = cannon.getInt("attackImageHeight");
        this.attackSound = cannon.getString("attackSound");
        this.damage = cannon.getInt("damage");

        System.out.println("Cannon Created!");
        System.out.println(toString());
    }

    public String toString() {
        return "attachPoint: "+attachPoint+" emitPoint: "+emitPoint+" image: "+image+" imageWidth: "+imageWidth+" imageHeight: "+imageHeight+" attackImage: "+attackImage
                +" attackImageWidth: "+attackImageWidth+" attackImageHeight: "+attackImageHeight+" attackSound: "+attackSound+" damage: "+damage;
    }

    public String getAttachPoint() {
        return attachPoint;
    }

    public void setAttachPoint(String attachPoint) {
        this.attachPoint = attachPoint;
    }

    public String getEmitPoint() {
        return emitPoint;
    }

    public void setEmitPoint(String emitPoint) {
        this.emitPoint = emitPoint;
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
