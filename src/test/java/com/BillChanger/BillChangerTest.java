package com.BillChanger;

import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

public class BillChangerTest {
    private Coin coin;
    private BillChanger billChanger;

    @Before
    public void setUp() {
        coin = new Coin(12); // Initialize with 10 coins of each denomination
        billChanger = new BillChanger(coin);
    }

    @Test
    public void getChangeForDifferentAmounts() {
        double amount = 1.0;
        assertThat(billChanger.getChange(amount).toString()).isEqualTo("[0.25, 0.25, 0.25, 0.25]");

        amount = 0.5;
        assertThat(billChanger.getChange(amount).toString()).isEqualTo("[0.25, 0.25]");

        amount = 0.15;
        assertThat(billChanger.getChange(amount).toString()).isEqualTo("[0.1, 0.05]");

        amount = 0.07;
        assertThat(billChanger.getChange(amount).toString()).isEqualTo("[0.05, 0.01, 0.01]");

        amount = 0.3;
        assertThat(billChanger.getChange(amount).toString()).isEqualTo("[0.25, 0.05]");

        amount = 0.02;
        assertThat(billChanger.getChange(amount).toString()).isEqualTo("[0.01, 0.01]");

        amount = 0.0;
        assertThat(billChanger.getChange(amount).toString()).isEqualTo("[]");

        amount = 1.0;
        assertThat(billChanger.getChange(amount).toString()).isEqualTo("[0.25, 0.25, 0.25, 0.25]");

    }

    @Test
    public void notEnoughCoinsForChangeShouldReturnEmpty() {
        double amount = 5.0;
        assertThat(billChanger.getChange(amount).toString()).isEqualTo("[]");
    }
}
