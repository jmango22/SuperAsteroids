package edu.byu.cs.superasteroids.model;

import edu.byu.cs.superasteroids.content.ContentManager;

/**
 * Created by Jon on 10/22/2016.
 */
public class Model {


    private String image;
    private int imageWidth;
    private int imageHeight;

    private int imageId;

    public void loadImage() {
        ContentManager.getInstance().loadImage(image);
    }

    public void unloadImage() {
        ContentManager.getInstance().unloadImage(imageId);
        imageId = -1;
    }

    public int getImageId() {
        return imageId;
    }

    public String getImage() {
        return image;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
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

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Model)) {
            return false;
        }
        else if((!(((Model) o).getImage().equals(this.image))
                || (((Model) o).getImageHeight() != this.imageHeight)
                || (((Model) o).getImageWidth() != this.imageWidth))) {
            return false;
        }
        else {
            return true;
        }
    }
}
