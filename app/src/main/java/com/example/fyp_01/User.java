package com.example.fyp_01;

public class User{

    private UserData userData;

    //Later on there will be an add user page. This class will add user to database
    public User (UserData userData){
        this.userData = userData;
    }

    public void setUserName(String userName) {
        userData.setUserNameData(userName);
    }

    public void setUserWeight(int userWeight) {
        userData.setUserWeightData(userWeight);
    }

    public void setUserGoal(int userGoal) {
        userData.setUserGoalData(userGoal);
    }

    public String getUserName() {
        return userData.getUserNameData();
    }

    public int getUserWeight() {
        return userData.getUserWeightData();
    }

    public int getUserGoal() {
        return userData.getUserGoalData();
    }

   /* public void addUser(User user){
       DatabaseHelper databaseHelper = new DatabaseHelper(null);
       databaseHelper.addUserData(user);
    }*/
}
