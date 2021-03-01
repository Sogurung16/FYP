package com.example.fyp_01.recommendations;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ImageButton;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.fyp_01.R;
import com.example.fyp_01.database.DatabaseHelper;
import com.example.fyp_01.user.UserModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.hasContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.*;

public class ControllerTest {

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