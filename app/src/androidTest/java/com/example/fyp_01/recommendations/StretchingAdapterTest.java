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

public class StretchingAdapterTest {
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
    public void testCorrectStretchingActivitiesDisplayedInView(){
        //check the stretching activity items in view is correct
        onView(withRecyclerView(R.id.stretchingRecyclerview).atPosition(0))
                .check(matches(hasDescendant(withText("Yoga 101"))));
        onView(withId(R.id.stretchingRecyclerview)).perform(scrollToPosition(1));
        onView(withRecyclerView(R.id.stretchingRecyclerview).atPosition(1))
                .check(matches(hasDescendant(withText("Yoga Core"))));
        onView(withId(R.id.stretchingRecyclerview)).perform(scrollToPosition(2));
        onView(withRecyclerView(R.id.stretchingRecyclerview).atPosition(2))
                .check(matches(hasDescendant(withText("Legs Warmup"))));
        onView(withId(R.id.stretchingRecyclerview)).perform(scrollToPosition(3));
        onView(withRecyclerView(R.id.stretchingRecyclerview).atPosition(3))
                .check(matches(hasDescendant(withText("Legs Cooldown"))));
        onView(withId(R.id.stretchingRecyclerview)).perform(scrollToPosition(4));
        onView(withRecyclerView(R.id.stretchingRecyclerview).atPosition(4))
                .check(matches(hasDescendant(withText("Recovery Mobility"))));
    }

    @After
    public void tearDown(){
        Intents.release();
    }
}