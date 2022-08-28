package com.starling.roundup;

import java.util.Date;

public enum Utils {
    /**
     * Constants and constructor for variables such as accountUUID, categoryUUID etc.
     * */

    // enum constants calling the enum constructors
    BaseUrl("https://api-sandbox.starlingbank.com"),
    ApiV2("/api/v2"),
    Feed("/feed"),
    Accounts("/accounts"),
    Balance("/balance"),
    Account("/account"),
    SavingsGoals("/savings-goals"),
    AddMoney("/add-money/"),
    Category("/category"),
    TransactionsBetween("/transactions-between?"),
    MinTransactionTimestamp("minTransactionTimestamp="),
    MaxTransactionTimestamp("&maxTransactionTimestamp=");

    private final String url;

    // private enum constructor
    private Utils(String url) {
        this.url = url;
    }
    public String getUrl(String slash, String var) {
        return url + slash + var;
    }
    public String getUrl() {
        return url;
    }
}
