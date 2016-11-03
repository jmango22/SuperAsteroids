package edu.byu.cs.superasteroids.model.level;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import edu.byu.cs.superasteroids.content.ContentManager;
import edu.byu.cs.superasteroids.database.SuperAsteroids_DAO;
import edu.byu.cs.superasteroids.model.asteroids.Asteroid;
import edu.byu.cs.superasteroids.model.asteroids.GrowingAsteroid;
import edu.byu.cs.superasteroids.model.asteroids.OcteroidAsteroid;
import edu.byu.cs.superasteroids.model.asteroids.RegularAsteroid;
import edu.byu.cs.superasteroids.model.ship.Laser;

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
    private List<Asteroid> asteroids = new LinkedList<>();

    public Level(Set<LevelAsteroid> levelAsteroids, String title, String hint, int width, String music, Set<LevelObject> levelObjects, int height, int number) {
        this.levelAsteroids = levelAsteroids;
        this.title = title;
        this.hint = hint;
        this.width = width;
        this.music = music;
        this.levelObjects = levelObjects;
        this.height = height;
        this.number = number;

        for(LevelAsteroid levelAsteroid : levelAsteroids) {
            Asteroid asteroid = SuperAsteroids_DAO.getInstance().getAsteroid(levelAsteroid.getAsteroidId());
            String type = asteroid.getType();
            if(type.equals("regular")) {
                for(int i=0; i <= levelAsteroid.getNumber(); i++) {
                    RegularAsteroid regularAsteroid = new RegularAsteroid(asteroid);
                    asteroids.add(regularAsteroid);
                }
            }
            else if(type.equals("growing")) {
                for(int i=0; i<= levelAsteroid.getNumber(); i++) {
                    GrowingAsteroid growingAsteroid = new GrowingAsteroid(asteroid);
                    asteroids.add(growingAsteroid);
                }
            }
            else if(type.equals("octeroid")) {
                for(int i=0; i<= levelAsteroid.getNumber(); i++) {
                    OcteroidAsteroid octeroidAsteroid = new OcteroidAsteroid(asteroid);
                    asteroids.add(octeroidAsteroid);
                }
            }
        }
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
        for(Asteroid asteroid : asteroids) {
            asteroid.loadImage(content);
        }
    }

    public void update(Set<Laser> lasers) {
        for(Iterator<Asteroid> iterator = asteroids.iterator(); iterator.hasNext(); ) {
            Asteroid asteroid = iterator.next();
            List<Asteroid> testAsteroids = new LinkedList<>(asteroids);
            testAsteroids.remove(asteroid);
            if(asteroid.getHealth() > 0) {
                asteroid.update(testAsteroids, lasers);
            }
            else {
                iterator.remove();
            }
        }
    }

    public void draw() {
        for(LevelObject levelObject : levelObjects) {
            levelObject.draw();
        }
        for(Asteroid asteroid : asteroids) {
            asteroid.draw();
        }
    }

    public void unloadContent(ContentManager content) {
        for(LevelObject levelObject : levelObjects) {
            levelObject.unloadImage(content);
        }
        for(Asteroid asteroid : asteroids) {
            asteroid.unloadImage(content);
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

    public List<Asteroid> getAsteroids() { return asteroids; }
}
