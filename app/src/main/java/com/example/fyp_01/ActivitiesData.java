package com.example.fyp_01;

import java.util.ArrayList;
import java.util.List;

public class ActivitiesData{

    private int intensityLvl, daysPerWeek;
    private String activityName, workoutLvl, equipmentGroup, activityType;
    private List<String> workoutLvlSpinnerArray = new ArrayList<String>();
    private List<String> equipmentGroupSpinnerArray = new ArrayList<String>();
    private List<String> activityTypeSpinnerArray = new ArrayList<String>();
    private List<Integer> daysPerWeekSpinnerArray = new ArrayList<Integer>();

    public String getActivityTypeData() {
        return activityType;
    }
    public int getDaysPerWeekData() {
        return daysPerWeek;
    }
    public int getIntensityLvlData() {
        return intensityLvl;
    }
    public String getActivityNameData() {
        return activityName;
    }
    public String getWorkoutLvlData() {
        return workoutLvl;
    }
    public String getEquipmentGroupData() {
        return equipmentGroup;
    }
    public List<String> getWorkoutLvlSpinnerArrayData() {
        return workoutLvlSpinnerArray;
    }
    public List<String> getEquipmentGroupSpinnerArrayData() {
        return equipmentGroupSpinnerArray;
    }
    public List<String> activityTypeSpinnerArrayData() {
        return activityTypeSpinnerArray;
    }
    public List<Integer> getDaysPerWeekSpinnerArrayData() {
        return daysPerWeekSpinnerArray;
    }

    public void setDaysPerWeekSpinnerArrayData(List<Integer> daysPerWeekSpinnerArray) {
        this.daysPerWeekSpinnerArray = daysPerWeekSpinnerArray;
    }
    public void activityTypeSpinnerArrayData(List<String> activityTypeSpinnerArray) {
        this.activityTypeSpinnerArray = activityTypeSpinnerArray;
    }
    public void setEquipmentGroupSpinnerArrayData(List<String> equipmentGroupSpinnerArray) {
        this.equipmentGroupSpinnerArray = equipmentGroupSpinnerArray;
    }
    public void setWorkoutLvlSpinnerArrayData(List<String> workoutLvlSpinnerArray) {
        this.workoutLvlSpinnerArray = workoutLvlSpinnerArray;
    }
    public void setEquipmentGroupData(String equipmentGroup) {
        this.equipmentGroup = equipmentGroup;
    }
    public void setWorkoutLvlData(String workoutLvl) {
        this.workoutLvl = workoutLvl;
    }
    public void setActivityNameData(String activityName) {
        this.activityName = activityName;
    }
    public void setIntensityLvlData(int intensityLvl) {
        this.intensityLvl = intensityLvl;
    }
    public void setDaysPerWeekData(int daysPerWeek) {
        this.daysPerWeek = daysPerWeek;
    }
    public void setActivityTypeData(String activityType) {
        this.activityType = activityType;
    }
}
