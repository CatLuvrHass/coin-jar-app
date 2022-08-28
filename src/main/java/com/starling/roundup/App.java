package com.starling.roundup;

import com.starling.roundup.DataOperations.*;
import com.starling.roundup.Models.Goal;
import com.starling.roundup.Requests.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class App{
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

	public static void main(String[] args) throws Exception {
		// ask user for token.
		Scanner input = new Scanner(System.in);
		System.out.println("Enter your Access token please: ");
		String accessToken = input.next();

		// parse the accountUUID and CategoryUUID
		String accountUID = ParseAccountDetails.AccountUID(GetAccounts.accounts(accessToken));
		String categoryUID = ParseAccountDetails.CategoryUID(GetAccounts.accounts(accessToken));

		// today's date and week past.
		Date date = new Date();
		String toDate = dateFormat.format(date);
		Date oneWeekAgo = new Date(System.currentTimeMillis() - 7L*24*3600*1000);
		String fromDate = dateFormat.format(oneWeekAgo);

		// get transactions for the past week.
		String weekTransactions = GetWeeklyTransactions.feed(accountUID, categoryUID, fromDate, toDate, accessToken);
		String savingsFeed = GetSavings.feed(accountUID, accessToken);

		// create a list of current goals.
		ArrayList<String> goalsList = GetSavingsGoal.goals(savingsFeed);

		// confirm or create new
		confirmOrNew(goalsList, savingsFeed, weekTransactions, input, accountUID, accessToken);
	}
	/**
	 * Confirm choice of goal to transfer the roundup to.
	 * */
	public static void confirmOrNew(ArrayList<String> goalsList, String savingsFeed, String weekTransactions, Scanner input, String accountUID, String accessToken) throws Exception {
		String decision;
		if (goalsList.size() > 0){
			goalsList = GetSavingsGoal.goals(GetSavings.feed(accountUID, accessToken));
			balance(accountUID, accessToken);
			System.out.println("List of current goals pick one: " + goalsList);
			int totalToTransfer = GetSavingsGoal.totalToTransfer(savingsFeed, RoundUp.parse(weekTransactions));
			System.out.println("choose your goal, 0 indexed: ie. 0 or 1 or 2 etc. ");
			System.out.println("total to roundup for this week: " + totalToTransfer);
			int index = Integer.parseInt(input.next());
			System.out.println("Confirm roundup or create new goal?: confirm or new, type exit to cancel.");
			decision = input.next();
			switch (decision){
				case "confirm":
					String newGoalUid = GetSavingsGoal.UID(GetSavings.feed(accountUID, accessToken), index);
					PutUpdateSavings.updateSavingsGoal(totalToTransfer, newGoalUid, accountUID, accessToken);
					GetSavings.feed(accountUID, accessToken);
					ArrayList<Integer> finalSavings = GetSavingsGoal.savingsPercentage(GetSavings.feed(accountUID, accessToken));
					ArrayList<String> finalGoalList = GetSavingsGoal.goals(GetSavings.feed(accountUID, accessToken));
					for(int i = 0; i < finalGoalList.size(); i++){
						System.out.println("The current savings percentage for: " + finalGoalList.get(i) + " is: " + finalSavings.get(i) + "%");
					}
					System.exit(0);
				case "new":
					System.out.println("What is the name of your goal? eg. paris");
					String name = input.next();
					System.out.println("currency of your goal? only GBP, gbp");
					String currency = input.next();
					System.out.println("amount to set for your goal? 1500 pounds");
					String amount = input.next();
					ArrayList<String> newList = makeGoal(amount, name, currency, accountUID, accessToken);
					confirmOrNew(newList, savingsFeed, weekTransactions, input, accountUID, accessToken);
				case "exit":
					System.exit(0);
			}
		}else{
			System.out.println("Would you like to create a savings goal?: yes or no , type exit to cancel.");
			decision = input.next();
			switch (decision){
				case "yes":
					System.out.println("What is the name of your goal? eg. paris");
					String name = input.next();
					System.out.println("currency of your goal? only GBP");
					String currency = input.next();
					System.out.println("amount to set for your goal? 1500 pounds");
					String amount = input.next();
					ArrayList<String> newList = makeGoal(amount, name, currency, accountUID, accessToken);
					confirmOrNew(newList, savingsFeed, weekTransactions, input, accountUID, accessToken);
				case "no":
					confirmOrNew(goalsList, savingsFeed, weekTransactions, input, accountUID, accessToken);
				case "exit":
					System.exit(0);
			}
		}
	}
	/**
	 * Update the goal list.
	 * */
	public static ArrayList<String> makeGoal(String amount, String name, String currency, String accountUID, String accessToken){
		String pennies;
		StringBuilder sb;
		if(!amount.contains(".")){
			sb = new StringBuilder(amount);
			sb.setLength(amount.length() + 2);
			pennies = sb.toString().replaceAll("[^0-9]", "0");
			System.out.println(pennies);
			Goal goal = new Goal(name, currency.toUpperCase(), Integer.parseInt(pennies));
			PutCreateGoal.createGoal(goal, accountUID, accessToken);
		}else{
			sb = new StringBuilder(amount);
			pennies = sb.toString().replaceAll("\\.", "");
			System.out.println(pennies);
			Goal goal = new Goal(name, currency.toUpperCase(), Integer.parseInt(pennies));
			PutCreateGoal.createGoal(goal, accountUID, accessToken);
		}
		return GetSavingsGoal.goals(GetSavings.feed(accountUID, accessToken));
	}

	public static void balance(String accountUID, String accessToken){
		String currentBalance = DisplayBalance.effectiveBalance(GetBalance.balanceResponse(accountUID, accessToken));
		System.out.println("current balance: " + currentBalance);
	}

}