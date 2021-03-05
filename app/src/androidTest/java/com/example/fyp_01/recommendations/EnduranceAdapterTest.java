package com.example.fyp_01.recommendations;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.fyp_01.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

public class EnduranceAdapterTest {

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
    public void testCorrectEnduranceActivitiesDisplayedInView(){
        //check the endurance activity items in view is correct
        onView(withRecyclerView(R.id.enduranceRecyclerview).atPosition(0))
                .check(matches(hasDescendant(withText("Speed Circuit"))));
        onView(withId(R.id.enduranceRecyclerview)).perform(scrollToPosition(1));
        onView(withRecyclerView(R.id.enduranceRecyclerview).atPosition(1))
                .check(matches(hasDescendant(withText("Runner up"))));
        onView(withId(R.id.enduranceRecyclerview)).perform(scrollToPosition(2));
        onView(withRecyclerView(R.id.enduranceRecyclerview).atPosition(2))
                .check(matches(hasDescendant(withText("Basic Burn"))));
        onView(withId(R.id.enduranceRecyclerview)).perform(scrollToPosition(3));
        onView(withRecyclerView(R.id.enduranceRecyclerview).atPosition(3))
                .check(matches(hasDescendant(withText("Explosive Ignition"))));
        onView(withId(R.id.enduranceRecyclerview)).perform(scrollToPosition(4));
        onView(withRecyclerView(R.id.enduranceRecyclerview).atPosition(4))
                .check(matches(hasDescendant(withText("Hit Challenge"))));
    }

    @After
    public void tearDown(){
        Intents.release();
    }
}