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

    // getChange returns a list of coins for the amount that has been requested change.
    public List<Double> getChange(double amount, boolean mostAmountOfCoins) {
        BigDecimal remainingAmount = BigDecimal.valueOf(amount);
        List<Double> change = new ArrayList<>();
        List<Double> sortedCoins = new ArrayList<>(coin.getCoinDenominations());
        Collections.sort(sortedCoins, Collections.reverseOrder());

        HashMap<Double, Integer> coinsToRefund = new HashMap<>();

        for (double coinValue : sortedCoins) {
            BigDecimal coinBigDecimal = BigDecimal.valueOf(coinValue);
            while (remainingAmount.compareTo(coinBigDecimal) >= 0 && coin.getAmount(coinValue) > 0) {
                change.add(coinValue);
                remainingAmount = remainingAmount.subtract(coinBigDecimal);
                coin.withdrawCoins(coinValue,1);
                coinsToRefund.put(coinValue, 1);
            }
        }

        if (remainingAmount.compareTo(BigDecimal.ZERO) == 0) {
            return change;
        } else {
            refundCoins(change);
            return new ArrayList<>();
        }
    }

    // MakeDeposit allows a new amount of coins to be added to the avialable coins for change
    public void MakeDeposit(Double typeCoin, int amount) {
        coin.depositCoins(typeCoin,amount);
    }

    // refundCoins is used to refund the coins that have been removed during the counting process for withdraw.
    // I would rather have done this in the reverse order, counting the ones to remove and if that is enough to then remove
    // the coins, instead of refunding, but due to time constrains I have implemented this way to avoid breaking my current routine.
    private void refundCoins(List<Double> change) {
        for (double coinChange : change) {
            coin.depositCoins(coinChange, 1);
        }
    }
}
