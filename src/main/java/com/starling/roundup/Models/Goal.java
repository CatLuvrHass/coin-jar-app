package com.starling.roundup.Models;

public class Goal {

    /**
     *  a model for json request.
     *  for goal creations.
     * */
    private String name;
    private String currency;
    private int amount;
    public Goal(String name, String currency, int amount) {
        this.name = name;
        this.currency = currency;
        this.amount = amount;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrency(String currency){
        this.currency = currency;
    }
    public String getCurrency(){
        return currency;
    }
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}