package com.example.fyp_01;

import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

public class UserData{

    private int userWeight, daysAvailable;
    private String userName, userGoal, intensity;
    private List<String> userGoalSpinnerArray  = new ArrayList<String>();
    private List<Integer> daysAvailableSpinnerArray = new ArrayList<Integer>();
    private List<String> intensitySpinnerArray = new ArrayList<String>();

    public void setUserNameData(String userName) {
        this.userName = userName;
    }
    public void setUserWeightData(int userWeight) {
        this.userWeight = userWeight;
    }
    public void setUserGoalData(String userGoal) { this.userGoal = userGoal; }
    public void setUserGoalSpinnerArrayData(List<String> userGoalSpinnerArray) {
        this.userGoalSpinnerArray = userGoalSpinnerArray;
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
    public int getUserWeightData() {
        return userWeight;
    }
    public String getUserGoalData() {
        return userGoal;
    }
    public List<String> getUserGoalSpinnerArrayData() {
        return userGoalSpinnerArray;
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
