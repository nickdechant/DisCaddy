package com.discaddy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Map;

public class DisCaddyDbAdapter {

    private static final String TAG = "DisCaddyDbAdapter";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    //Database name
    private static final String DATABASE_NAME = "data";
    //table names
    private static final String PLAYER_TABLE = "player";
    private static final String SCORE_TABLE = "score";
    private static final String COURSE_TABLE = "course";
    //Column names for all tables
    private static final String KEY_ID = "_id";
    private static final String KEY_CREATED_AT = "created_at";
    static final String KEY_NAME = "name";
    static final String KEY_COURSE = "course";
    //Column names for PLAYER_TABLE
    private static final String KEY_DISK = "disk";
    private static final String KEY_AVE_SCORE = "avgScore";
    private static final String KEY_LOW_SCORE = "lowScore";
    private static final String KEY_LOW_COURSE = "lowCourse";
    private static final String KEY_IMAGE = "image";
    String player_table_values[] = {KEY_ID, KEY_CREATED_AT, KEY_NAME, KEY_COURSE, KEY_DISK, KEY_AVE_SCORE,
                                    KEY_LOW_SCORE, KEY_LOW_COURSE, KEY_IMAGE};
    //Column names for SCORE_TABLE
    private static final String KEY_CARD_NAME = "cardName";
    private static final String KEY_ONE = "hole1";
    private static final String KEY_TWO = "hole2";
    private static final String KEY_THREE = "hole3";
    private static final String KEY_FOUR = "hole4";
    private static final String KEY_FIVE = "hole5";
    private static final String KEY_SIX = "hole6";
    private static final String KEY_SEVEN = "hole7";
    private static final String KEY_EIGHT = "hole8";
    private static final String KEY_NINE = "hole9";
    private static final String KEY_TEN = "hole10";
    private static final String KEY_ELEVEN = "hole11";
    private static final String KEY_TWELVE = "hole12";
    private static final String KEY_THIRTEEN = "hole13";
    private static final String KEY_FOURTEEN = "hole14";
    private static final String KEY_FIFTEEN = "hole15";
    private static final String KEY_SIXTEEN = "hole16";
    private static final String KEY_SEVENTEEN = "hole17";
    private static final String KEY_EIGHTEEN = "hole18";
    String score_table_values[] = {KEY_ID, KEY_CREATED_AT, KEY_NAME, KEY_COURSE, KEY_CARD_NAME, KEY_ONE, KEY_TWO,
                                    KEY_THREE, KEY_FOUR, KEY_FIVE, KEY_SIX, KEY_SEVEN, KEY_EIGHT, KEY_NINE, KEY_TEN,
                                    KEY_ELEVEN, KEY_TWELVE, KEY_THIRTEEN, KEY_FOURTEEN, KEY_FIFTEEN, KEY_SIXTEEN,
                                    KEY_SEVENTEEN, KEY_EIGHTEEN};
    //Column names for COURSE_TABLE
    private static final String KEY_PAR_ONE = "par1";
    private static final String KEY_PAR_TWO = "par2";
    private static final String KEY_PAR_THREE = "par3";
    private static final String KEY_PAR_FOUR = "par4";
    private static final String KEY_PAR_FIVE = "par5";
    private static final String KEY_PAR_SIX = "par6";
    private static final String KEY_PAR_SEVEN = "par7";
    private static final String KEY_PAR_EIGHT = "par8";
    private static final String KEY_PAR_NINE = "par9";
    private static final String KEY_PAR_TEN = "par10";
    private static final String KEY_PAR_ELEVEN = "par11";
    private static final String KEY_PAR_TWELVE = "par12";
    private static final String KEY_PAR_THIRTEEN = "par13";
    private static final String KEY_PAR_FOURTEEN = "par14";
    private static final String KEY_PAR_FIFTEEN = "par15";
    private static final String KEY_PAR_SIXTEEN = "par16";
    private static final String KEY_PAR_SEVENTEEN = "par17";
    private static final String KEY_PAR_EIGHTEEN = "par18";
    String course_table_values[] = {KEY_ID, KEY_CREATED_AT, KEY_COURSE, KEY_PAR_ONE, KEY_PAR_TWO, KEY_PAR_THREE, KEY_PAR_FOUR,
                                    KEY_PAR_FIVE, KEY_PAR_SIX, KEY_PAR_SEVEN, KEY_PAR_EIGHT, KEY_PAR_NINE, KEY_PAR_TEN, KEY_PAR_ELEVEN,
                                    KEY_PAR_TWELVE, KEY_PAR_THIRTEEN, KEY_PAR_FOURTEEN, KEY_PAR_FIFTEEN, KEY_PAR_SIXTEEN, KEY_PAR_SEVENTEEN,
                                    KEY_PAR_EIGHTEEN};

    //SQL table creation statements

    private static final String CREATE_PLAYER =
            "CREATE TABLE "+ PLAYER_TABLE +" (" +
                    KEY_ID + " INTEGER PRIMARY KEY," +
                    KEY_CREATED_AT +" TEXT," +
                    KEY_NAME +" TEXT," +
                    KEY_COURSE +" TEXT," +
                    KEY_AVE_SCORE +" TEXT," +
                    KEY_LOW_SCORE +" TEXT," +
                    KEY_LOW_COURSE +" TEXT," +
                    KEY_DISK +" TEXT," +
                    KEY_IMAGE +" TEXT);";
    private static final String CREATE_SCORE =
            "CREATE TABLE " + SCORE_TABLE +" (" +
                    KEY_ID + " INTEGER PRIMARY KEY," +
                    KEY_CREATED_AT +" TEXT," +
                    KEY_NAME +" TEXT," +
                    KEY_COURSE +" TEXT," +
                    KEY_CARD_NAME +" TEXT," +
                    KEY_ONE +" INTEGER," +
                    KEY_TWO +" INTEGER," +
                    KEY_THREE +" INTEGER," +
                    KEY_FOUR +" INTEGER," +
                    KEY_FIVE +" INTEGER," +
                    KEY_SIX +" INTEGER," +
                    KEY_SEVEN +" INTEGER," +
                    KEY_EIGHT +" INTEGER," +
                    KEY_NINE +" INTEGER," +
                    KEY_TEN +" INTEGER," +
                    KEY_ELEVEN +" INTEGER," +
                    KEY_TWELVE +" INTEGER," +
                    KEY_THIRTEEN +" INTEGER," +
                    KEY_FOURTEEN +" INTEGER," +
                    KEY_FIFTEEN +" INTEGER," +
                    KEY_SIXTEEN +" INTEGER," +
                    KEY_SEVENTEEN +" INTEGER," +
                    KEY_EIGHTEEN +" INTEGER);";
    private static final String CREATE_COURSE =
            "CREATE TABLE " + COURSE_TABLE +" (" +
                    KEY_ID + " INTEGER PRIMARY KEY," +
                    KEY_CREATED_AT +" TEXT," +
                    KEY_COURSE +" TEXT," +
                    KEY_PAR_ONE +" INTEGER," +
                    KEY_PAR_TWO +" INTEGER," +
                    KEY_PAR_THREE +" INTEGER," +
                    KEY_PAR_FOUR +" INTEGER," +
                    KEY_PAR_FIVE+" INTEGER," +
                    KEY_PAR_SIX+" INTEGER," +
                    KEY_PAR_SEVEN +" INTEGER," +
                    KEY_PAR_EIGHT +" INTEGER," +
                    KEY_PAR_NINE +" INTEGER," +
                    KEY_PAR_TEN +" INTEGER," +
                    KEY_PAR_ELEVEN +" INTEGER," +
                    KEY_PAR_TWELVE +" INTEGER," +
                    KEY_PAR_THIRTEEN +" INTEGER," +
                    KEY_PAR_FOURTEEN +" INTEGER," +
                    KEY_PAR_FIFTEEN +" INTEGER," +
                    KEY_PAR_SIXTEEN +" INTEGER," +
                    KEY_PAR_SEVENTEEN +" INTEGER," +
                    KEY_PAR_EIGHTEEN +" INTEGER);";

    private static final int DATABASE_VERSION = 1;

    private final Context mCtx;

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //create tables.
            db.execSQL(CREATE_PLAYER);
            db.execSQL(CREATE_SCORE);
            db.execSQL(CREATE_COURSE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");

            db.execSQL("DROP TABLE IF EXISTS " + CREATE_PLAYER);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_SCORE);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_COURSE);
            //create new tables.
            onCreate(db);
        }
    }

    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     *
     * @param ctx the Context within which to work
     */
    public DisCaddyDbAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    /**
     * Open the player database. If it cannot be opened, try to create a new
     * instance of the database. If it cannot be created, throw an exception to
     * signal the failure
     *
     * @return this (self reference, allowing this to be chained in an
     *         initialization call)
     * @throws SQLException if the database could be neither opened or created
     */
    public DisCaddyDbAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    /**
     * Delete the player with the given rowId
     *
     * @param rowId id of note to delete
     * @return true if deleted, false otherwise
     */
    public boolean deletePlayer(long rowId) {

        return mDb.delete(PLAYER_TABLE, KEY_ID + "=" + rowId, null) > 0;
    }

    /**
     * Return a Cursor over the list of all players in the database
     *
     * @return Cursor over all notes
     */
    public Cursor fetchAllPlayers() {
        Log.v(TAG,"made it to fetch all players");
        return mDb.query(PLAYER_TABLE, player_table_values, null, null, null, null, null);
    }

    /**
     * Return a Cursor positioned at the note that matches the given rowId
     *
     * @param rowId id of player to retrieve
     * @return Cursor positioned to matching player, if found
     * @throws SQLException if note could not be found/retrieved
     */
    public Cursor fetchPlayer(long rowId) throws SQLException {

        Cursor mCursor = mDb.query(true, PLAYER_TABLE, player_table_values,
                                    KEY_ID + "=" + rowId, null, null, null, null, null);
        if (mCursor != null)
            mCursor.moveToFirst();
        return mCursor;

    }

    /**
     * Create a new player using the information provided. If the player is
     * successfully created return the new rowId for that player, otherwise return
     * a -1 to indicate failure.
     */
    public long createPlayer(String name, String course, String aveScore, String lowScore,
                             String createdAt, String lowCourse, String disk, String imagepath) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_COURSE, course);
        initialValues.put(KEY_AVE_SCORE, aveScore);
        initialValues.put(KEY_LOW_SCORE, lowScore);
        initialValues.put(KEY_DISK, disk);
        initialValues.put(KEY_CREATED_AT, createdAt);
        initialValues.put(KEY_LOW_COURSE, lowCourse);
        initialValues.put(KEY_IMAGE, imagepath);
        return mDb.insert(PLAYER_TABLE, null, initialValues);
    }

    /**
     * Update the player using the details provided. The player to be updated is
     * specified using the rowId.
     */
    public boolean updatePlayer(long rowId, String name, String course, String aveScore, String lowScore,
                                String createdAt, String lowCourse, String disk, String imagepath) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_COURSE, course);
        initialValues.put(KEY_AVE_SCORE, aveScore);
        initialValues.put(KEY_LOW_SCORE, lowScore);
        initialValues.put(KEY_DISK, disk);
        initialValues.put(KEY_CREATED_AT, createdAt);
        initialValues.put(KEY_LOW_COURSE, lowCourse);
        initialValues.put(KEY_IMAGE, imagepath);
        return mDb.update(PLAYER_TABLE, initialValues, KEY_ID + "=" + rowId, null) > 0;
    }

    public long createCourse(String createdAt, String courseName, int pars[]){
        ContentValues initialValues = new ContentValues();
        initialValues.put(course_table_values[1], createdAt);
        initialValues.put(course_table_values[2], courseName);
        for(int i = 0; i < pars.length; i++)
            initialValues.put(course_table_values[i+3], pars[i]);
        return mDb.insert(COURSE_TABLE, null, initialValues);
    }

    public boolean updateCourse(long rowId ,String createdAt, String courseName, int pars[]){
        ContentValues initialValues = new ContentValues();
        initialValues.put(course_table_values[1], createdAt);
        initialValues.put(course_table_values[2], courseName);
        for(int i = 0; i < pars.length; i++)
            initialValues.put(course_table_values[i+3], pars[i]);
        return mDb.update(COURSE_TABLE, initialValues, KEY_ID + "=" + rowId, null) > 0;
    }

    public boolean deleteCourse(long rowId) {

        return mDb.delete(COURSE_TABLE, KEY_ID + "=" + rowId, null) > 0;
    }

    /**
     * Return a Cursor over the list of all players in the database
     *
     * @return Cursor over all notes
     */
    public Cursor fetchAllCourse() {

        return mDb.query(COURSE_TABLE, course_table_values, null, null, null, null, null);
    }

    /**
     * Return a Cursor positioned at the note that matches the given rowId
     *
     * @param rowId id of player to retrieve
     * @return Cursor positioned to matching player, if found
     * @throws SQLException if note could not be found/retrieved
     */
    public Cursor fetchCourse(long rowId) throws SQLException {

        Cursor mCursor = mDb.query(true, COURSE_TABLE, course_table_values,
                KEY_ID + "=" + rowId, null, null, null, null, null);
        if (mCursor != null)
            mCursor.moveToFirst();
        return mCursor;
    }

    public long createScoreCard(String createdAt, String name, String course, String cardName, int[] scores){
        ContentValues initialValues = new ContentValues();
        initialValues.put(score_table_values[1],createdAt);
        initialValues.put(score_table_values[2], name);
        initialValues.put(score_table_values[3], course);
        initialValues.put(score_table_values[4], cardName);
        for(int i = 0; i < scores.length; i++){
            initialValues.put(score_table_values[i+5], scores[i]);
        }
        return mDb.insert(SCORE_TABLE, null, initialValues);
    }

    public Cursor fetchAllScores() {

        return mDb.query(SCORE_TABLE, score_table_values, null, null, null, null, null);
    }

    public Cursor fetchScore(long rowId) throws SQLException {

        Cursor mCursor =

                mDb.query(true, SCORE_TABLE, score_table_values, KEY_ID + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }
}

