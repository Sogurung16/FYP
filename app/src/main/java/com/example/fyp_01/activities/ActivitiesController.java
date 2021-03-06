package com.example.fyp_01.activities;

import java.util.List;

public class ActivitiesController{

    private ActivitiesData activitiesData;

    public ActivitiesController(ActivitiesData activitiesData) {
        this.activitiesData = activitiesData;
    }

    public int getIntensityLvl() {
        return activitiesData.getIntensityLvlData();
    }
    public String getActivityType() {
        return activitiesData.getActivityTypeData();
    }
    public String getActivityName() {
        return activitiesData.getActivityNameData();
    }
    public String getWorkoutLvl() {
        return activitiesData.getWorkoutLvlData();
    }
    public String getEquipmentGroup() {
        return activitiesData.getEquipmentGroupData();
    }
    public List<String> getWorkoutLvlSpinnerArray() {
        return activitiesData.getWorkoutLvlSpinnerArrayData();
    }
    public List<String> getEquipmentGroupSpinnerArray() {
        return activitiesData.getEquipmentGroupSpinnerArrayData();
    }
    public List<String> getActivityTypeSpinnerArray() {
        return activitiesData.getActivityTypeSpinnerArrayData();
    }
    public List<String> getIntensityLvlSpinnerArray() {
        return activitiesData.getIntensityLvlSpinnerArrayData();
    }

    public void setEquipmentGroupSpinnerArray(List<String> equipmentGroupSpinnerArray) {
        activitiesData.setEquipmentGroupSpinnerArrayData(equipmentGroupSpinnerArray);
    }
    public void setIntensityLvlSpinnerArray(List<String> intensityLvlSpinnerArray) {
        activitiesData.setIntensityLvlSpinnerArrayData(intensityLvlSpinnerArray);
    }
    public void setActivityTypeSpinnerArray(List<String> activityTypeSpinnerArray) {
        activitiesData.setActivityTypeSpinnerArrayData(activityTypeSpinnerArray);
    }
    public void setWorkoutLvlSpinnerArray(List<String> workoutLvlSpinnerArray) {
        activitiesData.setWorkoutLvlSpinnerArrayData(workoutLvlSpinnerArray);
    }
    public void setEquipmentGroup(String equipmentGroup) {
        activitiesData.setEquipmentGroupData(equipmentGroup);
    }
    public void setWorkoutLvl(String workoutLvl) {
        activitiesData.setWorkoutLvlData(workoutLvl);
    }
    public void setActivityName(String activityName) {
        activitiesData.setActivityNameData(activityName);
    }
    public void setActivityTypeData(String activityType) {
        activitiesData.setActivityTypeData(activityType);
    }
    public void setIntensityLvl(int intensityLvl) {
        activitiesData.setIntensityLvlData(intensityLvl);
    }
}
