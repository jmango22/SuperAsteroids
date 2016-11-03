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

    private int health = 2;
    boolean start = true;

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
    }

    public Asteroid(Asteroid asteroid) {
        this.setName(asteroid.getName());
        this.setImage(asteroid.getImage());
        this.setImageWidth(asteroid.getImageWidth());
        this.setImageHeight(asteroid.getImageHeight());
        this.setType(asteroid.getType());


        Random random = new Random();
        velX = random.nextFloat() * (0 - 7) -3.5f;
        velY = random.nextFloat() * (0 - 7) -3.5f;
    }

    // This makes a new child asteroid from the parent, needs to be run however many times depending on the parent
    public Asteroid(Asteroid parent, float posX, float posY) {
        this.setName(parent.getName());
        this.setImage(parent.getImage());
        this.setImageWidth(parent.getImageWidth());
        this.setImageHeight(parent.getImageHeight());
        this.setType(parent.getType());

        this.posX = posX;
        this.posY = posY;
        Random random = new Random();
        velX = random.nextFloat() * (0 - 7) -3.5f;
        velY = random.nextFloat() * (0 - 7) -3.5f;
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

    public void loadImage(ContentManager content) {
        imageId = content.loadImage(image);
    }

    public void unloadImage(ContentManager content) {
        content.unloadImage(imageId);
    }

    public void update(List<Asteroid> asteroids, Set<Laser> lasers) {
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
        for(Laser laser : lasers) {
            testLaserCollision(laser);
        }
        for(Asteroid asteroid : asteroids) {
            testAsteroidCollision(asteroid);
        }

        posX = posX + velX;
        posY = posY + velY;
    }

    public void draw() {
        PointF viewCoordinate = ViewPort.worldToView(new PointF(posX, posY));
        DrawingHelper.drawImage(imageId, viewCoordinate.x, viewCoordinate.y, rotationDegrees, scaleX, scaleY, alpha);
    }

    private void testAsteroidCollision(Asteroid asteroid) {
        if(getWorldRect().intersect(asteroid.getWorldRect())) {
            velX = velX * -1;
            velY = velY * -1;
        }
    }

    private void testLaserCollision(Laser laser) {
        if(getWorldRect().contains((int)laser.getWorldHitPoint().x, (int)laser.getWorldHitPoint().y)) {
            System.out.println("Laser hit asteroid!");
            health = health-1;
        }
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
        float left = posX-((getImageWidth()/2f)*scaleX);
        float right = posX+((getImageWidth()/2f)*scaleX);
        float top = posY-((getImageHeight()/2f)*scaleY);
        float bottom = posY+((getImageHeight()/2f)*scaleY);
        Rect asteroid = new Rect((int)left, (int)top, (int)right, (int)bottom);
        //System.out.println("Asteroid rect: "+asteroid);
        return asteroid;
    }
}
