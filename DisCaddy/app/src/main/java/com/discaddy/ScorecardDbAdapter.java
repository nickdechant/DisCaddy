package com.discaddy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ScorecardDbAdapter {

    private String[] keys = {"_id", "player","hole_one","hole_two","hole_three","hole_four","hole_five","hole_six","hole_seven","hole_eight","hole_nine","hole_ten","hole_eleven","hole_twelve","hole_thirteen","hole_fourteen","hole_fifteen","hole_sixteen","hole_seventeen","hole_eighteen"};
    private static final String TAG = "ScorecardDbAdapter";
    private DatabaseHelper mDbHelperScorecard;
    private SQLiteDatabase mDbScoreCard;

    /**
     * Database creation sql statement
     */
    private static final String DATABASE_CREATE =
            "CREATE TABLE player (" +
                    "_id INTEGER PRIMARY KEY," +
                    "player TEXT," +
                    "hole_one INTEGER," +
                    "hole_two INTEGER," +
                    "hole_three INTEGER," +
                    "hole_four INTEGER," +
                    "hole_five INTEGER," +
                    "hole_six INTEGER," +
                    "hole_seven INTEGER," +
                    "hole_eight INTEGER," +
                    "hole_nine INTEGER," +
                    "hole_ten INTEGER," +
                    "hole_eleven INTEGER," +
                    "hole_twelve INTEGER," +
                    "hole_thirteen INTEGER," +
                    "hole_fourteen INTEGER," +
                    "hole_fifteen INTEGER," +
                    "hole_sixteen INTEGER," +
                    "hole_seventeen INTEGER," +
                    "hole_eighteen INTEGER)";

    private static final String DATABASE_NAME = "scorecards";
    private static final String DATABASE_TABLE = "zilker";
    private static final int DATABASE_VERSION = 1;

    private final Context mCtx;

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(DATABASE_CREATE);
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
        mDbHelperScorecard = new DatabaseHelper(mCtx);
        mDbScoreCard = mDbHelperScorecard.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelperScorecard.close();
    }


    /**
     * Create a new player for this course using the information provided. If the player is
     * successfully created return the new rowId for that note, otherwise return
     * a -1 to indicate failure.
     *
     *
     * @return rowId or -1 if failed
     */
    public long createPlayer(String name) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(keys[1], name);
        for(int i = 2; i < keys.length; i++)
            initialValues.put(keys[i], 0);
        return mDbScoreCard.insert(DATABASE_TABLE, null, initialValues);
    }

    /**
     * Delete the player with the given rowId
     *
     * @param rowId id of note to delete
     * @return true if deleted, false otherwise
     */
    public boolean deletePlayer(long rowId) {

        return mDbScoreCard.delete(DATABASE_TABLE, keys[0] + "=" + rowId, null) > 0;
    }

    /**
     * Return a Cursor over the list of all players in the database
     *
     * @return Cursor over all notes
     */
    public Cursor fetchAllPlayers() {

        return mDbScoreCard.query(DATABASE_TABLE, keys, null, null, null, null, null);
    }

    /**
     * Return a Cursor positioned at the note that matches the given rowId
     *
     * @param rowId id of player to retrieve
     * @return Cursor positioned to matching player, if found
     * @throws SQLException if note could not be found/retrieved
     */
    public Cursor fetchPlayer(long rowId) throws SQLException {

        Cursor mCursor =

                mDbScoreCard.query(true, DATABASE_TABLE, keys, keys[0] + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

    /**
     * Update the player using the details provided. The player to be updated is
     * specified using the rowId, and it is altered to use the title and body
     * values passed in
     *
     * @return true if the player was successfully updated, false otherwise
     */
    public boolean updatePlayerScore(long rowId, String name, int[] scores) {
        ContentValues args = new ContentValues();
        args.put(keys[1], name);
        for(int i = 2; i < keys.length; i++)
            args.put(keys[i], scores[i]);
        return mDbScoreCard.update(DATABASE_TABLE, args, keys[0] + "=" + rowId, null) > 0;
    }
}