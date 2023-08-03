package com.BillChanger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan("com.BillChanger")
@Import(AppConfig.class)
public class SpringBootBillChangerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootBillChangerApplication.class, args);
    }
}
