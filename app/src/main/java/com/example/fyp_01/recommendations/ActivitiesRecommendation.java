package com.example.fyp_01.recommendations;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Toast;

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

    ArrayList<Model> models, stretchingAdapterModels, enduranceAdapterModels, defaultAdapterModels;
    Model model;
    Bitmap imageToStoreBitmap;

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
        stretchingAdapterModels = new ArrayList<>();
        enduranceAdapterModels = new ArrayList<>();
        defaultAdapterModels = new ArrayList<>();

        String activityType;
        StretchingAdapter stretchingAdapter;
        EnduranceAdapter enduranceAdapter;
        String[] names, intenstiyLvls, workoutLvls, equipmentGroups;
        int[] drawableIds;

        // Add New Activities Image data to database from drawable folder
       /* activityType = "Stretching";
        names = new String[]{"Yoga 101", "Yoga Core", "Legs Warmup", "Legs Cooldown", "Recovery Mobility"};
        intenstiyLvls = new String[]{"Easy", "Moderate", "Easy", "Easy", "Easy"};
        workoutLvls = new String[]{"Beginner", "Intermediate", "Beginner", "Beginner", "Beginner"};
        equipmentGroups = new String[]{"None", "None", "None", "None", "None"};
        drawableIds = new int[]{R.drawable.yoga_101, R.drawable.yoga_core, R.drawable.legs_warmup, R.drawable.legs_cooldown, R.drawable.recovery_mobility};
        addNewActivitiesData(names, activityType, intenstiyLvls, workoutLvls, equipmentGroups, drawableIds);

        activityType = "Endurance";
        names = new String[]{"Speed Circuit", "Runner up", "Basic Burn", "Explosive Ignition", "Hit Challenge"};
        intenstiyLvls = new String[]{"Easy", "Moderate", "Easy", "Easy", "Easy"};
        workoutLvls = new String[]{"Beginner", "Intermediate", "Beginner", "Beginner", "Beginner"};
        equipmentGroups = new String[]{"Basic", "None", "None", "Full", "Full"};
        drawableIds = new int[]{R.drawable.speed_circuit, R.drawable.runner_up, R.drawable.basic_burn,R.drawable.explosive_ignition,R.drawable.hit_challenge};
        addNewActivitiesData(names, activityType, intenstiyLvls, workoutLvls, equipmentGroups, drawableIds);*/

        //add strength activities

        //Design Horizontal Layout
        LinearLayoutManager stretchingLayoutManager = new LinearLayoutManager(ActivitiesRecommendation.this, LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager enduranceLayoutManager = new LinearLayoutManager(ActivitiesRecommendation.this, LinearLayoutManager.HORIZONTAL,false);
        mStretchingRecyclerView.setLayoutManager(stretchingLayoutManager);
        mStretchingRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mEnduranceRecyclerView.setLayoutManager(enduranceLayoutManager);
        mEnduranceRecyclerView.setItemAnimator(new DefaultItemAnimator());

        models = databaseHelper.getActivitiesData();

        for(int i=0; i<models.size();i++){
            model = new Model(models.get(i));
            activityType = model.getActivitiesType();
            if(activityType.contains("Stretching")){
                stretchingAdapterModels.add(model);
            } else if(activityType.contains("Endurance")){
                enduranceAdapterModels.add(model);
            }
            else{
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
            }
        }
        //Set Adapter to RecyclerView
        stretchingAdapter = new StretchingAdapter(ActivitiesRecommendation.this, stretchingAdapterModels);
        mStretchingRecyclerView.setAdapter(stretchingAdapter);
        enduranceAdapter = new EnduranceAdapter(ActivitiesRecommendation.this, enduranceAdapterModels);
        mEnduranceRecyclerView.setAdapter(enduranceAdapter);
    }

    private void addNewActivitiesData(String[] names, String type, String[] intensityLevels, String[] workoutLevels, String[] equipmentGroups,int[] drawableIds){
        for(int i = 0; i<drawableIds.length; i++) {
            imageToStoreBitmap = BitmapFactory.decodeResource(getResources(), drawableIds[i]);
            model = new Model(names[i], type, intensityLevels[i], workoutLevels[i], equipmentGroups[i], imageToStoreBitmap);
            models.add(model);

            databaseHelper.addActivityData(model);
        }
    }

}
