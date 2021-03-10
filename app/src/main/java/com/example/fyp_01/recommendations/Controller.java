package com.example.fyp_01.recommendations;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
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

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller extends AppCompatActivity {
    AlertDialog dialog;
    AlertDialog.Builder builder;

    private TextView userPoints;

    private RecommendationAdapter recommendationAdapter;
    private StretchingAdapter stretchingAdapter;
    private EnduranceAdapter enduranceAdapter;
    private StrengthAdapter strengthAdapter;
    private PremiumAdapter premiumAdapter;

    private DatabaseHelper databaseHelper;
    private RecyclerView mStretchingRecyclerView, mEnduranceRecyclerView, mStrengthRecyclerView, mRecommendationRecyclerView, mPremiumRecyclerView;
    private ArrayList<Model> models, stretchingAdapterModels, enduranceAdapterModels, strengthAdapterModels,recommendationAdapterModels, premiumAdapterModels;
    private List<String> recommendationList;
    private Model model;
    private String activityType;
    private int premium, points;
    private boolean unlock;
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
        mPremiumRecyclerView = findViewById(R.id.premiumRecyclerview);

        userPoints = findViewById(R.id.UserPointsData);

        //Initialize Database
        databaseHelper = new DatabaseHelper(this);

        //Initialize ArrayList
        models = new ArrayList<>();
        stretchingAdapterModels = new ArrayList<>();
        enduranceAdapterModels = new ArrayList<>();
        strengthAdapterModels = new ArrayList<>();
        recommendationAdapterModels = new ArrayList<>();
        premiumAdapterModels = new ArrayList<>();

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

        //add premium activities adapter models
        premiumAdapterModels = addPremiumAdapterModels(models);

        points = getUserPoints();
        userPoints.setText(String.valueOf(points));

        //Set Adapters to RecyclerView
        setRecyclerView();
    }

    @Override
    protected void onRestart(){
        super.onRestart();

        points = getUserPoints();
        userPoints.setText(String.valueOf(points));

        //get recommendation list
        recommendationList = getRecommendation();;

        //add recommendation adapter models
        recommendationAdapterModels = new ArrayList<>();
        recommendationAdapterModels = addRecommendationAdapterModels(recommendationList);

        //add activities adapter models categorized by type
        stretchingAdapterModels = new ArrayList<>();
        stretchingAdapterModels = addStretchingAdapterModels(models);
        enduranceAdapterModels = new ArrayList<>();
        enduranceAdapterModels = addEnduranceAdapterModels(models);
        strengthAdapterModels = new ArrayList<>();
        strengthAdapterModels = addStrengthAdapterModels(models);

        //add premium activities adapter models
        premiumAdapterModels = new ArrayList<>();
        premiumAdapterModels = addPremiumAdapterModels(models);

        //Set Adapters to RecyclerView
        setRecyclerView();
    }

    public void setRecyclerView(){
        //Set Adapters to their respective RecyclerView
        recommendationAdapter = new RecommendationAdapter(Controller.this, recommendationAdapterModels);
        mRecommendationRecyclerView.setAdapter(recommendationAdapter);
        recommendationAdapter.setOnItemClickListener(new RecommendationAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(int position) {
                startIntent(recommendationAdapterModels, position);
            }
        });
        stretchingAdapter = new StretchingAdapter(Controller.this, stretchingAdapterModels);
        mStretchingRecyclerView.setAdapter(stretchingAdapter);
        stretchingAdapter.setOnItemClickListener(new StretchingAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(int position) {
                startIntent(recommendationAdapterModels, position);
            }
        });
        enduranceAdapter = new EnduranceAdapter(Controller.this, enduranceAdapterModels);
        mEnduranceRecyclerView.setAdapter(enduranceAdapter);
        enduranceAdapter.setOnItemClickListener(new EnduranceAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(int position) {
                startIntent(enduranceAdapterModels, position);
            }
        });
        strengthAdapter = new StrengthAdapter(Controller.this, strengthAdapterModels);
        mStrengthRecyclerView.setAdapter(strengthAdapter);
        strengthAdapter.setOnItemClickListener(new StrengthAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(int position) {
                startIntent(strengthAdapterModels, position);
            }
        });
        premiumAdapter = new PremiumAdapter(Controller.this, premiumAdapterModels);
        mPremiumRecyclerView.setAdapter(premiumAdapter);
        premiumAdapter.setOnItemClickListener(new PremiumAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(int position) {
                model = premiumAdapterModels.get(position);
                if(model.getPremium()!=0) {
                    builder = new AlertDialog.Builder(Controller.this);
                    builder.setTitle("Do you want to use your points to unlock this activity?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (getUserPoints() >= 9) {
                                Toast.makeText(Controller.this, "Unlocked!", Toast.LENGTH_LONG).show();
                                unlock = true;
                                points -= 9;
                                setUserPoints(points);
                                model.setPremium(0);
                                SQLiteDatabase db = DatabaseHelper.getInstance(Controller.this).getWritableDatabase();
                                db.execSQL("UPDATE activities_table SET activities_premium=" + model.getPremium()+" WHERE activities_name = " + "'"+
                                        model.getActivitiesName() + "'");
                                db.close();
                                Intent currentIntent = getIntent();
                                finish();
                                startActivity(currentIntent);

                            } else {
                                Toast.makeText(Controller.this, "Not enough Points", Toast.LENGTH_LONG).show();
                                unlock = false;
                            }
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            unlock = false;
                        }
                    });

                    dialog = builder.create();
                    dialog.show();
                    unlock = false;
                }else{
                    startIntent(premiumAdapterModels, position);
                }
            }
        });
    }

    private ArrayList<Model> addStretchingAdapterModels(ArrayList<Model> models){
        for (int i = 0; i < models.size(); i++) {
            model = new Model(models.get(i));
            activityType = model.getActivitiesType();
            premium = model.getPremium();
            if(activityType.contains("Stretching")&&premium==0) {
                stretchingAdapterModels.add(model);
            }
        }
        return stretchingAdapterModels;
    }

    private ArrayList<Model> addEnduranceAdapterModels(ArrayList<Model> models){
        for (int i = 0; i < models.size(); i++) {
            model = new Model(models.get(i));
            activityType = model.getActivitiesType();
            premium = model.getPremium();
            if(activityType.contains("Endurance")&&premium==0){
                enduranceAdapterModels.add(model);
            }
        }
        return enduranceAdapterModels;
    }

    private ArrayList<Model> addStrengthAdapterModels(ArrayList<Model> models){
        for (int i = 0; i < models.size(); i++) {
            model = new Model(models.get(i));
            activityType = model.getActivitiesType();
            premium = model.getPremium();
            if(activityType.contains("Strength")&&premium==0){
                strengthAdapterModels.add(model);
            }
        }
        return strengthAdapterModels;
    }

    private ArrayList<Model> addPremiumAdapterModels(ArrayList<Model> models){
        for (int i = 0; i < models.size(); i++) {
            model = new Model(models.get(i));
            premium = model.getPremium();
            if(model.getPremium()==1){
                premiumAdapterModels.add(model);
            }
        }
        return premiumAdapterModels;
    }

    private ArrayList<Model> addRecommendationAdapterModels(List<String> recommendationList){
        for(int i=0; i<recommendationList.size();i++) {
            recommendation = recommendationList.get(i);
            for (int j = 0; j < models.size(); j++) {
                model = new Model(models.get(j));
                premium = model.getPremium();
                if (recommendation.contains(model.getActivitiesName())&&premium==0) {
                    recommendationAdapterModels.add(model);
                }
            }
        }
        return recommendationAdapterModels;
    }

    public void designHorizontalLayout(){
        //Design Horizontal Layout
        LinearLayoutManager recommendationLayoutManager = new LinearLayoutManager(Controller.this, LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager stretchingLayoutManager = new LinearLayoutManager(Controller.this, LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager enduranceLayoutManager = new LinearLayoutManager(Controller.this, LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager strengthLayoutManager = new LinearLayoutManager(Controller.this, LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager premiumLayoutManager = new LinearLayoutManager(Controller.this, LinearLayoutManager.HORIZONTAL,false);
        mRecommendationRecyclerView.setLayoutManager(recommendationLayoutManager);
        mRecommendationRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mStretchingRecyclerView.setLayoutManager(stretchingLayoutManager);
        mStretchingRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mEnduranceRecyclerView.setLayoutManager(enduranceLayoutManager);
        mEnduranceRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mStrengthRecyclerView.setLayoutManager(strengthLayoutManager);
        mStrengthRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mPremiumRecyclerView.setLayoutManager(premiumLayoutManager);
        mPremiumRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private ArrayList<Model> fakeActivityData(){
        int[] premium;
        String[] names, intensityLvls, workoutLvls, equipmentGroups;
        int stretchingLogo, enduranceLogo, strengthLogo;
        int[] drawableIds, times;

        // Add New Activities Image data to database from drawable folder
        stretchingLogo = R.drawable.stretching;
        enduranceLogo = R.drawable.endurance;
        strengthLogo = R.drawable.strength;

        //add activities
        premium = new int[]{0, 0, 0, 0, 1};
        activityType = "Stretching";
        names = new String[]{"Yoga 101", "Yoga Core", "Legs Warmup", "Legs Cooldown", "Recovery Mobility"};
        intensityLvls = new String[]{"Easy", "Moderate", "Easy", "Easy", "Easy"};
        workoutLvls = new String[]{"Beginner", "Intermediate", "Beginner", "Beginner", "Beginner"};
        equipmentGroups = new String[]{"None", "None", "None", "None", "None"};
        times = new int[]{5000,5000,5000,5000,5000};
        drawableIds = new int[]{stretchingLogo, stretchingLogo, stretchingLogo, stretchingLogo, stretchingLogo};
        //drawableIds = new int[]{R.drawable.yoga_101, R.drawable.yoga_core, R.drawable.legs_warmup, R.drawable.legs_cooldown, R.drawable.recovery_mobility};
        addNewActivitiesData(names, activityType, intensityLvls, workoutLvls, equipmentGroups, times, drawableIds, premium);

        premium = new int[]{0, 0, 0, 0, 1};
        activityType = "Endurance";
        names = new String[]{"Speed Circuit", "Runner up", "Basic Burn", "Explosive Ignition", "Hit Challenge"};
        intensityLvls = new String[]{"Moderate", "Easy", "Easy", "Hard", "Hard"};
        workoutLvls = new String[]{"Intermediate", "Intermediate", "Beginner", "Advanced", "Intermediate"};
        equipmentGroups = new String[]{"Basic", "None", "None", "Full", "Full"};
        times = new int[]{5000,5000,5000,5000,5000};
        drawableIds = new int[]{enduranceLogo, enduranceLogo, enduranceLogo, enduranceLogo, enduranceLogo};
        //drawableIds = new int[]{R.drawable.speed_circuit, R.drawable.runner_up, R.drawable.basic_burn,R.drawable.explosive_ignition,R.drawable.hit_challenge};
        addNewActivitiesData(names, activityType, intensityLvls, workoutLvls, equipmentGroups, times, drawableIds, premium);

        premium = new int[]{0, 0, 0, 0, 1};
        activityType = "Strength";
        names = new String[]{"Quick Crush", "Upper Body Blast", "Easy Drills", "Push Pull", "Core Strength"};
        intensityLvls = new String[]{"Hard", "Moderate", "Easy", "Moderate", "Hard"};
        workoutLvls = new String[]{"Intermediate", "Intermediate", "Beginner", "Advanced", "Advanced"};
        equipmentGroups = new String[]{"None", "Basic", "None", "Full", "None"};
        times = new int[]{5000,5000,5000,5000,5000};
        drawableIds = new int[]{strengthLogo, strengthLogo, strengthLogo, strengthLogo, strengthLogo};
        addNewActivitiesData(names, activityType, intensityLvls, workoutLvls, equipmentGroups, times, drawableIds, premium);

        return models;
    }

    private void addNewActivitiesData(String[] names, String type, String[] intensityLevels, String[] workoutLevels,
                                      String[] equipmentGroups, int[] times, int[] drawableIds, int[] premium){
        Bitmap imageToStoreBitmap;
        for(int i = 0; i<drawableIds.length; i++) {
            imageToStoreBitmap = BitmapFactory.decodeResource(getResources(), drawableIds[i]);
            model = new Model(names[i], type, workoutLevels[i], intensityLevels[i],
                    equipmentGroups[i], times[i], imageToStoreBitmap, premium[i]);
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

    private void putExtraContent(Intent activityDetailIntent){
        ByteArrayOutputStream objectByteArrayOutputStream;
        byte[] imgInByte;

        Bitmap imageToStoreBitmap = model.getActivitiesImage();
        objectByteArrayOutputStream = new ByteArrayOutputStream();
        imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG, 100, objectByteArrayOutputStream);
        imgInByte = objectByteArrayOutputStream.toByteArray();

        activityDetailIntent.putExtra("name", model.getActivitiesName());
        activityDetailIntent.putExtra("type", model.getActivitiesType());
        activityDetailIntent.putExtra("workoutLvl", model.getWorkoutLvl());
        activityDetailIntent.putExtra("intensity", model.getIntensityLvl());
        activityDetailIntent.putExtra("equipmentGroup", model.getEquipmentGroup());
        activityDetailIntent.putExtra("time", model.getActivitiesTime());
        activityDetailIntent.putExtra("image", imgInByte);
    }

    private void startIntent(ArrayList<Model> adapterModels, int position){
        Intent activityDetailIntent = new Intent(Controller.this, ActivityDetailController.class);
        model = adapterModels.get(position);
        putExtraContent(activityDetailIntent);
        startActivity(activityDetailIntent);
    }

    private int getUserPoints(){
        SQLiteDatabase db = DatabaseHelper.getInstance(this).getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from users_table", null);
        cursor.moveToFirst();
        points = cursor.getInt(6);
        cursor.close();

        return points;
    }

    private void setUserPoints(int points){
        SQLiteDatabase db = DatabaseHelper.getInstance(this).getWritableDatabase();
        db.execSQL("UPDATE users_table SET users_points="+points);
        db.close();
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
