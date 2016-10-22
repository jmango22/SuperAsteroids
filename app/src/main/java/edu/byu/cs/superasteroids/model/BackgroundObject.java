package edu.byu.cs.superasteroids.model;

/**
 * Created by Jon on 10/10/2016.
 * The Background Image
 */
public class BackgroundObject {
    private String image;
    private long id;
    /**
     * Creates the background image object
     */
    public BackgroundObject() {
        this.image = "";
    }

    /**
     * Creates the background image object
     * @param image - string: file path
     */
    public BackgroundObject(String image) {
        this.image = image;
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
}
