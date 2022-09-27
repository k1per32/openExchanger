package com.example.openexchanger.client;


import com.example.openexchanger.model.ExchangeRates;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@FeignClient(name = "OpenExchanger", url = "https://openexchangerates.org/api")
public interface FeignOpenExchangeRatesClient {
    @GetMapping("/latest.json")
    ExchangeRates getLatestRates(@RequestParam("app_id") String appId);

    @GetMapping("/historical/{date}.json")
    ExchangeRates getHistoricalRates(
            @RequestParam("app_id") String appId,
            @PathVariable String date);

}
