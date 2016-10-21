package edu.byu.cs.superasteroids.model;

/**
 * Created by Jon on 10/10/2016.
 * Contains information describing an asteroid type
 */
public class Asteroid {
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
    public Asteroid(Asteroid asteroid) {
        this.name = asteroid.getName();
        this.image = asteroid.getImage();
        this.imageWidth = asteroid.getImageWidth();
        this.imageHeight = asteroid.getImageHeight();
        this.type = asteroid.getType();
    }

    /**
     * Get the name
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name
     * @param name - String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the image
     * @return String image
     */
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
}