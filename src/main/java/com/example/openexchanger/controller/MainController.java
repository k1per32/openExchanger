package com.example.openexchanger.controller;


import com.example.openexchanger.model.ExchangeRates;
import com.example.openexchanger.service.OpenExchangeRatesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;


@RestController
public class MainController {
    @Autowired
    private OpenExchangeRatesServiceImpl openExchangeRatesServiceImpl;

    @GetMapping("/index")
    public List<String> getCurrencies() {
        return openExchangeRatesServiceImpl.getCurrencies();
    }

    @GetMapping("/getHistorical")
    public ExchangeRates getHistoricalCurrencies(){
        return openExchangeRatesServiceImpl.compareCurrencies();
    }
}
