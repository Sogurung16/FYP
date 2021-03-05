package com.example.fyp_01.user;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UserControllerTest {
    UserController controller;

    @Test
    public void userGoalSpinnerOptionsReturnsCorrectList(){
        controller = new UserController();

        List<String> expectedList = controller.userGoalSpinnerOptions();
        List<String> actualList = new ArrayList<String>();
        actualList.add("");
        actualList.add("Endurance");
        actualList.add("Strengthening");
        actualList.add("Stretching");

        Assert.assertEquals(expectedList, actualList);
    }

    @Test
    public void intensitySpinnerOptionsReturnsCorrectList(){
        controller = new UserController();

        List<String> expectedList = controller.intensitySpinnerOptions();
        List<String> actualList = new ArrayList<String>();
        actualList.add("");
        actualList.add("Easy");
        actualList.add("Moderate");
        actualList.add("Hard");

        Assert.assertEquals(expectedList, actualList);
    }

    @Test
    public void workoutGroupOptionsReturnsCorrectList(){
        controller = new UserController();

        List<String> expectedList = controller.workoutGroupSpinnerOptions();
        List<String> actualList = new ArrayList<String>();
        actualList.add("");
        actualList.add("Beginner");
        actualList.add("Intermediate");
        actualList.add("Advanced");

        Assert.assertEquals(expectedList, actualList);
    }

    @Test
    public void equipmentGroupOptionsReturnsCorrectList(){
        controller = new UserController();

        List<String> expectedList = controller.equipmentGroupSpinnerOptions();
        List<String> actualList = new ArrayList<String>();
        actualList.add("");
        actualList.add("None");
        actualList.add("Basic");
        actualList.add("Full");

        Assert.assertEquals(expectedList, actualList);
    }
}