package com.example.fyp_01.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fyp_01.R;
import com.example.fyp_01.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class ActivitiesController extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    private List<String> workoutLvlSpinnerOptions(){
        List<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("Beginner");
        spinnerArray.add("Intermediate");
        spinnerArray.add("Advanced");

        return spinnerArray;
    }
    private List<String> equipmentGroupSpinnerOptions(){
        List<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("None");
        spinnerArray.add("Little");
        spinnerArray.add("Moderate");
        spinnerArray.add("Full");

        return spinnerArray;
    }
    private List<String> activityTypeSpinnerOptions(){
        List<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("Aerobic");
        spinnerArray.add("Muscle Strengthening");
        spinnerArray.add("Stretching");

        return spinnerArray;
    }
    private List<String> intensityLvlSpinnerOptions(){
        List<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("Easy");
        spinnerArray.add("Moderate");
        spinnerArray.add("Hard");

        return spinnerArray;
    }
    private List<Integer> daysPerWeekSpinnerOptions(){
        List<Integer> spinnerArray = new ArrayList<Integer>();
        for(int i=1;i<=7;i++){
            spinnerArray.add(i);
        }

        return spinnerArray;
    }
}
