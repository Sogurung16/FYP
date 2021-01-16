package com.example.fyp_01.recommendations;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fyp_01.R;
import com.example.fyp_01.database.DatabaseHelper;

import java.util.ArrayList;

public class ActivitiesRecommendation extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    RecyclerView mStretchingRecyclerView;
    RecyclerView mEnduranceRecyclerView;

    ArrayList<Model> models;
    StretchingAdapter stretchingAdapter;
    EnduranceAdapter enduranceAdapter;
    String activitiesName, activityType;
    Bitmap imageToStoreBitmap;
    int[] stretchingDrawableIds, enduranceDrawableIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_recommendation_page_recycler_container);

        //Assign Variables
        mStretchingRecyclerView = findViewById(R.id.stretchingRecyclerview);
        mEnduranceRecyclerView = findViewById(R.id.enduranceRecyclerview);
        databaseHelper = new DatabaseHelper(this);

        //Initialize ArrayList
        models = new ArrayList<>();

        // Add New Activities Image data to database from drawable folder
        activityType = "Stretching";
        /*stretchingDrawableIds = new int[]{R.drawable.yoga_101, R.drawable.yoga_core, R.drawable.legs_warmup, R.drawable.legs_cooldown, R.drawable.recovery_mobility};
        addMultipleNewActivitiesImageData(stretchingDrawableIds);
        activityType = "Endurance";
        enduranceDrawableIds = new int[]{R.drawable.speed_circuit, R.drawable.runner_up, R.drawable.basic_burn,R.drawable.explosive_ignition,R.drawable.hit_challenge};
        addMultipleNewActivitiesImageData(enduranceDrawableIds);*/
        //addNewActivitiesImageData(R.drawable.recovery_mobility);

        //Design Horizontal Layout
        LinearLayoutManager stretchingLayoutManager = new LinearLayoutManager(ActivitiesRecommendation.this, LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager enduranceLayoutManager = new LinearLayoutManager(ActivitiesRecommendation.this, LinearLayoutManager.HORIZONTAL,false);
        mStretchingRecyclerView.setLayoutManager(stretchingLayoutManager);
        mStretchingRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mEnduranceRecyclerView.setLayoutManager(enduranceLayoutManager);
        mEnduranceRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //Initialize Adapters
        activityType = "Stretching";
        models = databaseHelper.getActivitiesImageData(activityType);
        stretchingAdapter = new StretchingAdapter(ActivitiesRecommendation.this, models);
        activityType = "Endurance";
        models = databaseHelper.getActivitiesImageData(activityType);
        enduranceAdapter = new EnduranceAdapter(ActivitiesRecommendation.this, models);
        //Set StretchingAdapter to RecyclerView
        mStretchingRecyclerView.setAdapter(stretchingAdapter);
        mEnduranceRecyclerView.setAdapter(enduranceAdapter);
    }

    private void addNewActivitiesImageData(int drawableId){
        imageToStoreBitmap = BitmapFactory.decodeResource(getResources(), drawableId);
        activitiesName = getResources().getResourceEntryName(drawableId);

        Model model = new Model(activitiesName, imageToStoreBitmap, activityType);
        models.add(model);

        databaseHelper.addActivitiesImageData(model);
    }

    private void addMultipleNewActivitiesImageData(int[] drawableIds){
        Model model;

        for(int i = 0; i<drawableIds.length; i++){
            imageToStoreBitmap = BitmapFactory.decodeResource(getResources(), drawableIds[i]);
            activitiesName = getResources().getResourceEntryName(drawableIds[i]);

            model = new Model(activitiesName, imageToStoreBitmap, activityType);
            models.add(model);

            databaseHelper.addActivitiesImageData(model);
        }
    }

}
