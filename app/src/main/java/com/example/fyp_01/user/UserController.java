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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class UserController extends AppCompatActivity{

    DatabaseHelper databaseHelper;

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
                //Toast.makeText(UserController.this, "Activity preferences NOT selected", Toast.LENGTH_LONG).show();
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
                //Toast.makeText(UserController.this, "Activity preferences NOT selected", Toast.LENGTH_LONG).show();
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
                //Toast.makeText(UserController.this, "Activity preferences NOT selected", Toast.LENGTH_LONG).show();
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
                //Toast.makeText(UserController.this, "Activity preferences NOT selected", Toast.LENGTH_LONG).show();
            }
        });
    }

    protected static List<String> userGoalSpinnerOptions(){
        List<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("");
        spinnerArray.add("Endurance");
        spinnerArray.add("Strength");
        spinnerArray.add("Stretching");

        return spinnerArray;
    }


    protected List<String> intensitySpinnerOptions(){
        List<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("");
        spinnerArray.add("Easy");
        spinnerArray.add("Moderate");
        spinnerArray.add("Hard");

        return spinnerArray;
    }

    protected List<String> workoutGroupSpinnerOptions(){
        List<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("");
        spinnerArray.add("Beginner");
        spinnerArray.add("Intermediate");
        spinnerArray.add("Advanced");

        return spinnerArray;
    }

    protected List<String> equipmentGroupSpinnerOptions(){
        List<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("");
        spinnerArray.add("None");
        spinnerArray.add("Basic");
        spinnerArray.add("Full");

        return spinnerArray;
    }

    protected Boolean addUser(){
        databaseHelper = new DatabaseHelper(this);
        databaseHelper.deleteUserData();
        if(user.getUserPointsData()<=0) {
            user.setUserPointsData(0);
        }
        boolean isInserted = databaseHelper.addUserData(user);

        if(isInserted == true){
            Toast.makeText(UserController.this, buildToastMessage(""), Toast.LENGTH_LONG).show();
            Intent recommendationPage = new Intent(this, Controller.class);
            startActivity(recommendationPage);
        }
        else{
            Toast.makeText(UserController.this, buildToastMessage(" NOT"), Toast.LENGTH_LONG).show();
        }
        return isInserted;
    }

    protected static String buildToastMessage(String message){
        return "Data"+message+" Added";
    }

    public UserModel retrieveUserData(){
        user.setUserNameData(user.getUserNameData());
        user.setWorkoutGroupData(user.getWorkoutGroupData());
        user.setUserGoalData(user.getUserGoalData());
        user.setEquipmentGroupData(user.getEquipmentGroupData());
        user.setIntensityData(user.getIntensityData());
        user.setUserPointsData(user.getUserPointsData());

        return  user;
    }

    public void addUserButton(View view){
        user.setUserNameData(mUserNameInput.getText().toString());
        retrieveUserData();
        if(user.getUserNameData().length()<=0 | user.getUserNameData()==null){
            Toast.makeText(UserController.this, buildToastMessage(" NOT") + "\nInvalid Name", Toast.LENGTH_LONG).show();
        }
        else if(user.getUserGoalData()=="" | user.getWorkoutGroupData()=="" | user.getEquipmentGroupData()=="" | user.getIntensityData()==""){
            Toast.makeText(UserController.this, buildToastMessage(" NOT") + "\nIncomplete Activity Preferences", Toast.LENGTH_LONG).show();
        }
        else {
            addUser();
        }
    }

    /*public void updateUserProfile(String goal, String workoutLvl, String intensity, String equipmentGroup){
        databaseHelper = new DatabaseHelper(this);
        user.setUserGoalData(goal);
        user.setWorkoutGroupData(workoutLvl);
        user.setIntensityData(intensity);
        user.setEquipmentGroupData(equipmentGroup);
        databaseHelper.addUserData(user);
    }*/
}
