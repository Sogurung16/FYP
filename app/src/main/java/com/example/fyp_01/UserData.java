package com.example.fyp_01;

import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

public class UserData{

    private int userWeight;
    private String userName, userGoal;
    private List<String> userGoalSpinnerArray  = new ArrayList<String>();

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
}
