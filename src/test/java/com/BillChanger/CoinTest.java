package com.BillChanger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoinTest {

    private Coin coin;

    @BeforeEach
    public void setUp() {
        coin = new Coin(10);
    }

    @Test
    public void testInitializationAmountForCoins() {
        assertEquals(10, coin.getAmount(0.01));
        assertEquals(10, coin.getAmount(0.05));
        assertEquals(10, coin.getAmount(0.10));
        assertEquals(10, coin.getAmount(0.25));
        assertEquals(0, coin.getAmount(0.5));
    }

    @Test
    public void testDepositCoins() {
        coin.depositCoins(0.01, 5);
        coin.depositCoins(0.05, 5);
        coin.depositCoins(0.1, 5);

        assertEquals(15, coin.getAmount(0.01));
        assertEquals(15, coin.getAmount(0.05));
        assertEquals(15, coin.getAmount(0.10));
    }

    @Test
    public void testWithdrawCoins() {
        coin.withdrawCoins(0.10, 6);
        coin.withdrawCoins(0.01, 1);
        coin.withdrawCoins(0.05, 5);
        coin.withdrawCoins(0.25, 2);

        assertEquals(4, coin.getAmount(0.10));
        assertEquals(9, coin.getAmount(0.01));
        assertEquals(5, coin.getAmount(0.05));
        assertEquals(8, coin.getAmount(0.25));
    }

    @Test
    public void testWithdrawCoinsNotEnoughAvailable() {
        coin.withdrawCoins(0.01, 15);

        // confirms no coin was withdrawn
        assertEquals(10, coin.getAmount(0.01));
    }

    @Test
    public void testGetCoinDenominations() {
        assertEquals(4, coin.getCoinDenominations().size());
    }
}
