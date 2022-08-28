package com.starling.roundup.DataOperations;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetSavingsGoal {
    /**
     * returns: a list of goals
     * */
    public static ArrayList<String> goals(String currentSavingGoals) {
        JSONObject object = new JSONObject(currentSavingGoals);
        JSONArray array = object.getJSONArray("savingsGoalList");
        ArrayList<String> goals = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            String goal = String.valueOf(array.getJSONObject(i).get("name"));
            goals.add(goal);
        }
        return goals;
    }
    /**
     * returns: a String UID of a goal.
     * */
    public static String UID(String currentSavingGoals, int index){
        JSONObject object = new JSONObject(currentSavingGoals);
        JSONArray savingsGoalList = object.getJSONArray("savingsGoalList");
        JSONObject savingsGoal = savingsGoalList.getJSONObject(index);
        return (String) savingsGoal.get("savingsGoalUid");
    }

    /**
     * returns: the sum of current saving in a particular goal + roundup value.
     * */
    public static int totalToTransfer(String currentSavingGoals, int totalPenny) {
        int totalUpdate = 0;

        JSONObject object = new JSONObject(currentSavingGoals);
        JSONArray feedItems = object.getJSONArray("savingsGoalList");

        for (int i = 0; i < feedItems.length(); i++) {
            JSONObject amount = (JSONObject) feedItems.getJSONObject(i).get("totalSaved");
            Integer minorUnits = (Integer) amount.get("minorUnits");
            totalUpdate = minorUnits + totalPenny;
        }
        return totalUpdate;
    }
}
