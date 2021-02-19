package com.example.fyp_01.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

import com.example.fyp_01.recommendations.Model;
import com.example.fyp_01.user.UserModel;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class  DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MyDatabase.db";

    private static final int VERSION_NAME = 10;
    private static final String TABLE_USER = "users_table";
    private static final String KEY_USER_ID = "users_id";
    private static final String KEY_USER_NAME = "users_name";
    private static final String KEY_USER_WORKOUT_GROUP = "users_workout_group";
    private static final String KEY_USER_GOAL = "users_goal";
    private static final String KEY_USER_INTENSITY = "users_intensity";
    private static final String KEY_USER_EQUIPMENT_GROUP = "users_equipment_group";

    private static final String TABLE_ACTIVITIES = "activities_table";
    private static final String KEY_ACTIVITIES_ID = "activities_id";
    private static final String KEY_ACTIVITIES_NAME = "activities_name";
    private static final String KEY_ACTIVITIES_ACTIVITY_TYPE = "activities_type";
    private static final String KEY_ACTIVITIES_INTENSITY_LEVEL = "activities_intensity";
    private static final String KEY_ACTIVITIES_WORKOUT_LEVEL = "activities_workout_level";
    private static final String KEY_ACTIVITIES_EQUIPMENT_GROUP = "activities_equipment_group";
    private static final String KEY_ACTIVITIES_IMAGE = "activities_image";

    private ByteArrayOutputStream objectByteArrayOutputStream;
    private byte[] imgInByte;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_USER + " (" + KEY_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_USER_NAME + " TEXT, " +
                KEY_USER_WORKOUT_GROUP + " TEXT, " + KEY_USER_GOAL + " TEXT, " + KEY_USER_INTENSITY + " TEXT, " + KEY_USER_EQUIPMENT_GROUP + " TEXT" +")");

        db.execSQL("CREATE TABLE " + TABLE_ACTIVITIES + " (" + KEY_ACTIVITIES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_ACTIVITIES_NAME + " TEXT, " +
                KEY_ACTIVITIES_ACTIVITY_TYPE + " TEXT, " + KEY_ACTIVITIES_WORKOUT_LEVEL + " TEXT, " +
                KEY_ACTIVITIES_INTENSITY_LEVEL + " TEXT," + KEY_ACTIVITIES_EQUIPMENT_GROUP + " TEXT, " + KEY_ACTIVITIES_IMAGE + " BLOB" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTIVITIES);
        onCreate(db);
    }

    public boolean addUserData(UserModel user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_USER_ID, 1);
        contentValues.put(KEY_USER_NAME, user.getUserNameData());
        contentValues.put(KEY_USER_WORKOUT_GROUP, user.getWorkoutGroupData());
        contentValues.put(KEY_USER_GOAL, user.getUserGoalData());
        contentValues.put(KEY_USER_INTENSITY, user.getIntensityData());
        contentValues.put(KEY_USER_EQUIPMENT_GROUP, user.getEquipmentGroupData());


        long result = db.insert(TABLE_USER, null, contentValues);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public void deleteUserData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_USER);
    }

    public boolean addActivityData(Model model){
        SQLiteDatabase db = this.getWritableDatabase();

        Bitmap imageToStoreBitmap = model.getActivitiesImage();
        objectByteArrayOutputStream = new ByteArrayOutputStream();
        imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG, 100, objectByteArrayOutputStream);
        imgInByte = objectByteArrayOutputStream.toByteArray();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ACTIVITIES_NAME, model.getActivitiesName());
        contentValues.put(KEY_ACTIVITIES_ACTIVITY_TYPE, model.getActivitiesType());
        contentValues.put(KEY_ACTIVITIES_WORKOUT_LEVEL, model.getWorkoutLvl());
        contentValues.put(KEY_ACTIVITIES_INTENSITY_LEVEL, model.getIntensityLvl());
        contentValues.put(KEY_ACTIVITIES_EQUIPMENT_GROUP, model.getEquipmentGroup());
        contentValues.put(KEY_ACTIVITIES_IMAGE, imgInByte);


        long result = db.insert(TABLE_ACTIVITIES, null, contentValues);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    //retrieve image_table data from database
    public ArrayList<Model> getActivitiesData() {
        ArrayList<Model> models = new ArrayList<>();
        Bitmap imageToRetrieve;
        String activityName, activityType, activityWorkoutLvl, activityIntensityLvl, activityEquipmentGroup;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from activities_table", null);

        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                //int id = cursor.getInt(0);
                activityName = cursor.getString(1);
                activityType = cursor.getString(2);
                activityWorkoutLvl = cursor.getString(3);
                activityIntensityLvl = cursor.getString(4);
                activityEquipmentGroup = cursor.getString(5);
                imgInByte = cursor.getBlob(6);
                imageToRetrieve = BitmapFactory.decodeByteArray(imgInByte, 0, imgInByte.length);
                Model Model = new Model(activityName, activityType, activityWorkoutLvl, activityIntensityLvl, activityEquipmentGroup, imageToRetrieve);
                models.add(Model);
            }
        }
        return models;
    }
}
