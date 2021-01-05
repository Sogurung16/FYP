package com.example.fyp_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ActivitiesPage extends AppCompatActivity {

    private TextView mIntensityLvl, mFatBurn, mMuscleBuild, mActivityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation_page);

        mIntensityLvl = findViewById(R.id.intensityLvlValue);
        mFatBurn = findViewById(R.id.fatBurnValue);
        mMuscleBuild = findViewById(R.id.muscleBuildValue);
        mActivityName = findViewById(R.id.activity_tiltle);

        ActivitiesData data = new ActivitiesData();
        data.setActivityName("Jogging");
        data.setFatBurn(true);
        data.setMuscleBuild(false);
        data.setIntensityLvl(1);

        mActivityName.setText(data.getActivityName());
        mIntensityLvl.setText(IntensityLevelsIntToString(data.getIntensityLvl()));
        mFatBurn.setText(FatBurnBoolToString(data.getFatBurn()));
        mMuscleBuild.setText(MuscleBuildBoolToString(data.getMuscleBuild()));
    }

    private String IntensityLevelsIntToString(int intensityLvl){
        String stringIntensity;

        switch (intensityLvl){
            case (1):
                stringIntensity = "Easy";
                break;
            case (2):
                stringIntensity = "Moderate";
                break;
            case (3):
                stringIntensity = "Extreme";
                break;
            default:
                stringIntensity = "";
                break;
        }
        return  stringIntensity;
    }

    private String FatBurnBoolToString(boolean fatBurn){
        String stringFatBurn;

        if (fatBurn == true){
            stringFatBurn = "Yes";
        }
        else{
            stringFatBurn = "No";
        }
        return stringFatBurn;
    }

    private String MuscleBuildBoolToString(boolean muscleBuild){
        String stringMuscleBuild;

        if (muscleBuild == true){
            stringMuscleBuild = "Yes";
        }
        else{
            stringMuscleBuild = "No";
        }
        return stringMuscleBuild;
    }


    //TODO: 1) refactor jogging activity. Will have activities database in the future. Assign data values depending on the database.
}