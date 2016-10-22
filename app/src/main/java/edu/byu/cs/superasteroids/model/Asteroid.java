package edu.byu.cs.superasteroids.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Jon on 10/10/2016.
 * Contains information describing an asteroid type
 */
public class Asteroid {
    private long id;
    private String name;
    private String image;
    private int imageWidth;
    private int imageHeight;
    private String type;

    /**
     * Creates an Asteroid
     * @param name - Type String
     * @param image - Type String
     * @param imageWidth - Type int
     * @param imageHeight - Type int
     * @param type - Type String
     */
    public Asteroid(String name, String image, int imageWidth, int imageHeight, String type) {
        this.name = name;
        this.image = image;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.type = type;
    }

    /**
     * Creates an Asteroid from another Asteroid
     * @param asteroid - Type Asteroid
     */
    public Asteroid(JSONObject asteroid) throws JSONException {
        this.name = asteroid.getString("name");
        this.image = asteroid.getString("image");
        this.imageWidth = asteroid.getInt("imageWidth");
        this.imageHeight = asteroid.getInt("imageHeight");
        this.type = asteroid.getString("type");

        System.out.println("Asteroid Create!");
        System.out.println(toString());
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString() {
        return "name: "+name+" image: "+image+" imageWidth: "+imageWidth+" imageHeight: "+imageHeight+" type: "+type;
    }
}
