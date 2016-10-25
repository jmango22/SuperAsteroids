package edu.byu.cs.superasteroids.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Jon on 10/10/2016.
 * Contains information describing an asteroid type
 */
public class Asteroid extends Model {
    private long id;
    private String name;
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
        this.setName(name);
        this.setImage(image);
        this.setImageWidth(imageWidth);
        this.setImageHeight(imageHeight);
        this.setType(type);
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString() {
        return "name: "+name+" image: "+getImage()+" imageWidth: "+getImageWidth()+" imageHeight: "+getImageHeight()+" type: "+type;
    }
}
