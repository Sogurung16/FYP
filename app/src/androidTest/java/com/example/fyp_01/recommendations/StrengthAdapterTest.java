package com.example.fyp_01.recommendations;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.fyp_01.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

public class StrengthAdapterTest {
    @Rule
    public ActivityScenarioRule<Controller> activityTestRule = new ActivityScenarioRule<>(Controller.class);

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    @Before
    public void setUp(){
        Intents.init();
    }

    @Test
    public void testCorrectStrengthActivitiesDisplayedInView(){
        //swipe up to get the strength Recycler View visible == true
        onView(withRecyclerView(R.id.stretchingRecyclerview).atPosition(0)).perform(swipeUp());
        //check the strengthening activity items in view is correct
        onView(withRecyclerView(R.id.strengthRecyclerview).atPosition(0))
                .check(matches(hasDescendant(withText("Quick Crush"))));
        onView(withId(R.id.strengthRecyclerview)).perform(scrollToPosition(1));
        onView(withRecyclerView(R.id.strengthRecyclerview).atPosition(1))
                .check(matches(hasDescendant(withText("Upper Body Blast"))));
        onView(withId(R.id.strengthRecyclerview)).perform(scrollToPosition(2));
        onView(withRecyclerView(R.id.strengthRecyclerview).atPosition(2))
                .check(matches(hasDescendant(withText("Easy Drills"))));
        onView(withId(R.id.strengthRecyclerview)).perform(scrollToPosition(3));
        onView(withRecyclerView(R.id.strengthRecyclerview).atPosition(3))
                .check(matches(hasDescendant(withText("Push Pull"))));
        onView(withId(R.id.strengthRecyclerview)).perform(scrollToPosition(4));
        onView(withRecyclerView(R.id.strengthRecyclerview).atPosition(4))
                .check(matches(hasDescendant(withText("Core Strength"))));
    }

    @After
    public void tearDown(){
        Intents.release();
    }
}