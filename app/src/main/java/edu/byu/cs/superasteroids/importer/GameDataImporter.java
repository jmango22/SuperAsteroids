package edu.byu.cs.superasteroids.importer;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import edu.byu.cs.superasteroids.database.SuperAsteroids_DAO;
import edu.byu.cs.superasteroids.model.asteroids.Asteroid;
import edu.byu.cs.superasteroids.model.level.BackgroundObject;
import edu.byu.cs.superasteroids.model.ship.Cannon;
import edu.byu.cs.superasteroids.model.ship.Engine;
import edu.byu.cs.superasteroids.model.ship.ExtraPart;
import edu.byu.cs.superasteroids.model.level.Level;
import edu.byu.cs.superasteroids.model.ship.MainBody;
import edu.byu.cs.superasteroids.model.ship.PowerCore;

/**
 * Created by jmeng2 on 10/22/16.
 */
public class GameDataImporter implements IGameDataImporter {
    private SuperAsteroids_DAO dao;

    public GameDataImporter() {
        dao = SuperAsteroids_DAO.getInstance();
    }

    @Override
    public boolean importData(InputStreamReader dataInputReader) {
        // Start by clearing the Database up
        dao.resetAll();

        try {
            // Need to use my DAO and stuff up here to make a way to put the data into my database
            JSONObject rootObj = new JSONObject(makeString(dataInputReader));

            // There will only ever be one asteroidsGame object at a time...
            JSONObject asteroidsGame = rootObj.getJSONObject("asteroidsGame");

            // Everything inside of asteroidsGame is an Array

            JSONArray bgObjects = asteroidsGame.getJSONArray("objects");
            for(int i=0; i<bgObjects.length(); i++) {
                BackgroundObject bgObject = new BackgroundObject(bgObjects.getString(i));
                dao.addBackgroundObject(bgObject);
            }

            JSONArray asteroids = asteroidsGame.getJSONArray("asteroids");
            for(int i=0; i<asteroids.length(); i++) {
                Asteroid asteroid = new Asteroid(asteroids.getJSONObject(i));
                dao.addAsteroid(asteroid);
            }

            JSONArray levels = asteroidsGame.getJSONArray("levels");
            for(int i=0; i<levels.length(); i++) {
                Level level = new Level(levels.getJSONObject(i));
                dao.addLevel(level);
            }

            JSONArray mainBodies = asteroidsGame.getJSONArray("mainBodies");
            for(int i=0; i<mainBodies.length(); i++) {
                MainBody mainBody = new MainBody(mainBodies.getJSONObject(i));
                dao.addMainBody(mainBody);
            }

            JSONArray cannons = asteroidsGame.getJSONArray("cannons");
            for(int i=0; i<cannons.length(); i++) {
                Cannon cannon = new Cannon(cannons.getJSONObject(i));
                dao.addCannon(cannon);
            }

            JSONArray extraParts = asteroidsGame.getJSONArray("extraParts");
            for(int i=0; i<extraParts.length(); i++) {
                ExtraPart extraPart = new ExtraPart(extraParts.getJSONObject(i));
                dao.addExtraPart(extraPart);
            }

            JSONArray engines = asteroidsGame.getJSONArray("engines");
            for(int i=0; i<engines.length(); i++) {
                Engine engine = new Engine(engines.getJSONObject(i));
                dao.addEngine(engine);
            }

            JSONArray powerCores = asteroidsGame.getJSONArray("powerCores");
            for(int i=0; i<powerCores.length(); i++) {
                PowerCore powerCore = new PowerCore(powerCores.getJSONObject(i));
                dao.addPowerCore(powerCore);
            }

        }
        catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
        return true;
    }

    private static String makeString(Reader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        char[] buf = new char[512];

        int n = 0;
        while((n = reader.read(buf)) > 0) {
            sb.append(buf, 0, n);
        }

        return sb.toString();
    }
}
