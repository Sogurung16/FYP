package com.example.fyp_01.user;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class UserModel{

    private String userName, userGoal, intensity, workoutGroup, equipmentGroup;
    private int userPoints;
    private List<String> workoutGroupSpinnerArray  = new ArrayList<String>();
    private List<String> userGoalSpinnerArray  = new ArrayList<String>();
    private List<String> equipmentGroupSpinnerArray = new ArrayList<String>();
    private List<String> intensitySpinnerArray = new ArrayList<String>();

    public UserModel(String userName, String userGoal, String intensity, String workoutGroup, String equipmentGroup, int userPoints){
        this.userName = userName;
        this.userGoal = userGoal;
        this.intensity = intensity;
        this.workoutGroup = workoutGroup;
        this.equipmentGroup = equipmentGroup;
        this.userPoints = userPoints;
    }

    public UserModel (UserModel user){
        this.userName = user.getUserNameData();
        this.userGoal = user.getUserGoalData();
        this.intensity = user.getIntensityData();
        this.workoutGroup = user.getWorkoutGroupData();
        this.equipmentGroup = user.getEquipmentGroupData();
        this.userPoints = user.getUserPointsData();
    }

    public UserModel (){
        //empty user
    }

    public void setUserNameData(String userName) {
        this.userName = userName;
    }
    public void setUserGoalData(String userGoal) { this.userGoal = userGoal; }
    public void setWorkoutGroupData(String workoutGroup) {
        this.workoutGroup = workoutGroup;
    }
    public void setIntensityData(String intensity) {
        this.intensity = intensity;
    }
    public void setEquipmentGroupData(String equipmentGroup) {
        this.equipmentGroup = equipmentGroup;
    }
    public void setUserPointsData(int userPoints){
        this.userPoints = userPoints;
    }

    public void setUserGoalSpinnerArrayData(List<String> userGoalSpinnerArray) {
        this.userGoalSpinnerArray = userGoalSpinnerArray;
    }
    public void setWorkoutGroupSpinnerArrayData(List<String> workoutGroupSpinnerArray) {
        this.workoutGroupSpinnerArray = workoutGroupSpinnerArray;
    }
    public void setIntensitySpinnerArrayData(List<String> intensitySpinnerArray) {
        this.intensitySpinnerArray = intensitySpinnerArray;
    }
    public void setEquipmentGroupSpinnerArrayData(List<String> equipmentGroupSpinnerArray) {
        this.equipmentGroupSpinnerArray = equipmentGroupSpinnerArray;
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
    public String getIntensityData() {
        return intensity;
    }
    public String getEquipmentGroupData() {
        return equipmentGroup;
    }
    public int getUserPointsData() {return userPoints;}

    public List<String> getUserGoalSpinnerArrayData() {
        return userGoalSpinnerArray;
    }
    public List<String> getWorkoutGroupSpinnerArrayData() {
        return workoutGroupSpinnerArray;
    }
    public List<String> getIntensitySpinnerArrayData() {
        return intensitySpinnerArray;
    }
    public List<String> getEquipmentGroupSpinnerArrayData() {
        return equipmentGroupSpinnerArray;
    }
}
