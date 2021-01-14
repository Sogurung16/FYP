package com.example.fyp_01.recommendations;

import android.graphics.Bitmap;

public class StretchingModel {

    private String stretchingActivitiesName;
    private Bitmap stretchingActivitiesImage;

    public StretchingModel(String stretchingActivitiesName, Bitmap stretchingActivitiesImage) {
        this.stretchingActivitiesName = stretchingActivitiesName;
        this.stretchingActivitiesImage = stretchingActivitiesImage;
    }

    public void setStretchingActivitiesName(String stretchingActivitiesName) {
        this.stretchingActivitiesName = stretchingActivitiesName;
    }
    public void setStretchingActivitiesImage(Bitmap stretchingActivitiesImage) {
        this.stretchingActivitiesImage = stretchingActivitiesImage;
    }

    public String getStretchingActivitiesName(){
        return stretchingActivitiesName;
    }
    public Bitmap getStretchingActivitiesImage(){
        return stretchingActivitiesImage;
    }
}
