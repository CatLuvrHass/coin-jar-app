package com.starling.roundup.DataOperations;

import org.json.JSONArray;
import org.json.JSONObject;

public class ParseAccountDetails {
    /**
     * returns: a String of accountUID
     * */
    public static String AccountUID(String accountsResponse){
        JSONObject object = new JSONObject(accountsResponse);
        JSONArray accounts = object.getJSONArray("accounts");
        JSONObject account = accounts.getJSONObject(0);
        return (String) account.get("accountUid");
    }
    /**
     * returns: a String of defaultCategory
     * */
    public static String CategoryUID(String accountsResponse){
        JSONObject object = new JSONObject(accountsResponse);
        JSONArray accounts = object.getJSONArray("accounts");
        JSONObject account = accounts.getJSONObject(0);
        return (String) account.get("defaultCategory");
    }
}
