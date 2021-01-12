package com.example.fyp_01.recommendations;

public class StretchingModel {
    Integer stretchingActivitiesImages;
    String stretchingActivitiesNames;

    public StretchingModel(Integer stretchingActivitiesImages, String stretchingActivitiesNames){
        this.stretchingActivitiesImages = stretchingActivitiesImages;
        this.stretchingActivitiesNames = stretchingActivitiesNames;
    }

    public Integer getStretchingActivitiesImages(){
        return stretchingActivitiesImages;
    }
    public String getStretchingActivitiesNames(){
        return stretchingActivitiesNames;
    }
}
