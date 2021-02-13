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
import com.example.fyp_01.recommendations.Controller;

import java.util.ArrayList;
import java.util.List;

public class UserController extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    private Button mAddUserButton;
    private TextView mUserName, mUserGoal, mEquipmentGroup, mIntensity, mWorkoutGroup;

    private EditText mUserNameInput;
    private Spinner mUserGoalInput, mIntensityInput, mWorkoutGroupInput, mEquipmentGroupInput;

    UserModel user = new UserModel();

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

        user.setUserGoalSpinnerArrayData(userGoalSpinnerOptions());
        user.setIntensitySpinnerArrayData(intensitySpinnerOptions());
        user.setWorkoutGroupSpinnerArrayData(workoutGroupSpinnerOptions());
        user.setEquipmentGroupSpinnerArrayData(equipmentGroupSpinnerOptions());

        ArrayAdapter<String> goalAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, user.getUserGoalSpinnerArrayData());
        mUserGoalInput.setAdapter(goalAdapter);
        ArrayAdapter<String> intensityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, user.getIntensitySpinnerArrayData());
        mIntensityInput.setAdapter(intensityAdapter);
        ArrayAdapter<String> workoutGroupAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, user.getWorkoutGroupSpinnerArrayData());
        mWorkoutGroupInput.setAdapter(workoutGroupAdapter);
        ArrayAdapter<String> equipmentGroupAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, user.getEquipmentGroupSpinnerArrayData());
        mEquipmentGroupInput.setAdapter(equipmentGroupAdapter);
    }

    private void listeners(){
        mUserGoalInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                if (item != null){
                    user.setUserGoalData(item.toString());
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
                    user.setIntensityData(item.toString());
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
                    user.setWorkoutGroupData(item.toString());
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
                    user.setEquipmentGroupData(item.toString());
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
        databaseHelper.deleteUserData();
        boolean isInserted = databaseHelper.addUserData(user);

        if(isInserted == true){
            Toast.makeText(UserController.this, "Data Added", Toast.LENGTH_LONG).show();
            Intent recommendationPage = new Intent(this, Controller.class);
            startActivity(recommendationPage);
        }
        else{
            Toast.makeText(UserController.this, "Data NOT Added", Toast.LENGTH_LONG).show();
        }
    }

    private UserModel retrieveUserData(){
        user.setUserNameData(mUserNameInput.getText().toString());
        user.setWorkoutGroupData(user.getWorkoutGroupData());
        user.setUserGoalData(user.getUserGoalData());
        user.setEquipmentGroupData(user.getEquipmentGroupData());
        user.setIntensityData(user.getIntensityData());

        return  user;
    }
}
