package com.example.fyp_01.activityDetail;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.fyp_01.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

public class ActivityDetailControllerTest {

    private String userName, goal, workoutGroup, intensity, equipmentGroup;

    @Rule
    public ActivityScenarioRule<ActivityDetailController> activityTestRule = new ActivityScenarioRule<>(ActivityDetailController.class);

    @Before
    public void setUp(){
        // String to be typed for User name input
       /* userName = "Sonam Gurung";
        goal = "Endurance";
        workoutGroup = "Beginner";
        intensity = "Easy";
        equipmentGroup = "None";*/
        Intents.init();
    }

    @Test
    public void quickRun(){
        onView(withId(R.id.recommendationRecyclerview))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.playButton)).perform(click());
        //await()
    }
}
