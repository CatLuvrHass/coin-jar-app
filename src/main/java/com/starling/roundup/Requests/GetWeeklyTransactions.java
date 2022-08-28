package com.starling.roundup.Requests;

import static com.starling.roundup.APIConnection.Http.*;
import static com.starling.roundup.Utils.*;
//import static com.starling.roundup.Constants.*;

import java.io.IOException;
import java.net.URL;
import java.util.Date;

public class GetWeeklyTransactions {
	/**
	 * Request interface for account's transactions of a given week
	 * */
    public static String feed(String accountUID, String categoryUID, String fromDate, String toDate, String accessToken){
		String transactionsOfWeek = null;
		try {
			URL url = new URL(BaseUrl.getUrl() + ApiV2.getUrl() + Feed.getUrl() + Account.getUrl("/", accountUID) + Category.getUrl("/", categoryUID) + TransactionsBetween.getUrl() + MinTransactionTimestamp.getUrl("", fromDate) + MaxTransactionTimestamp.getUrl("", toDate));
			transactionsOfWeek = GetRequest(url, accessToken);
		} catch (IOException e) {
			e.printStackTrace();
		}
        return transactionsOfWeek;
    }
}
