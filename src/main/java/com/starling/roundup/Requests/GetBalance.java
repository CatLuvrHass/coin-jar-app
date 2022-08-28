package com.starling.roundup.Requests;

import static com.starling.roundup.APIConnection.Http.GetRequest;
import static com.starling.roundup.Utils.*;
import java.io.IOException;
import java.net.URL;


public class GetBalance {
    /**
     * Request interface for general balance of account
     * */
    public static String balanceResponse(String accountUID, String accessToken){
        String balanceResponse = null;
        try {
            URL url = new URL(BaseUrl.getUrl() + ApiV2.getUrl() + Accounts.getUrl("/", accountUID) + Balance.getUrl());
            balanceResponse = GetRequest(url, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return balanceResponse;
    }
}
