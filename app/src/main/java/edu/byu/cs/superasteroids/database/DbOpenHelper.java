package edu.byu.cs.superasteroids.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jon on 10/10/2016.
 * Stores database name, version
 * creates schema when database is created for the first time
 */
public class DbOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "super_asteroids.sqlite";
    private static final int DB_VERSION = 1;

    public DbOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ASTEROID_TABLE);
        db.execSQL(CREATE_LEVEL_TABLE);
        db.execSQL(CREATE_LEVEL_OBJECT_TABLE);
        db.execSQL(CREATE_LEVEL_ASTEROID_TABLE);
        db.execSQL(CREATE_MAIN_BODY_TABLE);
        db.execSQL(CREATE_CANNON_TABLE);
        db.execSQL(CREATE_EXTRA_PART_TABLE);
        db.execSQL(CREATE_ENGINE_TABLE);
        db.execSQL(CREATE_POWER_CORE_TABLE);
        db.execSQL(CREATE_BACKGROUND_OBJECT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        return;
    }

    /**
     * SQL CREATE TABLE statement for ASTEROID
     */
    public static final String CREATE_ASTEROID_TABLE = "CREATE TABLE asteroid ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, image TEXT NOT NULL, image_width INTEGER NOT NULL, image_height INTEGER NOT NULL, type TEXT NOT NULL );";

    /**
     * SQL CREATE TABLE statement for LEVEL
     */
    public static final String CREATE_LEVEL_TABLE = "CREATE TABLE level ( number INTEGER NOT NULL, title TEXT NOT NULL, hint TEXT NOT NULL, width INTEGER NOT NULL, height INTEGER NOT NULL, music TEXT NOT NULL );";

    /**
     * SQL CREATE TABLE statement for LEVEL_OBJECT
     */
    public static final String CREATE_LEVEL_OBJECT_TABLE = "CREATE TABLE level_object ( position TEXT NOT NULL, object_id INTEGER NOT NULL, scale REAL NOT NULL, level_id INTEGER NOT NULL);";

    /**
     * SQL CREATE TABLE statement for LEVEL_ASTEROID
     */
    public static final String CREATE_LEVEL_ASTEROID_TABLE = "CREATE TABLE level_asteroid ( number INTEGER NOT NULL, asteroid_id INTEGER NOT NULL, level_id INTEGER NOT NULL );";

    /**
     * SQL CREATE TABLE statement for MAIN_BODY
     */
    public static final String CREATE_MAIN_BODY_TABLE = "CREATE TABLE main_body ( cannon_attach TEXT NOT NULL, engine_attach TEXT NOT NULL, extra_attach TEXT NOT NULL, image TEXT NOT NULL, image_width INTEGER NOT NULL, image_height INTEGER NOT NULL );";

    /**
     * SQL CREATE TABLE statement for CANNON
     */
    public static final String CREATE_CANNON_TABLE = "CREATE TABLE cannon ( attach_point TEXT NOT NULL, emit_point TEXT NOT NULL, image TEXT NOT NULL, image_width INTEGER NOT NULL, image_height INTEGER NOT NULL, attack_image TEXT NOT NULL, attack_image_width INTEGER NOT NULL, attack_image_height INTEGER NOT NULL, attack_sound TEXT NOT NULL, damage INTEGER NOT NULL );";

    /**
     * SQL CREATE TABLE statement for EXTRA_PART
     */
    public static final String CREATE_EXTRA_PART_TABLE = "CREATE TABLE extra_part ( attach_point TEXT NOT NULL, image TEXT NOT NULL, image_width INTEGER NOT NULL, image_height INTEGER NOT NULL );";

    /**
     * SQL CREATE TABLE statement for ENGINE
     */
    public static final String CREATE_ENGINE_TABLE = "CREATE TABLE engine ( base_speed INTEGER NOT NULL, base_turn_rate INTEGER NOT NULL, attach_point TEXT NOT NULL, image TEXT NOT NULL, image_width INTEGER NOT NULL, image_height INTEGER NOT NULL );";

    /**
     * SQL CREATE TABLE statement for POWER_CORE
     */
    public static final String CREATE_POWER_CORE_TABLE = "CREATE TABLE power_core ( cannon_boost INTEGER NOT NULL, engine_boost INTEGER NOT NULL, image TEXT NOT NULL );";

    /**
     * SQL CREATE TABLE statement for BACKGROUND_OBJECT
     */
    public static final String CREATE_BACKGROUND_OBJECT_TABLE = "CREATE TABLE bgobject (id INTEGER PRIMARY KEY AUTOINCREMENT, path TEXT NOT NULL);";
}
