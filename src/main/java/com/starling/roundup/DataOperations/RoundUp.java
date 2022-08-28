package com.starling.roundup.DataOperations;

import org.json.*;
public class RoundUp {

    /**
     * returns: total sum of transactions for a given week.
     * */
    public static int parse(String transactionsOfWeek){
        int total = 0;

        JSONObject jsonObject = new JSONObject(transactionsOfWeek);
        JSONArray feedItems = jsonObject.getJSONArray("feedItems");
        for (int i = 0; i < feedItems.length(); i++) {
            JSONObject amount = (JSONObject) feedItems.getJSONObject(i).get("amount");
            Integer minorUnits = (Integer) amount.get("minorUnits");
            if (minorUnits % 100 > 0){
                total += 100 - (minorUnits % 100);
            }
        }
        return total;
    }
}
