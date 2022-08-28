package com.starling.roundup.Requests;

import static com.starling.roundup.APIConnection.Http.GetRequest;
import static com.starling.roundup.Utils.*;
import java.io.IOException;
import java.net.URL;


public class GetAccounts {
    /**
     * Request interface for general accounts
     * */
    public static String accounts(String accessToken){
        String accountsResponse = null;
        try {
            URL url = new URL(BaseUrl.getUrl() + ApiV2.getUrl() + Accounts.getUrl());
            accountsResponse = GetRequest(url, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accountsResponse;
    }
}
