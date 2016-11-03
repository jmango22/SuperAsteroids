package edu.byu.cs.superasteroids.model.asteroids;

import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.RotateDrawable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Random;
import java.util.Set;

import edu.byu.cs.superasteroids.content.ContentManager;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;
import edu.byu.cs.superasteroids.model.level.ViewPort;
import edu.byu.cs.superasteroids.model.ship.Laser;
import edu.byu.cs.superasteroids.model.ship.StarShip;

/**
 * Created by Jon on 10/10/2016.
 * Contains information describing an asteroid type
 */
public class Asteroid {
    private long id;
    private String name;
    private String type;

    private String image;
    private int imageId;
    private int imageWidth;
    private int imageHeight;

    //keep these in world coordinates
    private float posX;
    private float posY;

    private float velX;
    private float velY;

    private float scaleX = 1f;
    private float scaleY = 1f;

    private int alpha = 255;
    private float rotationDegrees = 0f;

    private int health;
    boolean start = true;
    boolean split;

    /**
     * Creates an Asteroid
     * @param name - Type String
     * @param image - Type String
     * @param imageWidth - Type int
     * @param imageHeight - Type int
     * @param type - Type String
     */
    public Asteroid(String name, String image, int imageWidth, int imageHeight, String type) {
        this.setName(name);
        this.setImage(image);
        this.setImageWidth(imageWidth);
        this.setImageHeight(imageHeight);
        this.setType(type);

        System.out.println("Asteroid created from Database\t"+toString());
    }

    public Asteroid(Asteroid asteroid) {
        this.setName(asteroid.getName());
        this.setImage(asteroid.getImage());
        this.setImageWidth(asteroid.getImageWidth());
        this.setImageHeight(asteroid.getImageHeight());
        this.setType(asteroid.getType());

        loadImage(ContentManager.getInstance());

        Random random = new Random();
        velX = random.nextFloat() * (0 - 7) -3.5f;
        velY = random.nextFloat() * (0 - 7) -3.5f;
        split = true;
        health = 2;

        System.out.println("Asteroid created from Generic Asteroid\t"+toString());
    }

    // This makes a new child asteroid from the parent, needs to be run however many times depending on the parent
    public Asteroid(Asteroid parent, float posX, float posY) {
        this.setName(parent.getName());
        this.setImage(parent.getImage());
        this.setImageWidth(parent.getImageWidth());
        this.setImageHeight(parent.getImageHeight());
        this.setType(parent.getType());
        this.health = 2;
        this.scaleX = parent.getScaleX()*.5f;
        this.scaleY = parent.getScaleY()*.5f;
        this.posX = posX;
        this.posY = posY;
        this.imageId = parent.getImageId();
        start = false;

        Random random = new Random();
        velX = random.nextFloat() * (0 - 7) -3.5f;
        velY = random.nextFloat() * (0 - 7) -3.5f;
        System.out.println("Asteroid created from parent\t"+toString());
        split = false;
    }

    /**
     * Creates an Asteroid from another Asteroid
     * @param asteroid - JSONObject
     */
    public Asteroid(JSONObject asteroid) throws JSONException {
        this.setName(asteroid.getString("name"));
        this.setImage(asteroid.getString("image"));
        this.setImageWidth(asteroid.getInt("imageWidth"));
        this.setImageHeight(asteroid.getInt("imageHeight"));
        this.setType(asteroid.getString("type"));

        System.out.println("Asteroid created from JSON\t"+toString());
    }

    private int getRandomX() {
        int min = 0;
        int max = ViewPort.getWorldWidth();
        Random r = new Random();
        return r.nextInt(max - min + 1) + min;
    }

    private int getRandomY() {
        int min = 0;
        int max = ViewPort.getWorldHeight();
        Random r = new Random();
        return r.nextInt(max - min + 1) + min;
    }

    private void setInitialPosition() {
        float left = (ViewPort.getWorldWidth()/2f)-(ViewPort.getViewWidth()/2f);
        float top = (ViewPort.getWorldHeight()/2f)-(ViewPort.getViewHeight()/2f);
        float right = left + ViewPort.getViewWidth();
        float bottom = top + ViewPort.getViewHeight();

        posX = getRandomX();
        posY = getRandomY();

        while((posX > left) && (posX < right)) {
            posX = getRandomX();
        }

        while((posY > top) && (posY < bottom)) {
            posY = getRandomY();
        }
    }

    public boolean canSplit() {
        return split;
    }

    public void loadImage(ContentManager content) {
        this.imageId = content.loadImage(image);
    }

    public void unloadImage(ContentManager content) {
        content.unloadImage(imageId);
    }

    public void update() {
        if(start) {
            setInitialPosition();
            start = false;
        }
        if ((posX >= ViewPort.getWorldWidth()) || (posX <= 0)) {
            velX = velX * -1;
        }
        if ((posY >= ViewPort.getWorldHeight()) || (posY <= 0)) {
            velY = velY * -1;
        }

        posX = posX + velX;
        posY = posY + velY;
    }

    public void draw() {
        PointF viewCoordinate = ViewPort.worldToView(new PointF(posX, posY));
        DrawingHelper.drawImage(imageId, viewCoordinate.x, viewCoordinate.y, rotationDegrees, scaleX, scaleY, alpha);
    }

    public void testCollision(Asteroid asteroid) {
        if(getWorldRect().intersect(asteroid.getWorldRect())) {
            velX = velX * -1;
            velY = velY * -1;
        }
    }

    public void testCollision(Laser laser) {
        if(getWorldRect().intersect(laser.getWorldRect())) {
            //System.out.println("Laser hit asteroid!");
            health = health-1;
        }
    }

    public boolean testCollision(StarShip ship) {
        if(ship.getSafeTime() <= 0) {
            if (getWorldRect().intersect(ship.getSpaceShipSpace())) {
                velX = velX * -1;
                velY = velY * -1;
                health = health - 1;
                return true;
            }
            return false;
        }
        return false;
    }

    public void collide() {
        velX = velX * -1;
        velY = velY * -1;
        health = health - 1;
    }

    public int getHealth() { return health; }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getVelX() { return velX; }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() { return velY; }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString() {
        return "name: "+name+" image: "+getImage()+" type: "+type;
    }

    public int getImageId() {
        return imageId;
    }

    public float getScaleX () {
        return scaleX;
    }

    public float getScaleY () {
        return scaleY;
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

    public void setScaleX(float scaleX) {
        this.scaleX = scaleX;
    }

    public void setScaleY(float scaleY) {
        this.scaleY = scaleY;
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

    public Rect getWorldRect() {
        float radius;
        if(getScaleX() >= 1) {
            radius = getImageWidth()/2f*getScaleX();
        }
        else {
            radius = getImageWidth()/2f;
        }
        float left = getPosX()-radius;
        float right = getPosX()+radius;
        float top = getPosY()-radius;
        float bottom = getPosY()+radius;
        Rect asteroid = new Rect((int)left, (int)top, (int)right, (int)bottom);
        //System.out.println("Asteroid rect: "+asteroid);
        return asteroid;
    }
}
