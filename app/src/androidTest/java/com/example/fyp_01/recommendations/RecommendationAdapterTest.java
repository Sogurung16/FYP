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
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToHolder;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;;

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
        //check the recommended items in view is correct utilising
        //first user profile test data from (Test_02 Test No.1).
        onView(withRecyclerView(R.id.recommendationRecyclerview).atPosition(0))
                .check(matches(hasDescendant(withText("Basic Burn"))));
        onView(withId(R.id.recommendationRecyclerview)).perform(scrollToPosition(1));
        onView(withRecyclerView(R.id.recommendationRecyclerview).atPosition(1))
                .check(matches(hasDescendant(withText("Yoga 101"))));
        onView(withId(R.id.recommendationRecyclerview)).perform(scrollToPosition(2));
        onView(withRecyclerView(R.id.recommendationRecyclerview).atPosition(2))
                .check(matches(hasDescendant(withText("Legs Warmup"))));
        onView(withId(R.id.recommendationRecyclerview)).perform(scrollToPosition(3));
        onView(withRecyclerView(R.id.recommendationRecyclerview).atPosition(3))
                .check(matches(hasDescendant(withText("Legs Cooldown"))));
        onView(withId(R.id.recommendationRecyclerview)).perform(scrollToPosition(4));
        onView(withRecyclerView(R.id.recommendationRecyclerview).atPosition(4))
                .check(matches(hasDescendant(withText("Recovery Mobility"))));
    }

    @After
    public void tearDown(){
        Intents.release();
    }
}