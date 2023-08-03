package com.BillChanger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/change")
public class BillChangerController {
    BillChanger billChanger;
    private final Coin coin;

    @Autowired
    public BillChangerController(Coin coin, BillChanger billChanger) {
        this.coin = coin;
        this.billChanger = billChanger;
    }

    @GetMapping
    public List<Double> getChange(@RequestParam double amount) {
        return billChanger.getChange(amount);
    }

    @PostMapping
    public void makeDeposit(@RequestBody double coin, int amount) {
        billChanger.MakeDeposit(coin, amount);
    }
}
