package com.example.anuragsharma.habittracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by anuragsharma on 23/08/16.
 */
public class HabitTrackerDbHelper extends SQLiteOpenHelper {

    private static final String TEXT_TYPE = " TEXT NOT NULL";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + HabitContract.HabitTrackerEntry.TABLE_NAME + " (" +
                    HabitContract.HabitTrackerEntry.COLUMN_NAME_ENTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    HabitContract.HabitTrackerEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    HabitContract.HabitTrackerEntry.COLUMN_DAYS+" INTEGER NOT NULL  );";
    public HabitTrackerDbHelper(Context context) {
        super(context, HabitContract.DATABASE_NAME, null, HabitContract.DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL("DROP TABLE IF EXISTS " + HabitContract.HabitTrackerEntry.TABLE_NAME);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public boolean deleteDatabase(Context context){
        return context.deleteDatabase(HabitContract.DATABASE_NAME);
    }

}
