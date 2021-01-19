package com.example.fyp_01.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.fyp_01.R;
import com.example.fyp_01.activities.ActivitiesView;
import com.example.fyp_01.recommendations.ActivitiesRecommendation;
//import com.example.fyp_01.user.UserView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent activitiesRecommendationPage = new Intent(this, ActivitiesRecommendation.class);
        startActivity(activitiesRecommendationPage);

        /*Intent userDataPage = new Intent(this, UserView.class);
        startActivity(userDataPage);*/
    }
}
