package com.example.a1449877.notelist.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by 1449877 on 2016-09-22.
 */
public class NoteDatabaseHandler extends SQLiteOpenHelper {

    /**
     * Filename to store the local database (on device)
     */
    private static final String DATABASE_FILE_NAME = "notes.db";

    /**
     * Update this field for every structural change to the database.
     */
    private static final int DATABASE_VERSION = 2;


    // TODO: maybe these could be replaced with a map of tables...

    /**
     * Contact database tables
     */
    private NoteTable noteTable;

    /**
     * Construct a new database handler.
     * @param context The application context.
     */
    public NoteDatabaseHandler(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
        noteTable = new NoteTable(this);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //  create tables
        db.execSQL(noteTable.getCreateTableStatement());

        //  populate tables as needed
        if (noteTable.hasInitialData()) {
            noteTable.initialize(db);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(DATABASE_FILE_NAME, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");

        // drop tables
        db.execSQL(noteTable.getDropTableStatement());

        // recreate DB
        onCreate(db);
    }

    /**
     * Getters
     */
    public NoteTable getNoteTable() {
        return noteTable;
    }
}
