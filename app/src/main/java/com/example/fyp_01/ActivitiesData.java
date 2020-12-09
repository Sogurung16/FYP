package com.example.fyp_01;

public class ActivitiesData extends Activities {

    private boolean fatBurn;
    private boolean muscleBuild;
    private int intensityLvl;
    private String activityName;

    public String getActivityName() { return activityName; }
    public void setActivityName(String activityName) { this.activityName = activityName; }

    public Boolean getFatBurn(){
        return fatBurn;
    }
    public void setFatBurn(boolean fatBurn){ this.fatBurn = fatBurn; }

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
