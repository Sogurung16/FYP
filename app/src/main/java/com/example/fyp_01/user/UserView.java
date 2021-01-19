package com.example.fyp_01.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp_01.database.DatabaseHelper;
import com.example.fyp_01.R;
import com.example.fyp_01.recommendations.ActivitiesRecommendation;

import java.util.ArrayList;
import java.util.List;

public class UserView extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    private Button mAddUserButton;
    private TextView mUserName, mUserGoal, mEquipmentGroup, mIntensity, mWorkoutGroup;

    private EditText mUserNameInput;
    private Spinner mUserGoalInput, mIntensityInput, mWorkoutGroupInput, mEquipmentGroupInput;

    UserController user = new UserController(new UserData());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

        initialization();
        listeners();
    }

    private void initialization(){
        mAddUserButton = findViewById(R.id.addUser);
        mUserName = findViewById(R.id.userName);
        mUserGoal = findViewById(R.id.userGoal);
        mIntensity = findViewById(R.id.intensity);
        mWorkoutGroup = findViewById(R.id.workoutGroup);
        mEquipmentGroup = findViewById(R.id.equipmentGroup);

        mUserNameInput = findViewById(R.id.userNameInput);
        mUserGoalInput = findViewById(R.id.userGoalInput);
        mIntensityInput = findViewById(R.id.intensityInput);
        mWorkoutGroupInput = findViewById(R.id.workoutGroupInput);
        mEquipmentGroupInput = findViewById(R.id.equipmentGroupInput);

        user.setUserGoalSpinnerArray(userGoalSpinnerOptions());
        user.setIntensitySpinnerArray(intensitySpinnerOptions());
        user.setWorkoutGroupSpinnerArray(workoutGroupSpinnerOptions());
        user.setEquipmentGroupSpinnerArray(equipmentGroupSpinnerOptions());

        ArrayAdapter<String> goalAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, user.getUserGoalSpinnerArray());
        mUserGoalInput.setAdapter(goalAdapter);
        ArrayAdapter<String> intensityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, user.getIntensitySpinnerArray());
        mIntensityInput.setAdapter(intensityAdapter);
        ArrayAdapter<String> workoutGroupAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, user.getWorkoutGroupSpinnerArray());
        mWorkoutGroupInput.setAdapter(workoutGroupAdapter);
        ArrayAdapter<String> equipmentGroupAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, user.getEquipmentGroupSpinnerArray());
        mEquipmentGroupInput.setAdapter(equipmentGroupAdapter);
    }

    private void listeners(){
        mUserGoalInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                if (item != null){
                    user.setUserGoal(item.toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //TODO: Display Error Message.
            }
        });

        mIntensityInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                if (item != null){
                    user.setIntensity(item.toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //TODO: Display Error Message.
            }
        });

        mWorkoutGroupInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                if (item != null){
                    user.setWorkoutGroup(item.toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //TODO: Display Error Message.
            }
        });

        mEquipmentGroupInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                if (item != null){
                    user.setEquipmentGroup(item.toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //TODO: Display Error Message.
            }
        });
    }

    private List<String> userGoalSpinnerOptions(){
        List<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("Endurance");
        spinnerArray.add("Strengthening");
        spinnerArray.add("Stretching");

        return spinnerArray;
    }


    private List<String> intensitySpinnerOptions(){
        List<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("Easy");
        spinnerArray.add("Moderate");
        spinnerArray.add("Hard");

        return spinnerArray;
    }

    private List<String> workoutGroupSpinnerOptions(){
        List<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("Beginner");
        spinnerArray.add("Intermediate");
        spinnerArray.add("Advanced");

        return spinnerArray;
    }

    private List<String> equipmentGroupSpinnerOptions(){
        List<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("None");
        spinnerArray.add("Basic");
        spinnerArray.add("Full");

        return spinnerArray;
    }

    public void addUserButton(View view){
        retrieveUserData();
        addUser();
    }

    private void addUser(){
        databaseHelper = new DatabaseHelper(this);
        boolean isInserted = databaseHelper.addUserData(user);

        if(isInserted == true){
            Toast.makeText(UserView.this, "Data Added", Toast.LENGTH_LONG).show();
            Intent recommendationPage = new Intent(this, ActivitiesRecommendation.class);
            startActivity(recommendationPage);
        }
        else{
            Toast.makeText(UserView.this, "Data NOT Added", Toast.LENGTH_LONG).show();
        }
    }

    private UserController retrieveUserData(){
        user.setUserName(mUserNameInput.getText().toString());
        user.setWorkoutGroup(user.getWorkoutGroup());
        user.setUserGoal(user.getUserGoal());
        user.setEquipmentGroup(user.getEquipmentGroup());
        user.setIntensity(user.getIntensity());

        return  user;
    }
}
