package com.starling.roundup.Requests;

import com.starling.roundup.Models.Goal;

import static com.starling.roundup.APIConnection.Http.*;
import static com.starling.roundup.Utils.*;

import java.io.*;
import java.net.URL;
public class PutCreateGoal {
    /**
     * Request interface for goal creation
     * */
    public static void createGoal(Goal goal, String accountUID, String accessToken) {
        try {
            URL url = new URL( BaseUrl.getUrl() + ApiV2.getUrl() + Account.getUrl("/", accountUID) + SavingsGoals.getUrl());
            String response = CreateGoalRequest(goal, url, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
