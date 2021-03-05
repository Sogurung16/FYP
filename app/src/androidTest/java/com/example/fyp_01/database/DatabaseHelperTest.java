package com.example.fyp_01.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.fyp_01.R;
import com.example.fyp_01.recommendations.Model;
import com.example.fyp_01.user.UserController;
import com.example.fyp_01.user.UserModel;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
public class DatabaseHelperTest {
    private UserModel user;
    private Model model;
    private ArrayList<Model> models;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;
    private Context context;

    private String name = "Sonam Gurung", goal = "Endurance",workoutLvl = "Beginner", intensity = "Easy", equipmentGroup = "None";
    private String activitiesName = "Yoga 101", activitiesType = "Stretching", activitiesWorkoutLvl = "Beginner", activitiesIntensityLvl = "Easy", activitiesEquipmentGroup = "None";
    private Bitmap imgToStore;

    @Rule
    public ActivityScenarioRule<UserController> activityTestRule = new ActivityScenarioRule<>(UserController.class);

    @Before
    public void setUp(){
        Intents.init();
    }

    @Test
    public void addUserDataTest_ReturnsTrue(){
        //Context of the app under test
        context = ApplicationProvider.getApplicationContext();
        //Creating a new databaseHelper
        databaseHelper = new DatabaseHelper(context);

        user = new UserModel();
        user.setUserNameData(name);
        user.setUserGoalData(goal);
        user.setWorkoutGroupData(workoutLvl);
        user.setIntensityData(intensity);
        user.setEquipmentGroupData(equipmentGroup);

        //Drop old database, upgrade new database and allow writable access
        db = databaseHelper.getWritableDatabase();
        databaseHelper.onUpgrade(db, 0, 1);
        // Store if user table is added into the database in result variable
        Boolean result = databaseHelper.addUserData(user);

        Assert.assertTrue(result);
    }

    @Test // return false if any one of the user properties are empty
    public void addUserDataWithMissingAttributeTest_ReturnsFalse(){
        //Context of the app under test
        context = ApplicationProvider.getApplicationContext();
        //Creating a new databaseHelper
        databaseHelper = new DatabaseHelper(context);

        // missing equipmentGroup data, method should return false
        user = new UserModel();
        user.setUserNameData(name);
        user.setUserGoalData(goal);
        user.setWorkoutGroupData(workoutLvl);
        user.setIntensityData(intensity);


        //Drop old database, upgrade new database and allow writable access
        db = databaseHelper.getWritableDatabase();
        databaseHelper.onUpgrade(db, 0, 1);
        // Store if user table is added into the database in result variable
        Boolean result = databaseHelper.addUserData(user);

        Assert.assertFalse(result);
    }

    @Test
    public void userDataAddedToDatabaseIsSameAsInput(){
        // Initialising variables under test
        String actualName = null, actualGoal = null, actualWorkoutLvl = null, actualIntensity = null, actualEquipmentGroup = null;

        //Context of the app under test
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        //Creating a new database
        databaseHelper = new DatabaseHelper(context);

        //type Name field
        onView(withId(R.id.userNameInput))
                .perform(typeText(name), ViewActions.closeSoftKeyboard());

        //select spinner options
        onView(withId(R.id.userGoalInput)).perform(click());
        onData(allOf(is(instanceOf(String.class)),
                is(goal))).perform(click());

        onView(withId(R.id.workoutGroupInput)).perform(click());
        onData(allOf(is(instanceOf(String.class)),
                is(workoutLvl))).perform(click());

        onView(withId(R.id.intensityInput)).perform(click());
        onData(allOf(is(instanceOf(String.class)),
                is(intensity))).perform(click());

        onView(withId(R.id.equipmentGroupInput)).perform(click());
        onData(allOf(is(instanceOf(String.class)),
                is(equipmentGroup))).perform(click());

        //click add button
        onView(ViewMatchers.withId(R.id.addUser))
                .perform(click());

        //Reading all the data on the database
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from users_table", null);
        // Reading and setting each variable under test
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                //int id = cursor.getInt(0);
                actualName = cursor.getString(1);
                actualWorkoutLvl = cursor.getString(2);
                actualGoal = cursor.getString(3);
                actualIntensity = cursor.getString(4);
                actualEquipmentGroup = cursor.getString(5);
            }
        }

        // Comparing expected results with actual results set
        Assert.assertEquals(name, actualName);
        Assert.assertEquals(goal, actualGoal);
        Assert.assertEquals(workoutLvl, actualWorkoutLvl);
        Assert.assertEquals(intensity, actualIntensity);
        Assert.assertEquals(equipmentGroup, actualEquipmentGroup);
    }

    @Test
    public void addActivityData_ReturnsTrue(){
        //Context of the app under test
        context = ApplicationProvider.getApplicationContext();
        //Creating a new databaseHelper
        databaseHelper = new DatabaseHelper(context);

        imgToStore = BitmapFactory.decodeResource(context.getResources(), R.drawable.stretching);

        model = new Model();
        model.setActivitiesName(activitiesName);
        model.setActivitiesType(activitiesType);
        model.setWorkoutLvlData(activitiesWorkoutLvl);
        model.setIntensityLvlData(activitiesIntensityLvl);
        model.setEquipmentGroupData(activitiesEquipmentGroup);
        model.setActivitiesImage(imgToStore);

        //Drop old database, upgrade new database and allow writable access
        db = databaseHelper.getWritableDatabase();
        databaseHelper.onUpgrade(db, 0, 1);
        // Store if activities table is added into the database in boolean variable result
        Boolean result = databaseHelper.addActivityData(model);

        Assert.assertTrue(result);
    }

    @Test
    public void addActivityDataWithMissingAttribute_ReturnsFalse(){
        //Context of the app under test
        context = ApplicationProvider.getApplicationContext();
        //Creating a new databaseHelper
        databaseHelper = new DatabaseHelper(context);

        imgToStore = BitmapFactory.decodeResource(context.getResources(), R.drawable.stretching);

        //Missing activities equipment data, test should return false
        model = new Model();
        model.setActivitiesName(activitiesName);
        model.setActivitiesType(activitiesType);
        model.setWorkoutLvlData(activitiesWorkoutLvl);
        model.setIntensityLvlData(activitiesIntensityLvl);
        model.setActivitiesImage(imgToStore);

        //Drop old database, upgrade new database and allow writable access
        db = databaseHelper.getWritableDatabase();
        databaseHelper.onUpgrade(db, 0, 1);
        // Store if activities table is added into the database in boolean variable result
        Boolean result = databaseHelper.addActivityData(model);

        Assert.assertFalse(result);
    }

    @Test
    public void testGetActivityData_ReturnsTrue(){
        //Context of the app under test
        context = ApplicationProvider.getApplicationContext();
        //Creating a new databaseHelper
        databaseHelper = new DatabaseHelper(context);

        imgToStore = BitmapFactory.decodeResource(context.getResources(), R.drawable.stretching);

        model = new Model();
        model.setActivitiesName(activitiesName);
        model.setActivitiesType(activitiesType);
        model.setWorkoutLvlData(activitiesWorkoutLvl);
        model.setIntensityLvlData(activitiesIntensityLvl);
        model.setEquipmentGroupData(activitiesEquipmentGroup);
        model.setActivitiesImage(imgToStore);

        //Drop old database, upgrade new database and allow writable access
        db = databaseHelper.getWritableDatabase();
        databaseHelper.onUpgrade(db, 0, 1);
        // add activities data into database
        databaseHelper.addActivityData(model);

        // function testing
        models = databaseHelper.getActivitiesData();
        model = models.get(0);

        Assert.assertEquals(model.getActivitiesName(), "Yoga 101");
    }

    @After
    public void tearDown(){
        Intents.release();
    }
}