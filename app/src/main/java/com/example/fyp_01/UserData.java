package com.example.fyp_01;

public class UserData{

    private int userGoal;
    private int userWeight;
    private String userName;

    public void setUserNameData(String userName) {
        this.userName = userName;
    }

    public void setUserWeightData(int userWeight) {
        this.userWeight = userWeight;
    }

    public void setUserGoalData(int userGoal) {
        this.userGoal = userGoal;
    }

    public String getUserNameData() {
        return userName;
    }

    public int getUserWeightData() {
        return userWeight;
    }

    public int getUserGoalData() {
        return userGoal;
    }
}
