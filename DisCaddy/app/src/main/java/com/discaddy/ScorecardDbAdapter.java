package com.discaddy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by nick on 10/28/14.
 */
public class ScorecardDbAdapter {

//    public static final String KEY_Name = "name";
//    public static final String KEY_Course = "course";
//    public static final String KEY_Score = "score";
//    public static final String KEY_Disk = "disk";
//    public static final String KEY_ROWID = "_id";

    public static final String KEY_GameName = "game_name";
    public static final String KEY_CourseName = "course_name";
    public static final String KEY_Players = "players";


    public static final String KEY_ROWID = "_id";

    private static final String TAG = "ScorecardDbAdapter";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    /**
     * Database creation sql statement
     * could be an issue!!! I don't know sql
     */
//    private static final String DATABASE_CREATE =
//            "create table player (_id integer primary key autoincrement, "
//                    + "name text not null, course text not null, score text not null" +
//                    ", disk text not null);";


//    private static final String DATABASE_NAME = "data";
//    private static final String DATABASE_TABLE = "player";

    private static final String DATABASE_NAME = "scorecard_data";
    private static final String DATABASE_TABLE = "scorecard";
    private static final int DATABASE_VERSION = 1;

    private final Context mCtx;

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

//            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS player");
            onCreate(db);
        }
    }

    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     *
     * @param ctx the Context within which to work
     */
    public ScorecardDbAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    /**
     * Open the player database. If it cannot be opened, try to create a new
     * instance of the database. If it cannot be created, throw an exception to
     * signal the failure
     *
     * @return this (self reference, allowing this to be chained in an
     *         initialization call)
     * @throws android.database.SQLException if the database could be neither opened or created
     */
    public ScorecardDbAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    /**
     * Create a new player using the information provided. If the player is
     * successfully created return the new rowId for that note, otherwise return
     * a -1 to indicate failure.
     *
     *
     * @return rowId or -1 if failed
     */
    public long createScorecard(String name, String course, String score, String disk) {
        ContentValues initialValues = new ContentValues();
//        initialValues.put(KEY_Name, name);
//        initialValues.put(KEY_Course, course);
//        initialValues.put(KEY_Score, score);
//        initialValues.put(KEY_Disk, disk);

        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }

    /**
     * Delete the player with the given rowId
     *
     * @param rowId id of note to delete
     * @return true if deleted, false otherwise
     */
    public boolean deletePlayer(long rowId) {

        return mDb.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    /**
     * Return a Cursor over the list of all players in the database
     *
     * @return Cursor over all notes
     */
//    public Cursor fetchAllPlayers() {
//
//        return mDb.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_Name,
//                KEY_Course, KEY_Score, KEY_Disk}, null, null, null, null, null);
//    }

    /**
     * Return a Cursor positioned at the note that matches the given rowId
     *
     * @param rowId id of player to retrieve
     * @return Cursor positioned to matching player, if found
     * @throws SQLException if note could not be found/retrieved
     */
//    public Cursor fetchPlayer(long rowId) throws SQLException {
//
//        Cursor mCursor =
//
//                mDb.query(true, DATABASE_TABLE, new String[] {KEY_ROWID, KEY_Name,
//                                KEY_Course, KEY_Score, KEY_Disk}, KEY_ROWID + "=" + rowId, null,
//                        null, null, null, null);
//        if (mCursor != null) {
//            mCursor.moveToFirst();
//        }
//        return mCursor;
//
//    }

    /**
     * Update the player using the details provided. The player to be updated is
     * specified using the rowId, and it is altered to use the title and body
     * values passed in
     *
     * @return true if the player was successfully updated, false otherwise
     */
//    public boolean updatePlayer(long rowId, String name, String course, String score, String disk) {
//        ContentValues args = new ContentValues();
//        args.put(KEY_Name, name);
//        args.put(KEY_Course, course);
//        args.put(KEY_Score, score);
//        args.put(KEY_Disk, disk);
//
//        return mDb.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
//    }

}
