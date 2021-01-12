package com.example.fyp_01.user;

import androidx.appcompat.app.AppCompatActivity;

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

import java.util.ArrayList;
import java.util.List;

public class UserView extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    private Button mAddUserButton;
    private TextView mUserName, mUserGoal, mDaysAvailable, mIntensity, mWorkoutGroup;

    private EditText mUserNameInput;
    private Spinner mUserGoalInput, mDaysAvailableInput, mIntensityInput, mWorkoutGroupInput;

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
        mDaysAvailable = findViewById(R.id.daysAvailable);
        mIntensity = findViewById(R.id.intensity);
        mWorkoutGroup = findViewById(R.id.workoutGroup);

        mUserNameInput = findViewById(R.id.userNameInput);
        mUserGoalInput = findViewById(R.id.userGoalInput);
        mDaysAvailableInput = findViewById(R.id.daysAvailableInput);
        mIntensityInput = findViewById(R.id.intensityInput);
        mWorkoutGroupInput = findViewById(R.id.workoutGroupInput);

        user.setUserGoalSpinnerArray(userGoalSpinnerOptions());
        user.setDaysAvailableSpinnerArray(DaysAvailableSpinnerOptions());
        user.setIntensitySpinnerArray(intensitySpinnerOptions());
        user.setWorkoutGroupSpinnerArray(workoutGroupSpinnerOptions());

        ArrayAdapter<String> goalAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, user.getUserGoalSpinnerArray());
        mUserGoalInput.setAdapter(goalAdapter);
        ArrayAdapter<Integer> daysAvailableAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, user.getDaysAvailableSpinnerArray());
        mDaysAvailableInput.setAdapter(daysAvailableAdapter);
        ArrayAdapter<String> intensityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, user.getIntensitySpinnerArray());
        mIntensityInput.setAdapter(intensityAdapter);
        ArrayAdapter<String> workoutGroupAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, user.getWorkoutGroupSpinnerArray());
        mWorkoutGroupInput.setAdapter(workoutGroupAdapter);
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

        mDaysAvailableInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                if (item != null){
                    user.setDaysAvailable((int)item);
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
    }

    private List<String> userGoalSpinnerOptions(){
        List<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("Aerobic");
        spinnerArray.add("Muscle Strengthening");
        spinnerArray.add("Stretching");

        return spinnerArray;
    }

    private List<Integer> DaysAvailableSpinnerOptions(){
        List<Integer> spinnerArray = new ArrayList<Integer>();
        spinnerArray.add(1);
        spinnerArray.add(2);
        spinnerArray.add(3);
        spinnerArray.add(4);
        spinnerArray.add(5);
        spinnerArray.add(6);
        spinnerArray.add(7);

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

    public void addUserButton(View view){
        retrieveUserData();
        addUser();
    }

    private void addUser(){
        databaseHelper = new DatabaseHelper(this);
        boolean isInserted = databaseHelper.addUserData(user);

        if(isInserted == true){
            Toast.makeText(UserView.this, "Data Added", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(UserView.this, "Data NOT Added", Toast.LENGTH_LONG).show();
        }
    }

    private UserController retrieveUserData(){
        user.setUserName(mUserNameInput.getText().toString());
        user.setWorkoutGroup(user.getWorkoutGroup());
        user.setUserGoal(user.getUserGoal());
        user.setDaysAvailable(user.getDaysAvailable());
        user.setIntensity(user.getIntensity());

        return  user;
    }
}
