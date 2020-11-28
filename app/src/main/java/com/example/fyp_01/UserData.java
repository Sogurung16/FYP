package com.example.fyp_01;

public class UserData {

    private int goal;
    private int userWeight;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserWeight(int userWeight) {
        this.userWeight = userWeight;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public int getUserWeight() {
        return userWeight;
    }

    public int getGoal() {
        return goal;
    }
}
