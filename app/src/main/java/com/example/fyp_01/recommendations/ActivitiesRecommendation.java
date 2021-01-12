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

    StretchingAdapter stretchingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_recommendation_page_recycler_container);

        //Assign Variables
        mStretchingRecyclerView = findViewById(R.id.stretchingRecyclerview);

        //Design Horizontal Layout
        LinearLayoutManager layoutManager = new LinearLayoutManager(ActivitiesRecommendation.this, LinearLayoutManager.HORIZONTAL,false);
        mStretchingRecyclerView.setLayoutManager(layoutManager);
        mStretchingRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //Initialize StretchingAdapter

        //Set StretchingAdapter to RecyclerView
        mStretchingRecyclerView.setAdapter(stretchingAdapter);
    }
}
