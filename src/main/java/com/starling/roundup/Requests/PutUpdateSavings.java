package com.starling.roundup.Requests;



import java.io.IOException;
import java.net.URL;
import java.util.UUID;

import static com.starling.roundup.APIConnection.Http.*;
import static com.starling.roundup.Utils.*;

// Updates the saving goal
public class PutUpdateSavings {
    /**
     * Request interface for updating the goal
     * */
    public static void updateSavingsGoal(int totalUpdate, String savingGoalUid, String accountUID, String accessToken) throws Exception {
        try {
            URL url = new URL(BaseUrl.getUrl() + ApiV2.getUrl() + Account.getUrl("/", accountUID) + SavingsGoals.getUrl("/","") + UUID.fromString(savingGoalUid) + AddMoney.getUrl() + UUID.randomUUID());
            PutGoalRequest(totalUpdate, url, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}