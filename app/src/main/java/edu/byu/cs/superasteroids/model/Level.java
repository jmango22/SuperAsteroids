package edu.byu.cs.superasteroids.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Jon on 10/10/2016.
 * Contains information describing a level
 */
public class Level {
    private int number;
    private String title;
    private String hint;
    private int width;
    private int height;
    private String music;
    private List<LevelObject> levelObjects;
    private List<LevelAsteroid> levelAsteroids;

    public Level(List<LevelAsteroid> levelAsteroids, String title, String hint, int width, String music, List<LevelObject> levelObjects, int height, int number) {
        this.levelAsteroids = levelAsteroids;
        this.title = title;
        this.hint = hint;
        this.width = width;
        this.music = music;
        this.levelObjects = levelObjects;
        this.height = height;
        this.number = number;
    }

    public Level(Level level) {
        this.levelObjects = level.getLevelObjects();
        this.music = level.getMusic();
        this.height = level.getHeight();
        this.width = level.getWidth();
        this.title = level.getTitle();
        this.number = level.getNumber();
        this.hint = level.getHint();
        this.levelAsteroids = getLevelAsteroids();
    }

    public List<LevelAsteroid> getLevelAsteroids() {
        return levelAsteroids;
    }

    public void setLevelAsteroids(List<LevelAsteroid> levelAsteroids) {
        this.levelAsteroids = levelAsteroids;
    }

    public List<LevelObject> getLevelObjects() {
        return levelObjects;
    }

    public void setLevelObjects(List<LevelObject> levelObjects) {
        this.levelObjects = levelObjects;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
