package edu.byu.cs.superasteroids.database;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.byu.cs.superasteroids.model.Asteroid;
import edu.byu.cs.superasteroids.model.BackgroundObject;
import edu.byu.cs.superasteroids.model.Cannon;
import edu.byu.cs.superasteroids.model.Engine;
import edu.byu.cs.superasteroids.model.ExtraPart;
import edu.byu.cs.superasteroids.model.Level;
import edu.byu.cs.superasteroids.model.LevelAsteroid;
import edu.byu.cs.superasteroids.model.LevelObject;
import edu.byu.cs.superasteroids.model.MainBody;
import edu.byu.cs.superasteroids.model.PowerCore;


/**
 * Created by Jon on 10/10/2016.
 * This class follows the SINGLETON DESIGN PATTERN, has only one instance, and is accessed through its public SINGLETON Method
 * Add methods
 * GetAll methods
 * ClearAll method
 */
public class SuperAsteroids_DAO {
    private SQLiteDatabase db;

    //Singleton instance
    private static volatile SuperAsteroids_DAO instance;

    private SuperAsteroids_DAO() {}

    public static SuperAsteroids_DAO getInstance() {
        if(instance == null) {
            instance = new SuperAsteroids_DAO();
        }
        return instance;
    }

    public void setDB(SQLiteDatabase db) {
        this.db = db;
    }

    // Add methods

    public boolean addAsteroid(Asteroid asteroid) {
        ContentValues values = new ContentValues();
        values.put("name", asteroid.getName());
        values.put("image", asteroid.getImage());
        values.put("image_width", asteroid.getImageWidth());
        values.put("image_height", asteroid.getImageHeight());
        values.put("type", asteroid.getType());

        long id = db.insert("asteroid", null, values);
        if(id >= 0) {
            asteroid.setId(id);
            //System.out.println("Asteroid added to DB successfully!");
            return true;
        }
        else {
            //System.out.println("Asteroid failed to be added to DB successfully.");
            return false;
        }
    }

    public boolean addLevel(Level level) {
        ContentValues values = new ContentValues();
        values.put("number", level.getNumber());
        values.put("title", level.getTitle());
        values.put("hint", level.getHint());
        values.put("width", level.getWidth());
        values.put("height", level.getHeight());
        values.put("music", level.getMusic());

        // Add LevelObjects to their table
        for (LevelObject levelObject : level.getLevelObjects()) {
            boolean result = addLevelObject(level.getNumber(), levelObject);
            if(!result) {
                return false;
            }
        }

        // Add LevelAsteroids to their table
        for (LevelAsteroid levelAsteroid : level.getLevelAsteroids()) {
            boolean result = addLevelAsteroid(level.getNumber(), levelAsteroid);
            if(!result) {
                return false;
            }
        }

        long id = db.insert("level", null, values);
        if(id >= 0) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean addLevelObject(int level_id, LevelObject levelObject) {
        ContentValues values = new ContentValues();
        values.put("position", levelObject.getPosition());
        values.put("object_id", levelObject.getObjectId());
        values.put("scale", levelObject.getScale());
        values.put("level_id", level_id);

        long id = db.insert("level_object", null, values);
        if(id >= 0) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean addLevelAsteroid(int level_id, LevelAsteroid levelAsteroid) {
        ContentValues values = new ContentValues();
        values.put("number", levelAsteroid.getNumber());
        values.put("asteroid_id", levelAsteroid.getAsteroidId());
        values.put("level_id", level_id);

        long id = db.insert("level_asteroid", null, values);
        if(id >= 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean addMainBody(MainBody mainBody) {
        ContentValues values = new ContentValues();
        values.put("cannon_attach", mainBody.getCannonAttachString());
        values.put("engine_attach", mainBody.getEngineAttachString());
        values.put("extra_attach", mainBody.getExtraAttachString());
        values.put("image", mainBody.getImage());
        values.put("image_width", mainBody.getImageWidth());
        values.put("image_height", mainBody.getImageHeight());

        long id = db.insert("main_body", null, values);
        if(id >= 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean addCannon(Cannon cannon) {
        ContentValues values = new ContentValues();
        values.put("attach_point", cannon.getAttachPointString());
        values.put("emit_point", cannon.getEmitPoint());
        values.put("image", cannon.getImage());
        values.put("image_width", cannon.getImageWidth());
        values.put("image_height", cannon.getImageHeight());
        values.put("attack_image", cannon.getAttackImage());
        values.put("attack_image_width", cannon.getAttackImageWidth());
        values.put("attack_image_height", cannon.getAttackImageHeight());
        values.put("attack_sound", cannon.getAttackSound());
        values.put("damage", cannon.getDamage());

        long id = db.insert("cannon", null, values);
        if(id >= 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean addExtraPart(ExtraPart extraPart) {
        ContentValues values = new ContentValues();
        values.put("attach_point", extraPart.getAttachPointString());
        values.put("image", extraPart.getImage());
        values.put("image_width", extraPart.getImageWidth());
        values.put("image_height", extraPart.getImageHeight());

        long id = db.insert("extra_part", null, values);
        if(id >= 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean addEngine(Engine engine) {
        ContentValues values = new ContentValues();
        values.put("base_speed", engine.getBaseSpeed());
        values.put("base_turn_rate", engine.getBaseTurnRate());
        values.put("attach_point", engine.getAttachPointString());
        values.put("image", engine.getImage());
        values.put("image_width", engine.getImageWidth());
        values.put("image_height", engine.getImageHeight());

        long id = db.insert("engine", null, values);
        if(id >= 0) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public boolean addPowerCore(PowerCore powerCore) {
        ContentValues values = new ContentValues();
        values.put("cannon_boost", powerCore.getCannonBoost());
        values.put("engine_boost", powerCore.getEngineBoost());
        values.put("image", powerCore.getImage());

        long id = db.insert("power_core", null, values);
        if(id >= 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean addBackgroundObject(BackgroundObject bgObject) {
        ContentValues values = new ContentValues();
        values.put("path", bgObject.getImage());

        long id = db.insert("bgobject", null, values);
        if(id >= 0) {
            bgObject.setId(id);
            return true;
        }
        else {
            return false;
        }
    }

    // Get methods

    public Asteroid getAsteroid(int AsteroidId) {
        final String SQL = "SELECT id, name, image, image_width, image_height, type FROM asteroid WHERE id = "+AsteroidId;
        Asteroid asteroid = null;
        Cursor cursor = db.rawQuery(SQL, new String[]{});
        try {
            cursor.moveToFirst();

            String name = cursor.getString(1);
            String image = cursor.getString(2);
            int imageWidth = cursor.getInt(3);
            int imageHeight = cursor.getInt(4);
            String type = cursor.getString(5);

            asteroid = new Asteroid(name, image, imageWidth, imageHeight, type);
        }
        finally {
            cursor.close();
        }
        return asteroid;
    }

    public Level getLevel(int levelNumber) {
        final String SQL = "SELECT number, title, hint, width, height, music FROM level WHERE number = "+levelNumber;
        Level level = null;
        Cursor cursor = db.rawQuery(SQL, new String[]{});
        Set<LevelObject> levelObjects = getLevelObjects(levelNumber);
        Set<LevelAsteroid> levelAsteroids = getLevelAsteroids(levelNumber);

        try {
            cursor.moveToFirst();
            int number = cursor.getInt(0);
            String title = cursor.getString(1);
            String hint = cursor.getString(2);
            int width = cursor.getInt(3);
            int height = cursor.getInt(4);
            String music = cursor.getString(5);

            level = new Level(levelAsteroids, title, hint, width, music, levelObjects, height, number);
        }
        finally {
            cursor.close();
        }
        return level;
    }

    private Set<LevelObject> getLevelObjects(int levelNumber) {
        Set<LevelObject> levelObjects = new HashSet<>();
        final String SQL = "SELECT position, object_id, scale FROM level_object WHERE level_id = "+levelNumber;
        Cursor cursor = db.rawQuery(SQL, new String[]{});
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {

                String position = cursor.getString(0);
                int objectId = cursor.getInt(1);
                double scale = cursor.getDouble(2);

                LevelObject levelObject = new LevelObject(position, scale, objectId);
                levelObjects.add(levelObject);
                cursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return levelObjects;
    }

    private Set<LevelAsteroid> getLevelAsteroids(int levelNumber) {
        Set<LevelAsteroid> levelAsteroids = new HashSet<>();
        final String SQL = "SELECT number, asteroid_id FROM level_asteroid WHERE level_id = "+levelNumber;
        Cursor cursor = db.rawQuery(SQL, new String[]{});
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {

                int number = cursor.getInt(0);
                int asteroidId = cursor.getInt(1);

                LevelAsteroid levelAsteroid = new LevelAsteroid(number, asteroidId);
                levelAsteroids.add(levelAsteroid);
                cursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return levelAsteroids;
    }

    public BackgroundObject getBackgroundObject(int index) {
        final String SQL = "SELECT path FROM bgobject WHERE id = "+index;
        BackgroundObject backgroundObject = null;
        Cursor cursor = db.rawQuery(SQL, new String[] {});
        try {
            cursor.moveToFirst();
            backgroundObject = new BackgroundObject(cursor.getString(0));
        }
        finally {
            cursor.close();
        }
        return backgroundObject;
    }

    public List<MainBody> getMainBodies() {
        final String SQL = "SELECT * FROM main_body";
        List<MainBody> mainBodies = new ArrayList<>();
        Cursor cursor = db.rawQuery(SQL, new String[]{});
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String cannonAttach = cursor.getString(0);
                String engineAttach = cursor.getString(1);
                String extraAttach = cursor.getString(2);
                String image = cursor.getString(3);
                int imageWidth = cursor.getInt(4);
                int imageHeight = cursor.getInt(5);

                MainBody mainBody = new MainBody(cannonAttach, engineAttach, extraAttach, image, imageWidth, imageHeight);
                mainBodies.add(mainBody);
                cursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return mainBodies;
    }

    public List<Cannon> getCannons() {
        final String SQL = "SELECT * FROM cannon";
        List<Cannon> cannons = new ArrayList<>();
        Cursor cursor = db.rawQuery(SQL, new String[]{});
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String attachPoint = cursor.getString(0);
                String emitPoint = cursor.getString(1);
                String image = cursor.getString(2);
                int imageWidth = cursor.getInt(3);
                int imageHeight = cursor.getInt(4);
                String attackImage = cursor.getString(5);
                int attackImageWidth = cursor.getInt(6);
                int attackImageHeight = cursor.getInt(7);
                String attackSound = cursor.getString(8);
                int damage = cursor.getInt(9);

                Cannon cannon = new Cannon(attachPoint, emitPoint, image, imageWidth, imageHeight, attackImage, attackImageWidth, attackImageHeight, attackSound, damage);
                cannons.add(cannon);

                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return cannons;
    }

    public List<ExtraPart> getExtraParts() {
        final String SQL = "SELECT * FROM extra_part";
        List<ExtraPart> extraParts = new ArrayList<>();
        Cursor cursor = db.rawQuery(SQL, new String[]{});
        try {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                String attachPoint = cursor.getString(0);
                String image = cursor.getString(1);
                int imageWidth = cursor.getInt(2);
                int imageHeight = cursor.getInt(3);

                ExtraPart extraPart = new ExtraPart(attachPoint, image, imageWidth, imageHeight);
                extraParts.add(extraPart);

                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return extraParts;
    }

    public List<Engine> getEngines() {
        final String SQL = "SELECT * FROM engine";
        List<Engine> engines = new ArrayList<>();
        Cursor cursor = db.rawQuery(SQL, new String[]{});
        try {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                int baseSpeed = cursor.getInt(0);
                int baseTurnRate = cursor.getInt(1);
                String attachPoint = cursor.getString(2);
                String image = cursor.getString(3);
                int imageWidth = cursor.getInt(4);
                int imageHeight = cursor.getInt(5);

                Engine engine = new Engine(baseSpeed, baseTurnRate, attachPoint, image, imageWidth, imageHeight);
                engines.add(engine);

                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return engines;
    }

    public List<PowerCore> getPowerCores() {
        final String SQL = "SELECT * FROM power_core";
        List<PowerCore> powerCores = new ArrayList<>();
        Cursor cursor = db.rawQuery(SQL, new String[]{});
        try {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                int cannonBoost = cursor.getInt(0);
                int engineBoost = cursor.getInt(1);
                String image = cursor.getString(2);

                PowerCore powerCore = new PowerCore(cannonBoost, engineBoost, image);
                powerCores.add(powerCore);

                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return powerCores;
    }

    /**
     * Resets all the tables in the Database to what they were before
     */
    public void resetAll() {
        // DROP all the tables
        db.execSQL(DELETE_ASTEROID_TABLE_IF_EXISTS);
        db.execSQL(DELETE_BGOBJECT_TABLE_IF_EXISTS);
        db.execSQL(DELETE_LEVEL_TABLE_IF_EXISTS);
        db.execSQL(DELETE_LEVEL_OBJECT_TABLE_IF_EXISTS);
        db.execSQL(DELETE_LEVEL_ASTEROID_TABLE_IF_EXISTS);
        db.execSQL(DELETE_MAIN_BODY_TABLE_IF_EXISTS);
        db.execSQL(DELETE_CANNON_TABLE_IF_EXISTS);
        db.execSQL(DELETE_EXTRA_PART_TABLE_IF_EXISTS);
        db.execSQL(DELETE_ENGINE_TABLE_IF_EXISTS);
        db.execSQL(DELETE_POWER_CORE_TABLE_IF_EXISTS);

        // RECREATE the tables now
        db.execSQL(DbOpenHelper.CREATE_ASTEROID_TABLE);
        db.execSQL(DbOpenHelper.CREATE_BACKGROUND_OBJECT_TABLE);
        db.execSQL(DbOpenHelper.CREATE_LEVEL_TABLE);
        db.execSQL(DbOpenHelper.CREATE_LEVEL_OBJECT_TABLE);
        db.execSQL(DbOpenHelper.CREATE_LEVEL_ASTEROID_TABLE);
        db.execSQL(DbOpenHelper.CREATE_MAIN_BODY_TABLE);
        db.execSQL(DbOpenHelper.CREATE_CANNON_TABLE);
        db.execSQL(DbOpenHelper.CREATE_EXTRA_PART_TABLE);
        db.execSQL(DbOpenHelper.CREATE_ENGINE_TABLE);
        db.execSQL(DbOpenHelper.CREATE_POWER_CORE_TABLE);

    }

    private static final String DELETE_ASTEROID_TABLE_IF_EXISTS = "DROP TABLE IF EXISTS asteroid;";
    private static final String DELETE_BGOBJECT_TABLE_IF_EXISTS = "DROP TABLE IF EXISTS bgobject;";
    private static final String DELETE_LEVEL_TABLE_IF_EXISTS = "DROP TABLE IF EXISTS level;";
    private static final String DELETE_LEVEL_OBJECT_TABLE_IF_EXISTS = "DROP TABLE IF EXISTS level_object;";
    private static final String DELETE_LEVEL_ASTEROID_TABLE_IF_EXISTS = "DROP TABLE IF EXISTS level_asteroid;";
    private static final String DELETE_MAIN_BODY_TABLE_IF_EXISTS = "DROP TABLE IF EXISTS main_body;";
    private static final String DELETE_CANNON_TABLE_IF_EXISTS = "DROP TABLE IF EXISTS cannon;";
    private static final String DELETE_EXTRA_PART_TABLE_IF_EXISTS = "DROP TABLE IF EXISTS extra_part;";
    private static final String DELETE_ENGINE_TABLE_IF_EXISTS = "DROP TABLE IF EXISTS engine;";
    private static final String DELETE_POWER_CORE_TABLE_IF_EXISTS = "DROP TABLE IF EXISTS power_core;";
}
