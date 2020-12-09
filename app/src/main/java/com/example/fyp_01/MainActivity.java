package com.example.fyp_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Intent activitiesPage = new Intent(this, ActivitiesPage.class);
        startActivity(activitiesPage);*/

        Intent userDataPage = new Intent(this, UserDataPage.class);
        startActivity(userDataPage);
    }
}
