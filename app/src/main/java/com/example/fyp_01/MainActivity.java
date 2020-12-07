package com.example.fyp_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addUser();

        Intent recommendationPage = new Intent(this, RecommendationPage.class);
        startActivity(recommendationPage);
    }

    private void addUser(){
        UserData newUserData = retrieveUserData();

        User newUser = new User(newUserData);

        databaseHelper = new DatabaseHelper(this);
        boolean isInserted = databaseHelper.addUserData(newUser);

        if(isInserted == true){
            Toast.makeText(MainActivity.this, "Data Added", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(MainActivity.this, "Data NOT Added", Toast.LENGTH_LONG).show();
        }
    }

    private UserData retrieveUserData(){
        UserData userData = new UserData();
        userData.setUserNameData("Sonam");
        userData.setUserWeightData(75);
        userData.setUserGoalData(0);

        return  userData;
    }
}
