package com.example.fyp_01.recommendations;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.example.fyp_01.R;
import com.example.fyp_01.activityDetail.ActivityDetailController;
import com.example.fyp_01.database.DatabaseHelper;

import java.util.ArrayList;

public class Controller extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private RecyclerView mStretchingRecyclerView, mEnduranceRecyclerView, mStrengthRecyclerView, mRecommendationRecyclerView;

    private ArrayList<Model> models, stretchingAdapterModels, enduranceAdapterModels, strengthAdapterModels,recommendationAdapterModels;
    private Model model;
    private String activityType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_recommendation_page_recycler_container);

        //Assign Variables
        mRecommendationRecyclerView = findViewById(R.id.recommendationRecyclerview);
        mStretchingRecyclerView = findViewById(R.id.stretchingRecyclerview);
        mEnduranceRecyclerView = findViewById(R.id.enduranceRecyclerview);
        mStrengthRecyclerView = findViewById(R.id.strengthRecyclerview);
        databaseHelper = new DatabaseHelper(this);

        //Initialize ArrayList
        models = new ArrayList<>();
        stretchingAdapterModels = new ArrayList<>();
        enduranceAdapterModels = new ArrayList<>();
        strengthAdapterModels = new ArrayList<>();
        recommendationAdapterModels = new ArrayList<>();

        RecommendationAdapter recommendationAdapter;
        StretchingAdapter stretchingAdapter;
        EnduranceAdapter enduranceAdapter;
        StrengthAdapter strengthAdapter;

        models = databaseHelper.getActivitiesData();
        if(models.size() == 0) {
            activityData(); // add activity data
        }
        //Design Horizontal Layout
        LinearLayoutManager recommendationLayoutManager = new LinearLayoutManager(Controller.this, LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager stretchingLayoutManager = new LinearLayoutManager(Controller.this, LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager enduranceLayoutManager = new LinearLayoutManager(Controller.this, LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager strengthLayoutManager = new LinearLayoutManager(Controller.this, LinearLayoutManager.HORIZONTAL,false);
        mRecommendationRecyclerView.setLayoutManager(recommendationLayoutManager);
        mRecommendationRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mStretchingRecyclerView.setLayoutManager(stretchingLayoutManager);
        mStretchingRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mEnduranceRecyclerView.setLayoutManager(enduranceLayoutManager);
        mEnduranceRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mStrengthRecyclerView.setLayoutManager(strengthLayoutManager);
        mStrengthRecyclerView.setItemAnimator(new DefaultItemAnimator());

        String recommendation = getRecommendation();

        for(int i=0; i<models.size();i++){
            model = new Model(models.get(i));
            activityType = model.getActivitiesType();
            if(activityType.contains("Stretching")){
                stretchingAdapterModels.add(model);
                if(recommendation.contains(model.getActivitiesName())){
                    recommendationAdapterModels.add(model);
                }
            } else if(activityType.contains("Endurance")){
                enduranceAdapterModels.add(model);
                if(recommendation.contains(model.getActivitiesName())){
                    recommendationAdapterModels.add(model);
                }
            }
            else if(activityType.contains("Strength")){
                strengthAdapterModels.add(model);
                if(recommendation.contains(model.getActivitiesName())){
                    recommendationAdapterModels.add(model);
                }
            }
            else{
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
            }
        }
        //Set Adapter to RecyclerView
        recommendationAdapter = new RecommendationAdapter(Controller.this, recommendationAdapterModels);
        mRecommendationRecyclerView.setAdapter(recommendationAdapter);
        stretchingAdapter = new StretchingAdapter(Controller.this, stretchingAdapterModels);
        mStretchingRecyclerView.setAdapter(stretchingAdapter);
        enduranceAdapter = new EnduranceAdapter(Controller.this, enduranceAdapterModels);
        mEnduranceRecyclerView.setAdapter(enduranceAdapter);
        strengthAdapter = new StrengthAdapter(Controller.this, strengthAdapterModels);
        mStrengthRecyclerView.setAdapter(strengthAdapter);
    }

    public void navigateToActivityDetailPage(View view){
        Intent activityDetailIntent = new Intent(this, ActivityDetailController.class);
        startActivity(activityDetailIntent);
    }


    private void activityData(){
        String[] names, intensityLvls, workoutLvls, equipmentGroups;
        int stretchingLogo, enduranceLogo, strengthLogo;
        int[] drawableIds;

        // Add New Activities Image data to database from drawable folder
        stretchingLogo = R.drawable.stretching;
        enduranceLogo = R.drawable.endurance;
        strengthLogo = R.drawable.strength;

        //add activities
        activityType = "Stretching";
        names = new String[]{"Yoga 101", "Yoga Core", "Legs Warmup", "Legs Cooldown", "Recovery Mobility"};
        intensityLvls = new String[]{"Easy", "Moderate", "Easy", "Easy", "Easy"};
        workoutLvls = new String[]{"Beginner", "Intermediate", "Beginner", "Beginner", "Beginner"};
        equipmentGroups = new String[]{"None", "None", "None", "None", "None"};
        drawableIds = new int[]{stretchingLogo, stretchingLogo, stretchingLogo, stretchingLogo, stretchingLogo};
        //drawableIds = new int[]{R.drawable.yoga_101, R.drawable.yoga_core, R.drawable.legs_warmup, R.drawable.legs_cooldown, R.drawable.recovery_mobility};
        addNewActivitiesData(names, activityType, intensityLvls, workoutLvls, equipmentGroups, drawableIds);

        activityType = "Endurance";
        names = new String[]{"Speed Circuit", "Runner up", "Basic Burn", "Explosive Ignition", "Hit Challenge"};
        intensityLvls = new String[]{"Moderate", "Easy", "Easy", "Hard", "Hard"};
        workoutLvls = new String[]{"Intermediate", "Intermediate", "Beginner", "Advanced", "Intermediate"};
        equipmentGroups = new String[]{"Basic", "None", "None", "Full", "Full"};
        drawableIds = new int[]{enduranceLogo, enduranceLogo, enduranceLogo, enduranceLogo, enduranceLogo};
        //drawableIds = new int[]{R.drawable.speed_circuit, R.drawable.runner_up, R.drawable.basic_burn,R.drawable.explosive_ignition,R.drawable.hit_challenge};
        addNewActivitiesData(names, activityType, intensityLvls, workoutLvls, equipmentGroups, drawableIds);

        activityType = "Strength";
        names = new String[]{"Quick Crush", "Upper Body Blast", "Easy Drills", "Push Pull", "Core Strength"};
        intensityLvls = new String[]{"Hard", "Moderate", "Easy", "Moderate", "Hard"};
        workoutLvls = new String[]{"Intermediate", "Intermediate", "Beginner", "Advanced", "Advanced"};
        equipmentGroups = new String[]{"None", "Basic", "None", "Full", "None"};
        drawableIds = new int[]{strengthLogo, strengthLogo, strengthLogo, strengthLogo, strengthLogo};
        addNewActivitiesData(names, activityType, intensityLvls, workoutLvls, equipmentGroups, drawableIds);
    }

    private void addNewActivitiesData(String[] names, String type, String[] intensityLevels, String[] workoutLevels, String[] equipmentGroups,int[] drawableIds){
        Bitmap imageToStoreBitmap;
        for(int i = 0; i<drawableIds.length; i++) {
            imageToStoreBitmap = BitmapFactory.decodeResource(getResources(), drawableIds[i]);
            model = new Model(names[i], type, intensityLevels[i], workoutLevels[i], equipmentGroups[i], imageToStoreBitmap);
            models.add(model);

            databaseHelper.addActivityData(model);
        }
    }

    // Python script connects to the local database. Processes user and activities table to generate recommendation list
    public String getRecommendation(){
        if(!Python.isStarted()){
            Python.start(new AndroidPlatform(this));
        }

        Python py = Python.getInstance();
        PyObject pyObj = py.getModule("RecommendationEngine");
        PyObject obj = pyObj.callAttr("main");

        String recommendation = obj.toString();
        return recommendation;
    }

}
