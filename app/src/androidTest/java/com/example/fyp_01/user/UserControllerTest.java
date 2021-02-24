package com.example.fyp_01.user;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.fyp_01.R;
import com.example.fyp_01.recommendations.Controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.fyp_01.user.UserController.buildToastMessage;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;


@RunWith(AndroidJUnit4.class)
public class UserControllerTest {

    private String userName, goal, workoutGroup, intensity, equipmentGroup;

    @Rule
    public ActivityScenarioRule<UserController> activityTestRule = new ActivityScenarioRule<>(UserController.class);

    @Before
    public void setUp(){
        // String to be typed for User name input
        userName = "Sonam Gurung";
        goal = "Endurance";
        workoutGroup = "Beginner";
        intensity = "Easy";
        equipmentGroup = "None";

        Intents.init();
    }

    @Test
    public void checkSetStringMatchesInput(){
        // check input matches with the text typed
        onView(allOf(withId(R.id.userNameInput), withText(userName)));
    }

    @Test
    public void validateNavToRecommendationActivity_Pass(){
        //type Name field
        onView(withId(R.id.userNameInput))
                .perform(typeText(userName), ViewActions.closeSoftKeyboard());

        //select spinner options
        onView(withId(R.id.userGoalInput)).perform(click());
        onData(allOf(is(instanceOf(String.class)),
                is(goal))).perform(click());

        onView(withId(R.id.workoutGroupInput)).perform(click());
        onData(allOf(is(instanceOf(String.class)),
                is(workoutGroup))).perform(click());

        onView(withId(R.id.intensityInput)).perform(click());
        onData(allOf(is(instanceOf(String.class)),
                is(intensity))).perform(click());

        onView(withId(R.id.equipmentGroupInput)).perform(click());
        onData(allOf(is(instanceOf(String.class)),
                is(equipmentGroup))).perform(click());

        //click add button
        onView(ViewMatchers.withId(R.id.addUser))
                .perform(click());

        //check recommendations activity is launched.
        Intents.intended(hasComponent(Controller.class.getName()));
    }

    @Test
    public void testForToast_DataAdded(){
        //type Name field
        onView(withId(R.id.userNameInput))
                .perform(typeText(userName), ViewActions.closeSoftKeyboard());

        //select spinner options
        onView(withId(R.id.userGoalInput)).perform(click());
        onData(allOf(is(instanceOf(String.class)),
                is(goal))).perform(click());

        onView(withId(R.id.workoutGroupInput)).perform(click());
        onData(allOf(is(instanceOf(String.class)),
                is(workoutGroup))).perform(click());

        onView(withId(R.id.intensityInput)).perform(click());
        onData(allOf(is(instanceOf(String.class)),
                is(intensity))).perform(click());

        onView(withId(R.id.equipmentGroupInput)).perform(click());
        onData(allOf(is(instanceOf(String.class)),
                is(equipmentGroup))).perform(click());

        //click add button
        onView(ViewMatchers.withId(R.id.addUser))
                .perform(click());
        //test if toast is displayed
        onView(withText(buildToastMessage("")))
            .inRoot(new ToastMatcher())
            .check(matches(isDisplayed()));
    }

    @Test
    public void testForToast_DataNOTAddedForNoDataInput(){
        //click add button
        onView(ViewMatchers.withId(R.id.addUser))
                .perform(click());
        //test if toast is displayed
        onView(withText(buildToastMessage(" NOT")+ "\nInvalid Name"))
                .inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    @Test
    public void testForToast_DataNOTAddedForOnlyNameDataInput(){
        //type Name field
        onView(withId(R.id.userNameInput))
                .perform(typeText(userName), ViewActions.closeSoftKeyboard());
        //click add button
        onView(ViewMatchers.withId(R.id.addUser))
                .perform(click());
        //test if toast is displayed
        onView(withText(buildToastMessage(" NOT")+ "\nIncomplete Activity Preferences"))
                .inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    @Test
    public void testForToast_DataNOTAddedForOnlyPreferencesDataInputs(){
        //select spinner options
        onView(withId(R.id.userGoalInput)).perform(click());
        onData(allOf(is(instanceOf(String.class)),
                is(goal))).perform(click());

        onView(withId(R.id.workoutGroupInput)).perform(click());
        onData(allOf(is(instanceOf(String.class)),
                is(workoutGroup))).perform(click());

        onView(withId(R.id.intensityInput)).perform(click());
        onData(allOf(is(instanceOf(String.class)),
                is(intensity))).perform(click());

        onView(withId(R.id.equipmentGroupInput)).perform(click());
        onData(allOf(is(instanceOf(String.class)),
                is(equipmentGroup))).perform(click());
        //click add button
        onView(ViewMatchers.withId(R.id.addUser))
                .perform(click());
        //test if toast is displayed
        onView(withText(buildToastMessage(" NOT")+ "\nInvalid Name"))
                .inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    @After
    public void tearDown(){
        Intents.release();
    }
}