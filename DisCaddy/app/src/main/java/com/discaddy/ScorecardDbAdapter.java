package com.discaddy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ScorecardDbAdapter {
    //public static final String KEY_Course_Name = "course_name"; //Don't know if I need this yet.

    public static final String KEY_Hole_One = "hole_one";
    public static final String KEY_Hole_Two = "hole_two";
    public static final String KEY_Hole_Three = "hole_three";
    public static final String KEY_Hole_Four = "hole_four";
    public static final String KEY_Hole_Five = "hole_five";
    public static final String KEY_Hole_Six = "hole_six";
    public static final String KEY_Hole_Seven = "hole_seven";
    public static final String KEY_Hole_Eight = "hole_eight";
    public static final String KEY_Hole_Nine = "hole_nine";
    public static final String KEY_Hole_Ten = "hole_ten";
    public static final String KEY_Hole_Eleven = "hole_eleven";
    public static final String KEY_Hole_Twelve = "hole_twelve";
    public static final String KEY_Hole_Thirteen = "hole_thirteen";
    public static final String KEY_Hole_Fourteen = "hole_fourteen";
    public static final String KEY_Hole_Fifteen = "hole_fifteen";
    public static final String KEY_Hole_Sixteen = "hole_sixteen";
    public static final String KEY_Hole_Seventeen = "hole_seventeen";
    public static final String KEY_Hole_Eighteen = "hole_eighteen";
    public static final String KEY_Hole_Nineteen = "hole_nineteen";
    public static final String KEY_Player = "player";
    public static final String KEY_ROWID = "_id";
    private String[] keys = {KEY_ROWID, KEY_Player,KEY_Hole_One, KEY_Hole_Two, KEY_Hole_Three,KEY_Hole_Four,KEY_Hole_Five,KEY_Hole_Six,KEY_Hole_Seven,KEY_Hole_Eight,KEY_Hole_Nine,KEY_Hole_Ten,KEY_Hole_Eleven,KEY_Hole_Twelve,KEY_Hole_Thirteen,KEY_Hole_Fourteen,KEY_Hole_Fifteen,KEY_Hole_Sixteen,KEY_Hole_Seventeen,KEY_Hole_Eighteen};


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
        initialValues.put(KEY_Player, name);
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

        return mDbScoreCard.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
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

                mDbScoreCard.query(true, DATABASE_TABLE, keys, KEY_ROWID + "=" + rowId, null,
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
        args.put(KEY_Player, name);
        for(int i = 0; i < keys.length; i++)
            args.put(keys[i], scores[i]);
        return mDbScoreCard.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
}