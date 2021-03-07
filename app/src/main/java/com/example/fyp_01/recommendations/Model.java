package com.example.fyp_01.recommendations;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Model implements Parcelable {

    private String activitiesName, activitiesIntensityLvl, activitiesWorkoutLvl, activitiesEquipmentGroup;
    private int activitiesTime;
    private Bitmap activitiesImage;
    private String activitiesType;

    public Model(String activitiesName, String activitiesType, String activitiesIntensityLvl,
                 String activitiesWorkoutLvl, String activitiesEquipmentGroup, int activitiesTime, Bitmap activitiesImage) {
        this.activitiesName = activitiesName;
        this.activitiesType = activitiesType;
        this.activitiesIntensityLvl = activitiesIntensityLvl;
        this.activitiesWorkoutLvl = activitiesWorkoutLvl;
        this.activitiesEquipmentGroup = activitiesEquipmentGroup;
        this.activitiesTime = activitiesTime;
        this.activitiesImage = activitiesImage;
    }

    public Model(Model model){
        this.activitiesName = model.activitiesName;
        this.activitiesType = model.activitiesType;
        this.activitiesIntensityLvl = model.activitiesIntensityLvl;
        this.activitiesWorkoutLvl = model.activitiesWorkoutLvl;
        this.activitiesEquipmentGroup = model.activitiesEquipmentGroup;
        this.activitiesTime = model.activitiesTime;
        this.activitiesImage = model.activitiesImage;
    }

    public Model(){
        //create empty model
    }

    protected Model(Parcel in) {
        activitiesName = in.readString();
        activitiesIntensityLvl = in.readString();
        activitiesWorkoutLvl = in.readString();
        activitiesEquipmentGroup = in.readString();
        activitiesTime = in.readInt();
        activitiesImage = in.readParcelable(Bitmap.class.getClassLoader());
        activitiesType = in.readString();
    }

    public static final Creator<Model> CREATOR = new Creator<Model>() {
        @Override
        public Model createFromParcel(Parcel in) {
            return new Model(in);
        }

        @Override
        public Model[] newArray(int size) {
            return new Model[size];
        }
    };

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
    public void setActivitiesTime(int activitiesTime){
        this.activitiesTime = activitiesTime;
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
    public int getActivitiesTime(){return activitiesTime;}
    public Bitmap getActivitiesImage(){
        return activitiesImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(activitiesName);
        dest.writeString(activitiesIntensityLvl);
        dest.writeString(activitiesWorkoutLvl);
        dest.writeString(activitiesEquipmentGroup);
        dest.writeInt(activitiesTime);
        dest.writeParcelable(activitiesImage, flags);
        dest.writeString(activitiesType);
    }
}
