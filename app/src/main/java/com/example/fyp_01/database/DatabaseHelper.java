package com.example.fyp_01.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.fyp_01.activities.ActivitiesController;
import com.example.fyp_01.user.UserController;

public class  DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Database.db";
    public static final int VERSION_NAME = 8;
    public static final String TABLE_USER = "users_table";
    public static final String KEY_USER_ID = "users_id";
    public static final String KEY_USER_NAME = "users_name";
    public static final String KEY_USER_WORKOUT_GROUP = "users_workout_group";
    public static final String KEY_USER_GOAL = "users_goal";
    public static final String KEY_USER_DAYS_AVAILABLE = "users_days_available";
    public static final String KEY_USER_INTENSITY = "users_intensity";

    public static final String TABLE_ACTIVITIES = "activities_table";
    public static final String KEY_ACTIVITIES_ID = "activities_id";
    public static final String KEY_ACTIVITIES_NAME = "activities_name";
    public static final String KEY_ACTIVITIES_ACTIVITY_TYPE = "activities_type";
    public static final String KEY_ACTIVITIES_INTENSITY_LEVEL = "activities_intensity";
    public static final String KEY_ACTIVITIES_DAYS_PER_WEEK = "activities_days_per_week";
    public static final String KEY_ACTIVITIES_WORKOUT_LEVEL = "activities_workout_level";
    public static final String KEY_ACTIVITIES_EQUIPMENT_GROUP = "activities_equipment_group";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_USER + " (" + KEY_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_USER_NAME + " TEXT, " +
                KEY_USER_WORKOUT_GROUP + " TEXT, " + KEY_USER_GOAL + " TEXT, " + KEY_USER_DAYS_AVAILABLE + " INTEGER, " + KEY_USER_INTENSITY + " TEXT" +")");

        db.execSQL("CREATE TABLE " + TABLE_ACTIVITIES + " (" + KEY_ACTIVITIES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_ACTIVITIES_NAME + " TEXT, " +
                KEY_ACTIVITIES_ACTIVITY_TYPE + " TEXT, " + KEY_ACTIVITIES_WORKOUT_LEVEL + " TEXT, " + KEY_ACTIVITIES_DAYS_PER_WEEK + " INTEGER, " +
                KEY_ACTIVITIES_INTENSITY_LEVEL + " TEXT," + KEY_ACTIVITIES_EQUIPMENT_GROUP + " TEXT" +")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTIVITIES);
        onCreate(db);
    }

    public boolean addUserData(UserController user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_USER_NAME, user.getUserName());
        contentValues.put(KEY_USER_WORKOUT_GROUP, user.getWorkoutGroup());
        contentValues.put(KEY_USER_GOAL, user.getUserGoal());
        contentValues.put(KEY_USER_DAYS_AVAILABLE, user.getDaysAvailable());
        contentValues.put(KEY_USER_INTENSITY, user.getIntensity());


        long result = db.insert(TABLE_USER, null, contentValues);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean addActivityData(ActivitiesController activity){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ACTIVITIES_NAME, activity.getActivityName());
        contentValues.put(KEY_ACTIVITIES_ACTIVITY_TYPE, activity.getActivityType());
        contentValues.put(KEY_ACTIVITIES_WORKOUT_LEVEL, activity.getWorkoutLvl());
        contentValues.put(KEY_ACTIVITIES_DAYS_PER_WEEK, activity.getDaysPerWeek());
        contentValues.put(KEY_ACTIVITIES_INTENSITY_LEVEL, activity.getIntensityLvl());
        contentValues.put(KEY_ACTIVITIES_EQUIPMENT_GROUP, activity.getEquipmentGroup());


        long result = db.insert(TABLE_USER, null, contentValues);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }
}
