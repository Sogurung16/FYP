package com.example.fyp_01;

import java.util.List;

public class UserController {

    private UserData userData;

    public UserController(UserData userData){
        this.userData = userData;
    }

    public void setUserName(String userName) {
        userData.setUserNameData(userName);
    }

    public void setUserWeight(int userWeight) {
        userData.setUserWeightData(userWeight);
    }

    public void setUserGoal(String userGoal) { userData.setUserGoalData(userGoal); }

    public void setUserGoalSpinnerArray(List<String> userGoalSpinnerArray) {
        userData.setUserGoalSpinnerArrayData(userGoalSpinnerArray);
    }

    public String getUserName() {
        return userData.getUserNameData();
    }

    public int getUserWeight() {
        return userData.getUserWeightData();
    }

    public String getUserGoal() {
        return userData.getUserGoalData();
    }

    public List<String> getUserGoalSpinnerArray() {
        return userData.getUserGoalSpinnerArrayData();
    }

   /* public void addUser(User user){
       DatabaseHelper databaseHelper = new DatabaseHelper(null);
       databaseHelper.addUserData(user);
    }*/
}
