package com.example.openexchanger;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OpenExchangerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenExchangerApplication.class, args);

    }
}
