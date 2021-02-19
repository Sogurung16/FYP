package com.example.fyp_01.user;

import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

public class UserModel{

    private String userName, userGoal, intensity, workoutGroup, equipmentGroup;
    private List<String> workoutGroupSpinnerArray  = new ArrayList<String>();
    private List<String> userGoalSpinnerArray  = new ArrayList<String>();
    private List<String> equipmentGroupSpinnerArray = new ArrayList<String>();
    private List<String> intensitySpinnerArray = new ArrayList<String>();

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
