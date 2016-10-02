package com.example.a1449877.notelist.model;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

/**
 * Created by 1449877 on 2016-09-22.
 */
public class NoteTable implements CRUDRepository<Long, Note>{

    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_BODY = "body";
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_HAS_REMINDER = "hasReminder";
    private static final String COLUMN_REMINDER = "reminder";
    private static final String COLUMN_CREATED = "current";

    private SQLiteOpenHelper dbh;

    /**
     * Create a NoteTable with the DB handler
     * @param dbh
     */
    public NoteTable(SQLiteOpenHelper dbh) { this.dbh = dbh; }

    /**
     * Get the SQL CREATE TABLE statement for the note table.
     * @return SQL CREATE TABLE statement.
     */
    public String getCreateTableStatement() {
        return "CREATE TABLE note (\n" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                COLUMN_TITLE + " TEXT, \n" +
                COLUMN_BODY + " TEXT, \n" +
                COLUMN_CATEGORY + " INTEGER NOT NULL, \n" +
                COLUMN_HAS_REMINDER + " INTEGER NOT NULL, \n" +
                COLUMN_REMINDER + " TEXT, \n" +
                COLUMN_CREATED + " TEXT\n" +
                ");";
    }

    /**
     * Get the SQL DROP TABLE statement for the note table.
     * @return SQL DROP TABLE statement.
     */
    public String getDropTableStatement() { return "DROP TABLE IF EXISTS note"; }

    @Override
    public Long create(Note element) throws DatabaseException {
        SQLiteDatabase database = dbh.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, element.getTitle());
        values.put(COLUMN_BODY, element.getBody());
        values.put(COLUMN_CATEGORY, element.getCategory());
        values.put(COLUMN_HAS_REMINDER, element.isHasReminder());
        values.put(COLUMN_REMINDER, String.valueOf(element.getReminder()));
        values.put(COLUMN_CREATED, String.valueOf(element.getCreated()));

        long insertId = -1;

        try {
            insertId = database.insertOrThrow("note", null, values);
        }
        catch (SQLException e) {
            throw new DatabaseException(e);
        }
        finally {
            database.close();
        }

        return insertId;
    }

    @Override
    public Note read(Long key) throws DatabaseException {
        return null;
    }

    @Override
    public List<Note> readAll() throws DatabaseException {
        return null;
    }

    @Override
    public boolean update(Note element) throws DatabaseException {
        return false;
    }

    @Override
    public boolean delete(Note element) throws DatabaseException {
        return false;
    }

    /**
     * Check that the table has initial data.
     * @return
     */
    public boolean hasInitialData() { return true; }

    /**
     * Populate table with initial data.
     * Precondition: table has been created.
     * Note: this method is called during the handler's onCreate method where a writable database is open
     *       trying to get a second writable database will throw an error, hence the parameter.
     * @param db
     */
    public void initialize(SQLiteDatabase db) {

    }
}
