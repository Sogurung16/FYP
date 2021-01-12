package com.example.fyp_01.user;

import java.util.List;

public class UserController {

    private UserData userData;

    public UserController(UserData userData){
        this.userData = userData;
    }

    public void setUserName(String userName) {
        userData.setUserNameData(userName);
    }
    public void setUserGoal(String userGoal) { userData.setUserGoalData(userGoal); }
    public void setWorkoutGroup(String workoutGroup) { userData.setWorkoutGroupData(workoutGroup); }
    public void setUserGoalSpinnerArray(List<String> userGoalSpinnerArray) {
        userData.setUserGoalSpinnerArrayData(userGoalSpinnerArray);
    }
    public void setWorkoutGroupSpinnerArray(List<String> workoutGroupSpinnerArray) {
        userData.setWorkoutGroupSpinnerArrayData(workoutGroupSpinnerArray);
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
    public String getUserGoal() {
        return userData.getUserGoalData();
    }
    public String getWorkoutGroup() {
        return userData.getWorkoutGroupData();
    }
    public List<String> getUserGoalSpinnerArray() {
        return userData.getUserGoalSpinnerArrayData();
    }
    public List<String> getWorkoutGroupSpinnerArray() {
        return userData.getWorkoutGroupSpinnerArrayData();
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
