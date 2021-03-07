package com.example.fyp_01.activityDetail;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.fyp_01.R;
import com.example.fyp_01.recommendations.Controller;
import com.example.fyp_01.recommendations.Model;

public class ActivityDetailController extends AppCompatActivity {
    private Button mPlayButton;
    private TextView mActivityName, mActivityType, mActivityWorkoutLvl, mActivityIntensity, mEquipmentGroup, mActivityTime, mActivityTimer;
    private ImageView mImageView;

    private CountDownTimer countDownTimer;
    private Boolean timerRunning;
    private String name, type, workoutLvl, intensity, equipmentGroup;
    private Bitmap img;
    private long time;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bitmap imageToRetrieve;
        byte[] imgInByte;

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        type = intent.getStringExtra("type");
        workoutLvl = intent.getStringExtra("workoutLvl");
        intensity = intent.getStringExtra("intensity");
        equipmentGroup = intent.getStringExtra("equipmentGroup");
        time =  intent.getIntExtra("time", 0)/1000;
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
        mActivityTime.setText(String.valueOf(time));
        mImageView.setImageBitmap(imageToRetrieve);

        /*mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStop();
            }
        });*/
    }

   /* private void startStop(){
        if(timerRunning){
            stopTimer();
        } else{
            startTimer();
        }
    }

    private void startTimer(){
        countDownTimer = new CountDownTimer(time, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                time = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish(){

            }
        }.start();
        mPlayButton.setText("Stop");
        timerRunning = true;
    }

    private void stopTimer() {
        countDownTimer.cancel();
        mPlayButton.setText("Stop");
        timerRunning = false;
    }

    private void updateTimer() {
        int minutes = (int) time/60000;
        int seconds = (int) (time%60000)/1000;

        String timeLeftText;

        timeLeftText = "" + minutes + ":";
        // if digits is less than 10, add 0 string for the next iteration to be 09 instead of 9.
        if(seconds<10) timeLeftText += "0";
        timeLeftText += seconds;

        mActivityTimer.setText(timeLeftText);
    }*/

}
