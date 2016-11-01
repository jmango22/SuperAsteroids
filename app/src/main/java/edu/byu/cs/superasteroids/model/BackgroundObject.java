package edu.byu.cs.superasteroids.model;

import edu.byu.cs.superasteroids.content.ContentManager;

/**
 * Created by Jon on 10/10/2016.
 * The Background Image
 */
public class BackgroundObject {
    private String image;
    private long id;
    private int imageId;

    public BackgroundObject(String image) {
        this.image = image;
    }

    public void loadImage(ContentManager content) {
        imageId = content.loadImage(image);
    }

    public void unloadImage(ContentManager content) {
        content.unloadImage(imageId);
    }

    public int getImageId() { return imageId; }

    public void setId(long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public String toString() {
        return "image: "+image;
    }
}
