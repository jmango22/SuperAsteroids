package edu.byu.cs.superasteroids.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import java.util.HashSet;
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
 * Add* methods
 * GetAll* methods
 * ClearAll method
 */
public class SuperAsteroids_DAO {
    public static final SuperAsteroids_DAO SINGLETON = new SuperAsteroids_DAO();

    // TODO link the dbOpenhelper to the DAO, using the SINGLETON model
    private DbOpenHelper dbOpenHelper;

    private SQLiteDatabase db = dbOpenHelper.getWritableDatabase();

    private SuperAsteroids_DAO() {}

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
            return true;
        }
        else {
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
            boolean result = addLevelObject(levelObject);
            if(!result) {
                return false;
            }
        }

        // Add LevelAsteroids to their table
        for (LevelAsteroid levelAsteroid : level.getLevelAsteroids()) {
            boolean result = addLevelAsteroid(levelAsteroid);
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

    private boolean addLevelObject(LevelObject levelObject) {
        ContentValues values = new ContentValues();
        values.put("position", levelObject.getPosition());
        values.put("object_id", levelObject.getObjectId());
        values.put("scale", levelObject.getScale());

        long id = db.insert("level_object", null, values);
        if(id >= 0) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean addLevelAsteroid(LevelAsteroid levelAsteroid) {
        ContentValues values = new ContentValues();
        values.put("number", levelAsteroid.getNumber());
        values.put("asteroid_id", levelAsteroid.getAsteroidId());

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
        values.put("cannon_attach", mainBody.getConnonAttach());
        values.put("engine_attach", mainBody.getEngineAttach());
        values.put("extra_attach", mainBody.getExtraAttach());
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
        values.put("attach_point", cannon.getAttachPoint());
        values.put("emit_point", cannon.getEmitPoint());
        values.put("image", cannon.getImage());
        values.put("image_width", cannon.getImageWidth());
        values.put("image_height", cannon.getImageHeight());
        values.put("attack_image", cannon.getAttackImage());
        values.put("attack_image_width", cannon.getAttackImageWidth());
        values.put("attack_image_height", cannon.getImageHeight());
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
        values.put("attach_point", extraPart.getAttach_point());
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
        values.put("base_speed", engine.getBase_speed());
        values.put("base_turn_rate", engine.getBase_turn_rate());
        values.put("attach_point", engine.getAttach_point());
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

        long id = db.insert("level_asteroid", null, values);
        if(id >= 0) {
            bgObject.setId(id);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Gets Object of Type specified
     * @param
     * @return Object
     */
    // Get methods
    public Asteroid getAsteroid(int id) {
        final String SQL = "SELECT id, name, image, image_width, image_height, type FROM asteroid WHERE id = "+id;
        return null;
    }

    /**
     * Updates an Object
     * @param type - String type of object
     * @param object - The object
     * @return True: Success False: Fail
     */
    // Update methods
    public boolean updateObject(String type, Object object) {
        return false;
    }

    /**
     * Deletes an Object
     * @param type - String type of object
     * @param object - The object
     * @return True: Success False: Fail
     */
    // Delete methods
    public boolean deleteObject(String type, Object object) {
        return false;
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
