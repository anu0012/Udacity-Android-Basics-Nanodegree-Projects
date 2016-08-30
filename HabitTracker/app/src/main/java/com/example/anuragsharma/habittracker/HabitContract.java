package com.example.anuragsharma.habittracker;

import android.provider.BaseColumns;

/**
 * Created by anuragsharma on 23/08/16.
 */
public class HabitContract {

    public HabitContract(){}

    public static final String DATABASE_NAME = "HabitTracker.db";
    public static final int DATABASE_VERSION = 1;

    public class HabitTrackerEntry implements BaseColumns{
        public static final String TABLE_NAME = "Habits";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_DAYS = "days";
    }
}
