package com.BillChanger;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BillChangerControllerTest {
    private static String API_ENDPOINT ="/api/v1/change";

    @Autowired
    private BillChanger billChanger;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getChange() throws Exception {
        depositCoins(0.25, 10);

        String amount = "1.0";
        mockMvc.perform(get(API_ENDPOINT + "?amount=" + amount))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"change\":[0.25,0.25,0.25,0.25],\"message\":\"\"}"));
    }
    void depositCoins(double coin, int amount) {
        billChanger.MakeDeposit(coin, amount);
    }

}
