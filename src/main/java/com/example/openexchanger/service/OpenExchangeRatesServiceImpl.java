package com.example.openexchanger.service;

import com.example.openexchanger.client.FeignOpenExchangeRatesClient;
import com.example.openexchanger.model.ExchangeRates;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service
@Getter
@Setter
public class OpenExchangeRatesServiceImpl {
    private ExchangeRates currentRates;
    private ExchangeRates historicalRates;
    @Value("${openexchangerates.app.id}")
    private String app_id;
    @Autowired
    private FeignOpenExchangeRatesClient feignOpenExchangeRatesClient;

    //получение текущих курсов валют на данный час
    private ExchangeRates getExchangeRatesOnCurrentDates() {
        return this.currentRates = feignOpenExchangeRatesClient.getLatestRates(this.app_id);
    }

    //получение текущих курсов валют на вчера
    private ExchangeRates getExchangeRatesOnYesterday() {
        return this.historicalRates = feignOpenExchangeRatesClient.getHistoricalRates(app_id, toYesterday());
    }

    //вчерашняя дата
    private String toYesterday() {
        Date date = new Date();
        return date.toInstant().atZone(ZoneId.systemDefault())
                .toLocalDate().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    //получение и обновление курсов
    public void refreshRates() {
        if (this.currentRates == null) {
            this.currentRates = getExchangeRatesOnCurrentDates();
        }
        if (this.historicalRates == null) {
            this.historicalRates = getExchangeRatesOnYesterday();
        }
    }

    public List<String> getCurrencies() {
        return getExchangeRatesOnCurrentDates().getRates().keySet().stream().toList();
    }

    //сравнение валют
    public String compareCurrencies(String currency) {
        switch (this.currentRates.getRates().get(currency).compareTo(this.historicalRates.getRates().get(currency))) {
            case 1:
                return "rich";
            case -1:
                return "broke";
            default:
                return "";
        }

    }
}
