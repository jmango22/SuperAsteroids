package edu.byu.cs.superasteroids.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by Jon on 10/10/2016.
 * Contains DbOpenHelper and DAO(s)
 * Creates DbOpenHelper, Gets DB, Sends DB to DAO(s)
 * Has ClearAll method that calls ClearAll method on each DAO
 * Implements ImportData method (i.e., Database is the data importer)
 */
public class Database {
    private DbOpenHelper dbOpenHelper;
    private SQLiteDatabase database;
    private Context baseContext;

    public SuperAsteroids_DAO dao;

    /**
     * Creates the Databse
     * @param baseContext - Type Context
     */
    public Database(Context baseContext) {}

    /**
     * Gets the DAO
     * @return - DAO
     */
    public SuperAsteroids_DAO getDAO() {
        return null;
    }

    /**
     * Used with importData to file the Tables
     * @param fileName
     */
    public void open(String fileName) {}

    private static String makeString(Reader reader) throws IOException {
        return null;
    }
}
