package com.example.openexchanger.controller;


import com.example.openexchanger.client.FeignGiphyClient;
import com.example.openexchanger.model.ExchangeRates;
import com.example.openexchanger.service.GiphyClientImpl;
import com.example.openexchanger.service.OpenExchangeRatesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;


@RestController
public class MainController {
    @Autowired
    private OpenExchangeRatesServiceImpl openExchangeRatesServiceImpl;
    @Autowired
    private GiphyClientImpl giphyClientImpl;

    @GetMapping("/index")
    public List<String> getCurrencies() {
        return openExchangeRatesServiceImpl.getCurrencies();
    }

    @GetMapping("/gifs/{currency}")
    public ResponseEntity<Map> getGifs(@PathVariable String currency){
        openExchangeRatesServiceImpl.refreshRates();
        String tag = openExchangeRatesServiceImpl.compareCurrencies(currency);
        return giphyClientImpl.getGif(tag);
    }
}
