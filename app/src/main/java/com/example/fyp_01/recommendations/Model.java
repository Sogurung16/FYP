package com.example.fyp_01.recommendations;

import android.graphics.Bitmap;

public class Model{

    private String activitiesName, activitiesIntensityLvl, activitiesWorkoutLvl, activitiesEquipmentGroup;
    private Bitmap activitiesImage;
    private String activitiesType;

    public Model(String activitiesName, String activitiesType, String activitiesIntensityLvl,
                 String activitiesWorkoutLvl, String activitiesEquipmentGroup, Bitmap activitiesImage) {
        this.activitiesName = activitiesName;
        this.activitiesType = activitiesType;
        this.activitiesIntensityLvl = activitiesIntensityLvl;
        this.activitiesWorkoutLvl = activitiesWorkoutLvl;
        this.activitiesEquipmentGroup = activitiesEquipmentGroup;
        this.activitiesImage = activitiesImage;
    }

    public Model(Model model){
        this.activitiesName = model.activitiesName;
        this.activitiesType = model.activitiesType;
        this.activitiesIntensityLvl = model.activitiesIntensityLvl;
        this.activitiesWorkoutLvl = model.activitiesWorkoutLvl;
        this.activitiesEquipmentGroup = model.activitiesEquipmentGroup;
        this.activitiesImage = model.activitiesImage;
    }

    public void setActivitiesName(String activitiesName) {
        this.activitiesName = activitiesName;
    }
    public void setActivitiesType(String activitiesType) {
        this.activitiesType = activitiesType;
    }
    public void setIntensityLvlData(String activitiesIntensityLvl) {
        this.activitiesIntensityLvl = activitiesIntensityLvl;
    }
    public void setWorkoutLvlData(String activitiesWorkoutLvl) {
        this.activitiesWorkoutLvl = activitiesWorkoutLvl;
    }
    public void setEquipmentGroupData(String activitiesEquipmentGroup) {
        this.activitiesEquipmentGroup = activitiesEquipmentGroup;
    }
    public void setActivitiesImage(Bitmap ActivitiesImage) {
        this.activitiesImage = ActivitiesImage;
    }


    public String getActivitiesName(){
        return activitiesName;
    }
    public String getActivitiesType() {
        return activitiesType;
    }
    public String getIntensityLvl() {
        return activitiesIntensityLvl;
    }
    public String getWorkoutLvl() {
        return activitiesWorkoutLvl;
    }
    public String getEquipmentGroup() {
        return activitiesEquipmentGroup;
    }
    public Bitmap getActivitiesImage(){
        return activitiesImage;
    }
}
