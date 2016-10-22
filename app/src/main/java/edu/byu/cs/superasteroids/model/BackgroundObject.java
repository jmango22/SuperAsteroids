package edu.byu.cs.superasteroids.model;

import org.json.JSONObject;

/**
 * Created by Jon on 10/10/2016.
 * The Background Image
 */
public class BackgroundObject {
    private String image;
    private long id;

    public BackgroundObject(String image) {
        this.image = image;

        System.out.println("BackgroundObject Created!");
        System.out.println(toString());
    }

    public long getId() { return id; }

    public void setId(long id) {
        this.id = id;
    }
    /**
     * Get the path to the background image file
     * @return string path to background image
     */
    public String getImage() {
        return image;
    }

    /**
     * Set the path to the background image file
     * @param image - string: file path
     */
    public void setImage(String image) {
        this.image = image;
    }

    public String toString() {
        return "image: "+image;
    }
}
