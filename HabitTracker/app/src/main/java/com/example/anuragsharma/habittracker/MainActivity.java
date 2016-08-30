package com.example.anuragsharma.habittracker;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    HabitTrackerDbHelper mDbHelper;
    SQLiteDatabase db;
    ContentValues contentValues = new ContentValues();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDbHelper = new HabitTrackerDbHelper(this);
        db = mDbHelper.getWritableDatabase();
        insertHabit("Software Development","15");
        insertHabit("WWE", "20");
        insertHabit("Music", "5");
        updateHabit("Reading Books", "10");
        getHabit();
        deleteAllHabits();
    }

    public boolean insertHabit(String habit, String days){
        contentValues.put(HabitContract.HabitTrackerEntry.COLUMN_NAME_TITLE, habit);
        contentValues.put(HabitContract.HabitTrackerEntry.COLUMN_DAYS, days);
        long rowID = db.insert(HabitContract.HabitTrackerEntry.TABLE_NAME,null,contentValues);
        return rowID != -1;
    }

    public Cursor getHabit(){
        Cursor cursor = db.rawQuery("SELECT * FROM " + HabitContract.HabitTrackerEntry.TABLE_NAME, null);
        return cursor;
    }

    public boolean updateHabit(String habit, String days){
        contentValues.put(HabitContract.HabitTrackerEntry.COLUMN_DAYS, days);
        db.update(HabitContract.HabitTrackerEntry.TABLE_NAME,contentValues, HabitContract.HabitTrackerEntry.COLUMN_NAME_TITLE+"= ?",new String[]{habit});
        return true;
    }

    public Integer deleteAllHabits(){
        return db.delete(HabitContract.HabitTrackerEntry.TABLE_NAME,null,null);
    }
}
