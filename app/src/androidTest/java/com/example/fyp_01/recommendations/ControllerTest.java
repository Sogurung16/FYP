package com.example.fyp_01.recommendations;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.fyp_01.database.DatabaseHelper;
import com.example.fyp_01.user.UserController;
import com.example.fyp_01.user.UserModel;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.junit.Assert.*;

public class ControllerTest {
    Context context;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    UserModel user;
    Controller controller;

    String userName, goal, workoutGroup, intensity, equipmentGroup;

    @Rule
    public ActivityScenarioRule<Controller> activityTestRule = new ActivityScenarioRule<>(Controller.class);

    @Before
    public void setUp(){
        userName = "Sonam Gurung";
        goal = "Endurance";
        workoutGroup = "Beginner";
        intensity = "Easy";
        equipmentGroup = "None";

        Intents.init();
    }

    @After
    public void tearDown(){
        Intents.release();
    }
}