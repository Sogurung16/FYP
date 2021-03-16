package com.example.fyp_01.activityDetail;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.fyp_01.R;
import com.example.fyp_01.database.DatabaseHelper;
import com.example.fyp_01.recommendations.Controller;
import com.example.fyp_01.recommendations.Model;
import com.example.fyp_01.user.UserController;
import com.example.fyp_01.user.UserModel;

public class ActivityDetailController extends AppCompatActivity {
    private Button mPlayButton;
    private TextView mActivityName, mActivityType, mActivityWorkoutLvl, mActivityIntensity, mEquipmentGroup, mActivityTime, mActivityTimer;
    private ImageView mImageView;

    private CountDownTimer countDownTimer;
    private Boolean timerRunning;
    private String name, type, workoutLvl, intensity, equipmentGroup, timeView;
    private long timeInSeconds, timeInMillis, timeInMins;
    private Bitmap imageToRetrieve;
    private byte[] imgInByte;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        type = intent.getStringExtra("type");
        workoutLvl = intent.getStringExtra("workoutLvl");
        intensity = intent.getStringExtra("intensity");
        equipmentGroup = intent.getStringExtra("equipmentGroup");
        timeInMillis =  intent.getIntExtra("time", 0);

        timeInMins = timeInMillis/60000;
        timeInSeconds = (timeInMillis%60000)/1000;
        timeView = displayTime(timeInMins, timeInSeconds);

        imgInByte = intent.getByteArrayExtra("image");
        imageToRetrieve = BitmapFactory.decodeByteArray(imgInByte, 0, imgInByte.length);

        mActivityName = findViewById(R.id.activityDetailName);
        mActivityType = findViewById(R.id.activityTypeTextViewResult);
        mActivityWorkoutLvl = findViewById(R.id.activityWorkoutLevelTextViewResult);
        mActivityIntensity = findViewById(R.id.activityIntensityTextViewResult);
        mEquipmentGroup = findViewById(R.id.equipmentGroupTextViewResult);
        mActivityTime = findViewById(R.id.activityTimeTextViewResult);
        mActivityTimer = findViewById(R.id.activityTimer);
        mImageView = findViewById(R.id.activityDetailImage);
        mPlayButton = findViewById(R.id.playButton);

        mActivityName.setText(name);
        mActivityType.setText(type);
        mActivityWorkoutLvl.setText(workoutLvl);
        mActivityIntensity.setText(intensity);
        mEquipmentGroup.setText(equipmentGroup);
        mActivityTime.setText(String.valueOf(timeView));
        mImageView.setImageBitmap(imageToRetrieve);

        timerRunning = false;
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStop();
            }
        });
        updateTimer();
    }

    private void startStop(){
        if(timerRunning){
            stopTimer();
            timerRunning = false;
        } else{
            startTimer();
            timerRunning = true;
        }
    }

    private void startTimer(){
        countDownTimer = new CountDownTimer(timeInMillis, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timeInMillis = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish(){
                addPoints();
            }
        }.start();
        mPlayButton.setText("Stop");
    }

    private void stopTimer() {
        countDownTimer.cancel();
        mPlayButton.setText("Start");
    }

    private void updateTimer() {
        int minutes = (int) timeInMillis/60000;
        int seconds = (int) timeInMillis%60000/1000;

        timeView = displayTime(minutes, seconds);
        mActivityTimer.setText(timeView);
    }

    private String displayTime(long timeInMins, long timeInSeconds){
        timeView = "";
        timeView = timeInMins+":";
        if(timeInSeconds<10) timeView += "0";
        timeView += timeInSeconds;

        return timeView;
    }

    private void addPoints(){
        int pointsAdded, points = 0;

        SQLiteDatabase db = DatabaseHelper.getInstance(this).getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users_table", null);
        if (cursor.moveToFirst()) {
            points = cursor.getInt(6);
        }

        switch (intensity){
            case("Easy"):
                pointsAdded = 1;
                break;
            case("Moderate"):
                pointsAdded = 2;
                break;
            case("Hard"):
                pointsAdded = 3;
                break;
            default:
                pointsAdded = 0;
        }
        points+=pointsAdded;
        db.execSQL("UPDATE users_table SET users_points="+points+" "+"WHERE users_id = 1");
        Toast.makeText(ActivityDetailController.this, "Points Added "+pointsAdded+"!", Toast.LENGTH_LONG).show();
        int count = cursor.getCount();
        while(count>10){
            db.execSQL("DELETE FROM users_table WHERE users_id in (SELECT users_id FROM users_table LIMIT 1 OFFSET 1)");//delete the 2nd row of users profile. 1st row = initial user preferences
            cursor = db.rawQuery("select * from users_table", null);
            count = cursor.getCount();
        }
        cursor.close();
        db.execSQL("INSERT INTO users_table (users_name, users_goal, users_workout_group, users_intensity, users_equipment_group) " +
                "VALUES ("+"'"+name+"'"+", "+"'"+type+"'"+", "+"'"+workoutLvl+"'"+", "+"'"+intensity+"'"+", "+"'"+equipmentGroup+"'"+")");
        db.close();
    }
}
