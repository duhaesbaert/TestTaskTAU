package com.BillChanger;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class BillChanger {
    private Coin coin;

    public BillChanger(Coin coin) {
        this.coin = coin;
    }

    public List<Double> getChange(double amount) {
        BigDecimal remainingAmount = BigDecimal.valueOf(amount);
        List<Double> change = new ArrayList<>();
        List<Double> sortedCoins = new ArrayList<>(coin.getCoinDenominations());
        Collections.sort(sortedCoins, Collections.reverseOrder());

        for (double coinValue : sortedCoins) {
            BigDecimal coinBigDecimal = BigDecimal.valueOf(coinValue);
            while (remainingAmount.compareTo(coinBigDecimal) >= 0 && coin.getAmount(coinValue) > 0) {
                change.add(coinValue);
                remainingAmount = remainingAmount.subtract(coinBigDecimal);
                coin.withdrawCoins(coinValue,1);
            }
        }

        if (remainingAmount.compareTo(BigDecimal.ZERO) == 0) {
            return change;
        } else {
            System.out.println("Not enough coins available to make the exact change.");
            return new ArrayList<>();
        }
    }

    public void MakeDeposit(Double typeCoin, int amount) {
        coin.depositCoins(typeCoin,amount);
    }
}
