package com.BillChanger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public ResponseEntity<BillChangerResponse> getChange(@RequestParam String amount) {
        Double doubleAmount;

        // This might be incorrect for java, but in go we do something like this. Sorry if it is wrong
        try {
            doubleAmount = Double.parseDouble(amount);
        } catch (NumberFormatException e) {
            String message = "Amount parameter invalid. Please provide a valid amount.";
            return ResponseEntity.badRequest().body(new BillChangerResponse(new ArrayList<>(), message));
        }

        List<Double> change = billChanger.getChange(doubleAmount, false);

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
