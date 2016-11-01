package edu.byu.cs.superasteroids.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

import edu.byu.cs.superasteroids.content.ContentManager;
import edu.byu.cs.superasteroids.database.SuperAsteroids_DAO;

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
    private Set<LevelObject> levelObjects;
    private Set<LevelAsteroid> levelAsteroids;

    public Level(Set<LevelAsteroid> levelAsteroids, String title, String hint, int width, String music, Set<LevelObject> levelObjects, int height, int number) {
        this.levelAsteroids = levelAsteroids;
        this.title = title;
        this.hint = hint;
        this.width = width;
        this.music = music;
        this.levelObjects = levelObjects;
        this.height = height;
        this.number = number;

        System.out.print("Level Created!\t");
    }

    public Level(JSONObject level) throws JSONException{
        // LevelObjects will take that array and turn those object to level objects
        this.levelObjects = new HashSet<>();
        // Same goes for the levelAsteroids
        this.levelAsteroids = new HashSet<>();

        // Go through in order and get the information out of the JSONObject
        this.number = level.getInt("number");
        this.title = level.getString("title");
        this.hint = level.getString("hint");
        this.width = level.getInt("width");
        this.height = level.getInt("height");
        this.music = level.getString("music");

        //Now to get JSONArrays from levelAsteroids and levelObjects
        JSONArray levelObjects = level.getJSONArray("levelObjects");
        JSONArray levelAsteroids = level.getJSONArray("levelAsteroids");

        //Start with the levelObjects
        for(int i=0; i<levelObjects.length(); i++) {
            JSONObject levelObject = levelObjects.getJSONObject(i);
            LevelObject temp = new LevelObject(levelObject);
            this.levelObjects.add(temp);
        }

        //Finish with the levelAsteroids
        for(int i=0; i<levelAsteroids.length(); i++){
            JSONObject levelAsteroid = levelAsteroids.getJSONObject(i);
            LevelAsteroid temp = new LevelAsteroid(levelAsteroid);
            this.levelAsteroids.add(temp);
        }
    }

    public void loadContent(ContentManager content) {
        for(LevelObject levelObject : levelObjects) {
            levelObject.loadImage(content);
        }
    }

    public void draw() {
        for(LevelObject levelObject : levelObjects) {
            levelObject.draw();
        }
    }

    public void unloadContent(ContentManager content) {
        for(LevelObject levelObject : levelObjects) {
            levelObject.unloadImage(content);
        }
    }

    public Set<LevelAsteroid> getLevelAsteroids() {
        return levelAsteroids;
    }

    public void setLevelAsteroids(Set<LevelAsteroid> levelAsteroids) {
        this.levelAsteroids = levelAsteroids;
    }

    public Set<LevelObject> getLevelObjects() {
        return levelObjects;
    }

    public void setLevelObjects(Set<LevelObject> levelObjects) {
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
