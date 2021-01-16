package com.example.fyp_01.recommendations;

import android.graphics.Bitmap;

public class Model {

    private String activitiesName;
    private Bitmap activitiesImage;
    private String activitiesType;

    public Model(String activitiesName, Bitmap activitiesImage, String activitiesType) {
        this.activitiesName = activitiesName;
        this.activitiesImage = activitiesImage;
        this.activitiesType = activitiesType;
    }

    public void setActivitiesName(String activitiesName) {
        this.activitiesName = activitiesName;
    }
    public void setActivitiesImage(Bitmap ActivitiesImage) {
        this.activitiesImage = ActivitiesImage;
    }
    public void setActivitiesType(String activitiesType) {
        this.activitiesType = activitiesType;
    }

    public String getActivitiesName(){
        return activitiesName;
    }
    public Bitmap getActivitiesImage(){
        return activitiesImage;
    }
    public String getActivitiesType() {
        return activitiesType;
    }
}
