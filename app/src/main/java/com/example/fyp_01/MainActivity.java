package com.example.fyp_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private boolean fatBurn;
    private boolean muscleBuild;
    private int intensityLvl;

    private TextView mIntensityLvl = findViewById(R.id.intensityLvlValue);
    private TextView mFatBurn = findViewById(R.id.fatBurnValue);
    private TextView mMuscleBuild = findViewById(R.id.muscleBuildValue);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        joggingActivity();
    }

    private String IntensityLevels(int intensityLvl){
        String stringIntensity;

        switch (intensityLvl){
            case (1):
                stringIntensity = "easy";
                break;
            case (2):
                stringIntensity = "moderate";
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

    private String FatBurn(boolean fatBurn){
        String stringFatBurn;

        if (fatBurn == true){
            stringFatBurn = "Yes";
        }
        else{
            stringFatBurn = "No";
        }
        return stringFatBurn;
    }

    private String MuscleBuild(boolean muscleBuild){
        String stringMuscleBuild;

        if (muscleBuild == true){
            stringMuscleBuild = "Yes";
        }
        else{
            stringMuscleBuild = "No";
        }
        return stringMuscleBuild;
    }

    private void joggingActivity(){
        fatBurn = true;
        muscleBuild = false;
        intensityLvl = 2;

        mIntensityLvl.setText(IntensityLevels(intensityLvl));
        mFatBurn.setText(FatBurn(fatBurn));
        mMuscleBuild.setText(MuscleBuild(muscleBuild));
    }
}
