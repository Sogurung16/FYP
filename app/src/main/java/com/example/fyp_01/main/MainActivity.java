package com.example.fyp_01.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.fyp_01.R;
import com.example.fyp_01.database.DatabaseHelper;
import com.example.fyp_01.recommendations.Controller;
import com.example.fyp_01.user.UserController;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}
