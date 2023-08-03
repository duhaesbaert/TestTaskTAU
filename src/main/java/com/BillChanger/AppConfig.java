package com.BillChanger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Coin coin() {
        Integer coinAmount = 100;
        return new Coin(coinAmount);
    }
}

