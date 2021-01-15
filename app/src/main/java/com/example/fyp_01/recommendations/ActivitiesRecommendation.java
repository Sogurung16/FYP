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
    Context context;
    DatabaseHelper databaseHelper;
    RecyclerView mStretchingRecyclerView;

    ArrayList<StretchingModel> stretchingModels;
    StretchingAdapter stretchingAdapter;
    String activitiesName;
    Bitmap imageToStoreBitmap;
    int[] drawableIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_recommendation_page_recycler_container);

        //Assign Variables
        mStretchingRecyclerView = findViewById(R.id.stretchingRecyclerview);
        databaseHelper = new DatabaseHelper(this);

        //Initialize ArrayList
        stretchingModels = new ArrayList<>();

        // Add New Activities Image data to database from drawable folder
        /*drawableIds = new int[]{};
        addMultipleNewActivitiesImageData(drawableIds);*/
        //addNewActivitiesImageData(R.drawable.recovery_mobility);

        stretchingModels = databaseHelper.getActivitiesImageData();

        //Design Horizontal Layout
        LinearLayoutManager layoutManager = new LinearLayoutManager(ActivitiesRecommendation.this, LinearLayoutManager.HORIZONTAL,false);
        mStretchingRecyclerView.setLayoutManager(layoutManager);
        mStretchingRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //Initialize StretchingAdapter
        stretchingAdapter = new StretchingAdapter(ActivitiesRecommendation.this, stretchingModels);
        //Set StretchingAdapter to RecyclerView
        mStretchingRecyclerView.setAdapter(stretchingAdapter);
    }

    private void addNewActivitiesImageData(int drawableId){
        imageToStoreBitmap = BitmapFactory.decodeResource(getResources(), drawableId);
        activitiesName = getResources().getResourceEntryName(drawableId);

        StretchingModel stretchingModel = new StretchingModel(activitiesName, imageToStoreBitmap);
        stretchingModels.add(stretchingModel);

        databaseHelper.addActivitiesImageData(stretchingModel);
    }

    private void addMultipleNewActivitiesImageData(int[] drawableIds){
        StretchingModel stretchingModel;

        for(int i = 0; i<drawableIds.length; i++){
            imageToStoreBitmap = BitmapFactory.decodeResource(getResources(), drawableIds[i]);
            activitiesName = getResources().getResourceEntryName(drawableIds[i]);

            stretchingModel = new StretchingModel(activitiesName, imageToStoreBitmap);
            stretchingModels.add(stretchingModel);

            databaseHelper.addActivitiesImageData(stretchingModel);
        }
    }

}
