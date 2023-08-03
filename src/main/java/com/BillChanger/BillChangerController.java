package com.BillChanger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<BillChangerResponse> getChange(@RequestParam double amount) {
        List<Double> change = billChanger.getChange(amount, false);

        String message = "";
        org.springframework.http.HttpStatus status = HttpStatus.OK;

        if (change.isEmpty()) {
            message = "Not enough coins to make change.";
            status = HttpStatus.BAD_REQUEST;
        }

        return ResponseEntity.status(status)
                .body(new BillChangerResponse(change, message));
    }

    @PostMapping
    public void makeDeposit(@RequestBody double coin, int amount) {
        billChanger.MakeDeposit(coin, amount);
    }
}
