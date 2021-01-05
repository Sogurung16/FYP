package com.example.fyp_01;

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

import java.util.ArrayList;
import java.util.List;

public class UserView extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    private Button mAddUserButton;
    private TextView mUserName, mUserWeight, mUserGoal;
    private EditText mUserNameInput, mUserWeightInput;
    private Spinner mUserGoalInput;

    UserController user = new UserController(new UserData());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

        mAddUserButton = findViewById(R.id.addUser);
        mUserName = findViewById(R.id.userName);
        mUserWeight = findViewById(R.id.userWeight);
        mUserGoal = findViewById(R.id.userGoal);
        mUserNameInput = findViewById(R.id.userNameInput);
        mUserWeightInput = findViewById(R.id.userWeightInput);
        mUserGoalInput = findViewById(R.id.userGoalInput);

        user.setUserGoalSpinnerArray(userGoalSpinnerOptions());
        ArrayAdapter<String> goalAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, user.getUserGoalSpinnerArray());
        mUserGoalInput.setAdapter(goalAdapter);

        mUserGoalInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                if (item != null){
                    user.setUserGoal(item.toString());
                    Toast.makeText(UserView.this, item.toString(), Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(UserView.this, "Selected",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            //TODO: Display Error Message.
            }
        });
    }

    public List<String> userGoalSpinnerOptions(){
        List<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("Burn Fat");
        spinnerArray.add("Build Muscle");

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
        user.setUserWeight(Integer.parseInt(mUserWeightInput.getText().toString()));
        user.setUserGoal(user.getUserGoal());

        return  user;
    }
}
