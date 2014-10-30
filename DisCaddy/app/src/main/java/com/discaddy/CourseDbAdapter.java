package com.discaddy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by nick on 10/29/14.
 */
public class CourseDbAdapter {

    public static final String KEY_Course = "course";

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    public static final String KEY_ROWID = "_id";
    public static final String KEY_COURSENAME = "name";
    public static final String hole1 = "hole1", hole2 = "hole2", hole3 = "hole3", hole4 = "hole4", hole5 = "hole5", hole6 = "hole6",
                               hole7 = "hole7", hole8 = "hole8", hole9 = "hole9", hole10 = "hole10", hole11 = "hole11", hole12 = "hole12",
                               hole13 = "hole13", hole14 = "hole14", hole15 = "hole15", hole16 = "hole16", hole17 = "hole17", hole18 = "hole18";

    public static final String[] holes = {"hole1", "hole2", "hole3", "hole4", "hole5", "hole6", "hole7", "hole8", "hole9", "hole10",
                                          "hole11", "hole12", "hole13", "hole14", "hole15", "hole16", "hole17", "hole18"};

    private static final String DATABASE_NAME = "courses";
    private static final String DATABASE_TABLE = "course";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE =
            "CREATE TABLE course (" +
                    "_id INTEGER PRIMARY KEY," +
                    "hole1 TEXT," +
                    "hole2 TEXT," +
                    "hole3 TEXT," +
                    "hole4 TEXT," +
                    "hole5 TEXT," +
                    "hole6 TEXT," +
                    "hole7 TEXT," +
                    "hole8 TEXT," +
                    "hole9 TEXT," +
                    "hole10 TEXT," +
                    "hole11 TEXT," +
                    "hole12 TEXT," +
                    "hole13 TEXT," +
                    "hole14 TEXT," +
                    "hole15 TEXT," +
                    "hole16 TEXT," +
                    "hole17 TEXT," +
                    "hole18 TEXT)";

    private static final String LOG_TAG = "CourseDbAdapterTag";

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
            Log.w(LOG_TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS course");
            onCreate(db);
        }
    }

    public CourseDbAdapter(Context ctx) {this.mCtx = ctx;}

    public CourseDbAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    public long createCourse(String name, String[] pars) {
        ContentValues initialValues = new ContentValues();

        initialValues.put(KEY_COURSENAME, name);
        for (int i=0; i<pars.length; i++)
            initialValues.put(holes[i], pars[i]);

        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }

    public Cursor fetchAllCourses() {
        String[] args = new String[20];
        args[0] = KEY_ROWID;
        args[1] = KEY_COURSENAME;
        for (int i=2; i<20; i++)
            args[i] = holes[i-2];
        return mDb.query(DATABASE_TABLE, args, null, null, null, null, null);
    }

    public Cursor fetchCourse(long rowId) throws SQLException {
        String[] args = new String[20];
        args[0] = KEY_ROWID;
        args[1] = KEY_COURSENAME;
        for (int i=2; i<20; i++)
            args[i] = holes[i-2];
        Cursor mCursor =

                mDb.query(true, DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }


    public boolean updateCourse(long rowId, String name, String[] pars) {
        ContentValues args = new ContentValues();
        args.put(KEY_COURSENAME, name);
        for (int i=0; i<pars.length; i++)
            args.put(holes[i], pars[i]);

        return mDb.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }

}
