package com.example.fyp_01.recommendations;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_recommendation_page_recycler_container);

        //Assign Variables
        mStretchingRecyclerView = findViewById(R.id.stretchingRecyclerview);
        databaseHelper = new DatabaseHelper(this);

        Bitmap imageToStoreBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.yoga_101);
        String activitiesName = String.valueOf(R.drawable.yoga_101);
//        Bitmap imageToStoreBitmap = model.getStretchingActivitiesImage();

        //Initialize ArrayList
        stretchingModels = new ArrayList<>();

        StretchingModel stretchingModel = new StretchingModel(activitiesName, imageToStoreBitmap);
        stretchingModel.setStretchingActivitiesImage(imageToStoreBitmap);
        stretchingModel.setStretchingActivitiesName(activitiesName);
        stretchingModels.add(stretchingModel);

        databaseHelper.addActivitiesImageData(stretchingModel);

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
