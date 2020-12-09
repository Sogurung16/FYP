package com.example.fyp_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserDataPage extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    private Button mAddUserButton;
    private TextView mUserName, mUserWeight, mUserGoal;
    private EditText mUserNameInput, mUserWieghtInput, mUserGoalInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

        mAddUserButton = findViewById(R.id.addUser);
        mUserName = findViewById(R.id.userName);
        mUserWeight = findViewById(R.id.userWeight);
        mUserGoal = findViewById(R.id.userGoal);
        mUserNameInput = findViewById(R.id.userNameInput);
        mUserWieghtInput = findViewById(R.id.userWeightInput);
        mUserGoalInput = findViewById(R.id.userGoalInput);
    }

    public void addUserButton(View view){
        addUser();
    }

    private void addUser(){
        UserData newUserData = retrieveUserData();

        User newUser = new User(newUserData);

        databaseHelper = new DatabaseHelper(this);
        boolean isInserted = databaseHelper.addUserData(newUser);

        if(isInserted == true){
            Toast.makeText(UserDataPage.this, "Data Added", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(UserDataPage.this, "Data NOT Added", Toast.LENGTH_LONG).show();
        }
    }

    private UserData retrieveUserData(){
        UserData userData = new UserData();
        userData.setUserNameData(mUserNameInput.toString());
        userData.setUserWeightData(Integer.parseInt(mUserWieghtInput.getText().toString()));
        userData.setUserGoalData(Integer.parseInt(mUserGoalInput.getText().toString())); //TODO: change from number to autocomplete text

        return  userData;
    }
}
