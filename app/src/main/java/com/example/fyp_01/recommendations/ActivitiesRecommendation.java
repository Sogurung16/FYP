package com.example.fyp_01.recommendations;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fyp_01.R;

import java.util.ArrayList;

public class ActivitiesRecommendation extends AppCompatActivity {
    //Initialize Variables
    RecyclerView mStretchingRecyclerView;

    ArrayList<StretchingModel> stretchingModels;
    StretchingAdapter stretchingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_recommendation_page_recycler_container);

        //Assign Variables
        mStretchingRecyclerView = findViewById(R.id.stretchingRecyclerview);

        //Creating Integer Array for Images
        Integer[] stretchingActivitiesImages = {R.drawable.yoga_101, R.drawable.yoga_core, R.drawable.legs_warmup,
                R.drawable.legs_cooldown, R.drawable.recovery_mobility};

        //Creating String Array for texts
        String[] stretchingActivitiesNames = {"Yoga 101", "YogaCore", "Legs Warm up", "Legs Cool down",
        "Recovery Mobility"};

        //Initialize ArrayList
        stretchingModels = new ArrayList<>();
        for(int i=0;i<stretchingActivitiesImages.length;i++){
            StretchingModel stretchingModel = new StretchingModel(stretchingActivitiesImages[i], stretchingActivitiesNames[i]);
            stretchingModels.add(stretchingModel);
        }

        //Design Horizontal Layout
        LinearLayoutManager layoutManager = new LinearLayoutManager(ActivitiesRecommendation.this, LinearLayoutManager.HORIZONTAL,false);
        mStretchingRecyclerView.setLayoutManager(layoutManager);
        mStretchingRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //Initialize StretchingAdapter
        stretchingAdapter = new StretchingAdapter(ActivitiesRecommendation.this, stretchingModels);
        //Set StretchingAdapter to RecyclerView
        mStretchingRecyclerView.setAdapter(stretchingAdapter);
    }
}
