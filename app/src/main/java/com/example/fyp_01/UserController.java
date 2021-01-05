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
    public void setDaysAvailable(int daysAvailable){ userData.setDaysAvailableData(daysAvailable);}
    public void setDaysAvailableSpinnerArray(List<Integer> daysAvailableSpinnerArray) {
        userData.setDaysAvailableSpinnerArrayData(daysAvailableSpinnerArray);
    }
    public void setIntensity(String intensity) {
        userData.setIntensityData(intensity);
    }
    public void setIntensitySpinnerArray(List<String> intensitySpinnerArray) {
        userData.setIntensitySpinnerArrayData(intensitySpinnerArray);
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
    public int getDaysAvailable() {
        return userData.getDaysAvailableData();
    }
    public List<Integer> getDaysAvailableSpinnerArray() {
        return userData.getDaysAvailableSpinnerArrayData();
    }
    public String getIntensity() {
        return userData.getIntensityData();
    }
    public List<String> getIntensitySpinnerArray() {
        return userData.getIntensitySpinnerArrayData();
    }
}
