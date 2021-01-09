package com.example.fyp_01;

import java.util.List;

public class ActivitiesController{

    private ActivitiesData activitiesData;

    public ActivitiesController(ActivitiesData activitiesData) {
        this.activitiesData = activitiesData;
    }

    public int getDaysPerWeek() {
        return activitiesData.getDaysPerWeekData();
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
    public List<Integer> getDaysPerWeekSpinnerArray() {
        return activitiesData.getDaysPerWeekSpinnerArrayData();
    }

    public void setDaysPerWeekSpinnerArray(List<Integer> daysPerWeekSpinnerArray) {
        activitiesData.setDaysPerWeekSpinnerArrayData(daysPerWeekSpinnerArray);
    }
    public void setEquipmentGroupSpinnerArray(List<String> equipmentGroupSpinnerArray) {
        activitiesData.setEquipmentGroupSpinnerArrayData(equipmentGroupSpinnerArray);
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
    public void setDaysPerWeek(int daysPerWeek) {
        activitiesData.setDaysPerWeekData(daysPerWeek);
    }
}
