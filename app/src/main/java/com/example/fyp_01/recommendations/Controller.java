package com.example.fyp_01.recommendations;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

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
import java.util.Arrays;
import java.util.List;

public class Controller extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private RecyclerView mStretchingRecyclerView, mEnduranceRecyclerView, mStrengthRecyclerView, mRecommendationRecyclerView;
    private ArrayList<Model> models, stretchingAdapterModels, enduranceAdapterModels, strengthAdapterModels,recommendationAdapterModels;
    private List<String> recommendationList;
    private Model model;
    private String activityType;
    private String recommendation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_recommendation_page_recycler_container);

        //Assign Variables
        mRecommendationRecyclerView = findViewById(R.id.recommendationRecyclerview);
        mStretchingRecyclerView = findViewById(R.id.stretchingRecyclerview);
        mEnduranceRecyclerView = findViewById(R.id.enduranceRecyclerview);
        mStrengthRecyclerView = findViewById(R.id.strengthRecyclerview);

        //Initialize Database
        databaseHelper = new DatabaseHelper(this);

        //Initialize ArrayList
        models = new ArrayList<>();
        stretchingAdapterModels = new ArrayList<>();
        enduranceAdapterModels = new ArrayList<>();
        strengthAdapterModels = new ArrayList<>();
        recommendationAdapterModels = new ArrayList<>();

        //get activities data, if no activities data then add fake activity data.
        models = databaseHelper.getActivitiesData();
        if(models.size() == 0) {
            models = fakeActivityData(); // add fake activity data
        }

        designHorizontalLayout();

        //get recommendation list
        recommendationList = getRecommendation();;

        //add recommendation adapter models
        recommendationAdapterModels = addRecommendationAdapterModels(recommendationList);

        //add activities adapter models categorized by type
        stretchingAdapterModels = addStretchingAdapterModels(models);
        enduranceAdapterModels = addEnduranceAdapterModels(models);
        strengthAdapterModels = addStrengthAdapterModels(models);

        //Set Adapters to RecyclerView
        setRecyclerView();
    }

    private void setRecyclerView(){
        //Initialize adapters
        RecommendationAdapter recommendationAdapter;
        StretchingAdapter stretchingAdapter;
        EnduranceAdapter enduranceAdapter;
        StrengthAdapter strengthAdapter;
        //Set Adapters to their respective RecyclerView
        recommendationAdapter = new RecommendationAdapter(Controller.this, recommendationAdapterModels);
        mRecommendationRecyclerView.setAdapter(recommendationAdapter);
        stretchingAdapter = new StretchingAdapter(Controller.this, stretchingAdapterModels);
        mStretchingRecyclerView.setAdapter(stretchingAdapter);
        enduranceAdapter = new EnduranceAdapter(Controller.this, enduranceAdapterModels);
        mEnduranceRecyclerView.setAdapter(enduranceAdapter);
        strengthAdapter = new StrengthAdapter(Controller.this, strengthAdapterModels);
        mStrengthRecyclerView.setAdapter(strengthAdapter);
    }

    private ArrayList<Model> addStretchingAdapterModels(ArrayList<Model> models){
        for (int i = 0; i < models.size(); i++) {
            model = new Model(models.get(i));
            activityType = model.getActivitiesType();
            if(activityType.contains("Stretching")) {
                stretchingAdapterModels.add(model);
            }
        }
        return stretchingAdapterModels;
    }

    private ArrayList<Model> addEnduranceAdapterModels(ArrayList<Model> models){
        for (int i = 0; i < models.size(); i++) {
            model = new Model(models.get(i));
            activityType = model.getActivitiesType();
            if(activityType.contains("Endurance")){
                enduranceAdapterModels.add(model);
            }
        }
        return enduranceAdapterModels;
    }

    private ArrayList<Model> addStrengthAdapterModels(ArrayList<Model> models){
        for (int i = 0; i < models.size(); i++) {
            model = new Model(models.get(i));
            activityType = model.getActivitiesType();
            if(activityType.contains("Strength")){
                strengthAdapterModels.add(model);
            }
        }
        return strengthAdapterModels;
    }

    private ArrayList<Model> addRecommendationAdapterModels(List<String> recommendationList){
        for(int i=0; i<recommendationList.size();i++) {
            recommendation = recommendationList.get(i);
            for (int j = 0; j < models.size(); j++) {
                model = new Model(models.get(j));
                if (recommendation.contains(model.getActivitiesName())) {
                    recommendationAdapterModels.add(model);
                }
            }
        }
        return recommendationAdapterModels;
    }

    private void designHorizontalLayout(){
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
    }

    public void navigateToActivityDetailPage(View view){
        Intent activityDetailIntent = new Intent(this, ActivityDetailController.class);
        startActivity(activityDetailIntent);
    }

    private ArrayList<Model> fakeActivityData(){
        String[] names, intensityLvls, workoutLvls, equipmentGroups;
        int stretchingLogo, enduranceLogo, strengthLogo;
        int[] drawableIds, times;

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
        times = new int[]{5,5,5,5,5};
        drawableIds = new int[]{stretchingLogo, stretchingLogo, stretchingLogo, stretchingLogo, stretchingLogo};
        //drawableIds = new int[]{R.drawable.yoga_101, R.drawable.yoga_core, R.drawable.legs_warmup, R.drawable.legs_cooldown, R.drawable.recovery_mobility};
        addNewActivitiesData(names, activityType, intensityLvls, workoutLvls, equipmentGroups, times, drawableIds);

        activityType = "Endurance";
        names = new String[]{"Speed Circuit", "Runner up", "Basic Burn", "Explosive Ignition", "Hit Challenge"};
        intensityLvls = new String[]{"Moderate", "Easy", "Easy", "Hard", "Hard"};
        workoutLvls = new String[]{"Intermediate", "Intermediate", "Beginner", "Advanced", "Intermediate"};
        equipmentGroups = new String[]{"Basic", "None", "None", "Full", "Full"};
        times = new int[]{5,5,5,5,5};
        drawableIds = new int[]{enduranceLogo, enduranceLogo, enduranceLogo, enduranceLogo, enduranceLogo};
        //drawableIds = new int[]{R.drawable.speed_circuit, R.drawable.runner_up, R.drawable.basic_burn,R.drawable.explosive_ignition,R.drawable.hit_challenge};
        addNewActivitiesData(names, activityType, intensityLvls, workoutLvls, equipmentGroups, times, drawableIds);

        activityType = "Strength";
        names = new String[]{"Quick Crush", "Upper Body Blast", "Easy Drills", "Push Pull", "Core Strength"};
        intensityLvls = new String[]{"Hard", "Moderate", "Easy", "Moderate", "Hard"};
        workoutLvls = new String[]{"Intermediate", "Intermediate", "Beginner", "Advanced", "Advanced"};
        equipmentGroups = new String[]{"None", "Basic", "None", "Full", "None"};
        times = new int[]{5,5,5,5,5};
        drawableIds = new int[]{strengthLogo, strengthLogo, strengthLogo, strengthLogo, strengthLogo};
        addNewActivitiesData(names, activityType, intensityLvls, workoutLvls, equipmentGroups, times, drawableIds);

        return models;
    }

    private void addNewActivitiesData(String[] names, String type, String[] intensityLevels, String[] workoutLevels,
                                      String[] equipmentGroups, int[] times, int[] drawableIds){
        Bitmap imageToStoreBitmap;
        for(int i = 0; i<drawableIds.length; i++) {
            imageToStoreBitmap = BitmapFactory.decodeResource(getResources(), drawableIds[i]);
            model = new Model(names[i], type, intensityLevels[i], workoutLevels[i],
                    equipmentGroups[i], times[i], imageToStoreBitmap);
            models.add(model);

            databaseHelper.addActivityData(model);
        }
    }

    // Python script connects to the local database. Processes user and activities table to generate recommendation list
    private List<String> getRecommendation(){
        if(!Python.isStarted()){
            Python.start(new AndroidPlatform(this));
        }

        Python py = Python.getInstance();
        PyObject pyObj = py.getModule("RecommendationEngine");
        PyObject obj = pyObj.callAttr("main");

        String recommendation = obj.toString();

        List<String> recommendationList = Arrays.asList(recommendation.split("\n"));
        return recommendationList;
    }

    public List<String> getRecommendationList() {
        return recommendationList;
    }
    public void setRecommendationList(List<String> recommendationList) {
        this.recommendationList = recommendationList;
    }

    public ArrayList<Model> getModels() {
        return models;
    }
    public void setModels(ArrayList<Model> models) {
        this.models = models;
    }

    public ArrayList<Model> getStretchingAdapterModels() {
        return stretchingAdapterModels;
    }
    public void setStretchingAdapterModels(ArrayList<Model> stretchingAdapterModels) {
        this.stretchingAdapterModels = stretchingAdapterModels;
    }

    public ArrayList<Model> getEnduranceAdapterModels() {
        return enduranceAdapterModels;
    }
    public void setEnduranceAdapterModels(ArrayList<Model> enduranceAdapterModels) {
        this.enduranceAdapterModels = enduranceAdapterModels;
    }

    public ArrayList<Model> getStrengthAdapterModels() {
        return strengthAdapterModels;
    }
    public void setStrengthAdapterModels(ArrayList<Model> strengthAdapterModels) {
        this.strengthAdapterModels = strengthAdapterModels;
    }

    public ArrayList<Model> getRecommendationAdapterModels() {
        return recommendationAdapterModels;
    }
    public void setRecommendationAdapterModels(ArrayList<Model> recommendationAdapterModels) {
        this.recommendationAdapterModels = recommendationAdapterModels;
    }
}
