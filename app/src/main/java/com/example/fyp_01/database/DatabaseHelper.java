package com.example.fyp_01.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.fyp_01.R;
import com.example.fyp_01.activities.ActivitiesController;
import com.example.fyp_01.recommendations.StretchingAdapter;
import com.example.fyp_01.recommendations.StretchingModel;
import com.example.fyp_01.user.UserController;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class  DatabaseHelper extends SQLiteOpenHelper {

    Context context;
    public static final String DATABASE_NAME = "Database.db";

    public static final int VERSION_NAME = 12;
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

    public static final String TABLE_ACTIVITIES_IMAGES = "activities_images_table";
    public static final String KEY_ACTIVITIES_IMAGE = "activities_image";

    private ByteArrayOutputStream objectByteArrayOutputStream;
    private byte[] imgInByte;

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

        db.execSQL("CREATE TABLE " + TABLE_ACTIVITIES_IMAGES + " (" + KEY_ACTIVITIES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_ACTIVITIES_NAME + " TEXT, " +
                KEY_ACTIVITIES_IMAGE + " BLOB" +")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTIVITIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTIVITIES_IMAGES);
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

    public void addActivitiesImageData(StretchingModel model){
        SQLiteDatabase db = this.getWritableDatabase();

        Bitmap imageToStoreBitmap = model.getStretchingActivitiesImage();
        objectByteArrayOutputStream = new ByteArrayOutputStream();
        imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG, 100, objectByteArrayOutputStream);
        imgInByte = objectByteArrayOutputStream.toByteArray();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ACTIVITIES_NAME, model.getStretchingActivitiesName());
        contentValues.put(KEY_ACTIVITIES_IMAGE, imgInByte);

        db.insert(TABLE_ACTIVITIES_IMAGES, null, contentValues);
    }
    //retrieve image_table data from database
    public ArrayList<StretchingModel> getActivitiesImageData(){
        ArrayList<StretchingModel> stretchingModels = new ArrayList<>();
        Bitmap imageToRetrieve;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from activities_images_table", null);

        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                //int id = cursor.getInt(0);
                String activityName = cursor.getString(1);
                imgInByte = cursor.getBlob(2);
                imageToRetrieve = BitmapFactory.decodeByteArray(imgInByte, 0, imgInByte.length);
                StretchingModel stretchingModel = new StretchingModel(activityName, imageToRetrieve);
                stretchingModels.add(stretchingModel);
            }
        }
        return stretchingModels;

       /* SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<StretchingModel> stretchingModels = new ArrayList<>();
        Bitmap bitmap;

        Cursor cursor = db.rawQuery("select * from activities_images_table", null);
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                String activityName = cursor.getString(1);
                byte[] img = cursor.getBlob(2);
                bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
                stretchingModels.add(new StretchingModel(activityName, bitmap));
            }
        }
        return stretchingModels;*/
    }

   /* //retrieve activity image from database where id = given
    public Bitmap getActivitiesImage(Integer id){
        SQLiteDatabase db = this.getReadableDatabase();
        Bitmap bitmap = null;
        Cursor cursor = db.rawQuery("select activities_image from activities_images_table where id=?", new String[]{String.valueOf(id)});
        if(cursor.moveToNext()){
            byte[] img = cursor.getBlob(2);
            bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
        }
        return bitmap;
    }

    //retrieve activity name from database where id = given
    public String getActivitiesName(Integer id){
        SQLiteDatabase db = this.getReadableDatabase();
        String string = null;
        Cursor cursor = db.rawQuery("select activities_name from activities_images_table where id=?", new String[]{String.valueOf(id)});
        if(cursor.moveToNext()){
            string = cursor.getString(1);
        }
        return string;
    }*/

    //TODO 1) CREATE ACTIVITIES IMAGES SET METHODS (SAVING IMAGE INTO SQL)
}
