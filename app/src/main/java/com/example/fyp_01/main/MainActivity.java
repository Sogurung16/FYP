package com.example.fyp_01.main;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;

import com.example.fyp_01.R;
import com.example.fyp_01.database.DatabaseHelper;
import com.example.fyp_01.recommendations.Controller;
import com.example.fyp_01.user.UserController;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myAlarm();

        db = DatabaseHelper.getInstance(this).getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from users_table", null);
        //if user data already exists then skip userprofile page.
        if(cursor.getCount()<=0) {
            Intent userDataPage = new Intent(this, UserController.class);
            startActivity(userDataPage);
        }else{
            Intent recommendationPage = new Intent(this, Controller.class);
            startActivity(recommendationPage);
        }
        cursor.close();
    }

    public void myAlarm() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Intent intent = new Intent(getApplicationContext(), NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 7, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }
}
