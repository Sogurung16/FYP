package com.example.fyp_01.user;

import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

public class UserData{

    private int  daysAvailable;
    private String userName, userGoal, intensity, workoutGroup;
    private List<String> workoutGroupSpinnerArray  = new ArrayList<String>();
    private List<String> userGoalSpinnerArray  = new ArrayList<String>();
    private List<Integer> daysAvailableSpinnerArray = new ArrayList<Integer>();
    private List<String> intensitySpinnerArray = new ArrayList<String>();

    public void setUserNameData(String userName) {
        this.userName = userName;
    }
    public void setUserGoalData(String userGoal) { this.userGoal = userGoal; }
    public void setWorkoutGroupData(String workoutGroup) {
        this.workoutGroup = workoutGroup;
    }
    public void setUserGoalSpinnerArrayData(List<String> userGoalSpinnerArray) {
        this.userGoalSpinnerArray = userGoalSpinnerArray;
    }
    public void setWorkoutGroupSpinnerArrayData(List<String> workoutGroupSpinnerArray) {
        this.workoutGroupSpinnerArray = workoutGroupSpinnerArray;
    }
    public void setDaysAvailableData(int daysAvailable){ this.daysAvailable = daysAvailable;}
    public void setDaysAvailableSpinnerArrayData(List<Integer> daysAvailableSpinnerArray) {
        this.daysAvailableSpinnerArray = daysAvailableSpinnerArray;
    }
    public void setIntensityData(String intensity) {
        this.intensity = intensity;
    }
    public void setIntensitySpinnerArrayData(List<String> intensitySpinnerArray) {
        this.intensitySpinnerArray = intensitySpinnerArray;
    }

    public String getUserNameData() {
        return userName;
    }
    public String getUserGoalData() {
        return userGoal;
    }
    public String getWorkoutGroupData() {
        return workoutGroup;
    }
    public List<String> getUserGoalSpinnerArrayData() {
        return userGoalSpinnerArray;
    }
    public List<String> getWorkoutGroupSpinnerArrayData() {
        return workoutGroupSpinnerArray;
    }
    public int getDaysAvailableData() {
        return daysAvailable;
    }
    public List<Integer> getDaysAvailableSpinnerArrayData() {
        return daysAvailableSpinnerArray;
    }
    public String getIntensityData() {
        return intensity;
    }
    public List<String> getIntensitySpinnerArrayData() {
        return intensitySpinnerArray;
    }
}
