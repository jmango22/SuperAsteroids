package edu.byu.cs.superasteroids.database;

import android.database.sqlite.SQLiteDatabase;


/**
 * Created by Jon on 10/10/2016.
 * Add* methods
 * GetAll* methods
 * ClearAll method
 */
public class DAO {
    public static final DAO SINGLETON = new DAO();

    private SQLiteDatabase db;

    public DAO() {db = null;}
    public DAO(SQLiteDatabase db) {
        this.db = db;
    }

    public void setDB(SQLiteDatabase db) {
        this.db = db;
    }

    // Add methods

    /**
     * Adds an Object
     * @param type - String type of object
     * @param object - The object
     * @return True: Success False: Fail
     */
    public boolean addObject(String type, Object object) {
        return false;
    }

    /**
     * Gets Object of Type specified
     * @param type - String type of object
     * @return Object
     */
    // Get methods
    public Object getObject(String type) {
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
     * Resets all the objects of Type type
     * @param type - Type of Object
     */
    // Clear All
    public void emptyObject(String type) {}

    /**
     * SQL TO DROP - Changes with Type
     */
    private static final String DELETE_ASTEROID_TABLE_IF_EXISTS = "";



}
