package com.example.fyp_01;

import android.widget.TextView;

public class ActivitiesData extends RecommendationPage{

    private boolean fatBurn;
    private boolean muscleBuild;
    private int intensityLvl;

    public Boolean getFatBurn(){
        return fatBurn;
    }
    public void setFatBurn(boolean fatBurn){
        this.fatBurn = fatBurn;
    }

    public int getIntensityLvl() {
        return intensityLvl;
    }
    public void setIntensityLvl(int intensityLvl) {
        this.intensityLvl = intensityLvl;
    }

    public boolean getMuscleBuild() {
        return muscleBuild;
    }
    public void setMuscleBuild(boolean muscleBuild) {
        this.muscleBuild = muscleBuild;
    }

}
