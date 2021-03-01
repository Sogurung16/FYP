package com.example.fyp_01.recommendations;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.fyp_01.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;;

public class RecommendationAdapterTest {
    @Rule
    public ActivityScenarioRule<Controller> activityTestRule = new ActivityScenarioRule<>(Controller.class);

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    @Before
    public void setUp(){
        Intents.init();
    }

    /*@Test //iteration 3 test
    public void testRecommendationsClick(){
        onView(withId(R.id.recommendationRecyclerview))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }*/

    @Test
    public void testCorrectRecommendationsDisplayedInView(){
        //check the first recommended item in view is "Basic Burn" utilising
        //first user profile test data from (Test_02 Test No.1).
        onView(withRecyclerView(R.id.recommendationRecyclerview).atPosition(0))
                .check(matches(hasDescendant(withText("Basic Burn"))));
    }

    @Test
    public void testCorrectStretchingActivitiesDisplayedInView(){
        //check the first stretching activity item in view is "Yoga 101"
        onView(withRecyclerView(R.id.stretchingRecyclerview).atPosition(0))
                .check(matches(hasDescendant(withText("Yoga 101"))));
    }
    @Test
    public void testCorrectEnduranceActivitiesDisplayedInView(){
        //check the first endurance activity item in view is "Speed Circuit"
        onView(withRecyclerView(R.id.enduranceRecyclerview).atPosition(0))
                .check(matches(hasDescendant(withText("Speed Circuit"))));
    }
    @Test
    public void testCorrectStrengthActivitiesDisplayedInView(){
        //check the first strength activity item in view is "Quick Crush" utilising
        //first user test profile data from (Test_02 Test No.1).
        onView(withRecyclerView(R.id.strengthRecyclerview).atPosition(0))
                .check(matches(hasDescendant(withText("Quick Crush"))));
    }

    @After
    public void tearDown(){
        Intents.release();
    }
}