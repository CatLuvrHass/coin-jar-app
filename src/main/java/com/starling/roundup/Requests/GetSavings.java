package com.starling.roundup.Requests;

import java.io.*;
import java.net.URL;

import static com.starling.roundup.APIConnection.Http.*;
import static com.starling.roundup.Utils.*;

public class GetSavings {
    /**
     * Request interface for account savings feed
     * */
    public static String feed(String accountUID, String accessToken) {
        //get saving goals
        String currentSavingGoals = null;
        try {
            URL url = new URL(BaseUrl.getUrl() + ApiV2.getUrl() + Account.getUrl("/", accountUID) + SavingsGoals.getUrl());
            currentSavingGoals = GetRequest(url, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currentSavingGoals;
    }
}

