package com.BillChanger;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BillChangerResponse {
    @JsonProperty("change")
    private List<Double> change;

    @JsonProperty("message")
    private String message;

    public BillChangerResponse(List<Double> change, String message) {
        this.change = change;
        this.message = message;
    }

}
