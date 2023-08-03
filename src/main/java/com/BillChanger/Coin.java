package com.BillChanger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Coin {

    private HashMap<Double, Integer> availableCoins;

    public Coin(Integer amount){
        availableCoins = new HashMap<>();
        InitializeCoins(amount);
    }

    // InitializeCoins adds to the hashmap the determined amount of coins.
    private void InitializeCoins(Integer amount) {
        Double[] coins = {0.01, 0.05, 0.10, 0.25};

        for (Double coin : coins) {
            availableCoins.put(coin, amount);
        }
    }

    // getAmount returns teh amount of coins available for the specified coin
    public int getAmount(Double coin) {
        Integer amount = availableCoins.get(coin);
        return amount != null ? amount : 0;
    }

    // updateAmount replaces the amount of coins for a specified coin
    private void updateAmount(Double coin, int newAmount) {
        availableCoins.put(coin, newAmount);
    }

    // depositCoins adds to the amount of an already existing coin
    public void depositCoins(Double coin, int depositAmount) {
        int currentAmount = getAmount(coin);
        updateAmount(coin, currentAmount + depositAmount);
    }

    // withdrawCoins deducts from the current amount of coins the withdrawn amount
    public void withdrawCoins(Double coin, int withdrawAmount) {
        int currentAmount = getAmount(coin);
        int newAmount = currentAmount - withdrawAmount;
        if (newAmount < 0) {
            System.out.println("Not enough " + coin + " coins available.");
        } else {
            updateAmount(coin, newAmount);
        }
    }

    // getCoinDenominations provides a list of coins available
    public List<Double> getCoinDenominations() {
        return new ArrayList<>(availableCoins.keySet());
    }
}