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
    public static final String CREATE_ASTEROID_TABLE = "";

    /**
     * SQL CREATE TABLE statement for LEVEL
     */
    public static final String CREATE_LEVEL_TABLE = "";

    /**
     * SQL CREATE TABLE statement for LEVEL_OBJECT
     */
    public static final String CREATE_LEVEL_OBJECT_TABLE = "";

    /**
     * SQL CREATE TABLE statement for LEVEL_ASTEROID
     */
    public static final String CREATE_LEVEL_ASTEROID_TABLE = "";

    /**
     * SQL CREATE TABLE statement for MAIN_BODY
     */
    public static final String CREATE_MAIN_BODY_TABLE = "";

    /**
     * SQL CREATE TABLE statement for CANNON
     */
    public static final String CREATE_CANNON_TABLE = "";

    /**
     * SQL CREATE TABLE statement for EXTRA_PART
     */
    public static final String CREATE_EXTRA_PART_TABLE = "";

    /**
     * SQL CREATE TABLE statement for ENGINE
     */
    public static final String CREATE_ENGINE_TABLE = "";

    /**
     * SQL CREATE TABLE statement for POWER_CORE
     */
    public static final String CREATE_POWER_CORE_TABLE = "";

    /**
     * SQL CREATE TABLE statement for BACKGROUND_OBJECT
     */
    public static final String CREATE_BACKGROUND_OBJECT_TABLE = "";
}
