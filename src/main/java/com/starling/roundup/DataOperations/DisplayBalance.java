package com.starling.roundup.DataOperations;

import org.json.JSONObject;


public class DisplayBalance {
    /**
     * display the effective balance, which is the displayed amount to client.
     * returns: a String value of amount in pounds
     * */
    public static String effectiveBalance(String balanceResponse) {
        JSONObject object = new JSONObject(balanceResponse);
        JSONObject effectiveBalance = object.getJSONObject("effectiveBalance");
        int amount = effectiveBalance.getInt("minorUnits");
        float pounds = (float) (amount/100.00);
        float balance = Float.parseFloat(String.format("%.2f", pounds));
        return String.valueOf(balance);
    }
}
