package edu.byu.cs.superasteroids.model;

/**
 * Created by Jon on 10/10/2016.
 * Contains information describing a main body part of the ship
 */
public class MainBody {
    private String connonAttach;
    private String engineAttach;
    private String extraAttach;
    private String image;
    private int imageWidth;
    private int imageHeight;

    public MainBody(String connonAttach, String engineAttach, String extraAttach, String image, int imageWidth, int imageHeight) {
        this.connonAttach = connonAttach;
        this.engineAttach = engineAttach;
        this.extraAttach = extraAttach;
        this.image = image;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }

    public MainBody(MainBody body) {
        this.connonAttach = body.getConnonAttach();
        this.engineAttach =  body.getEngineAttach();
        this.extraAttach = body.getExtraAttach();
        this.image = body.getImage();
        this.imageWidth = body.getImageWidth();
        this.imageHeight = body.getImageHeight();
    }

    public String getConnonAttach() {
        return connonAttach;
    }

    public void setConnonAttach(String connonAttach) {
        this.connonAttach = connonAttach;
    }

    public String getEngineAttach() {
        return engineAttach;
    }

    public void setEngineAttach(String engineAttach) {
        this.engineAttach = engineAttach;
    }

    public String getExtraAttach() {
        return extraAttach;
    }

    public void setExtraAttach(String extraAttach) {
        this.extraAttach = extraAttach;
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
}
