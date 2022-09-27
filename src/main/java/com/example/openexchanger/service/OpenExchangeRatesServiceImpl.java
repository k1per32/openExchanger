package com.example.openexchanger.service;

import com.example.openexchanger.client.FeignOpenExchangeRatesClient;
import com.example.openexchanger.model.ExchangeRates;

import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;


@Service
@Getter
@Setter
public class OpenExchangeRatesServiceImpl implements ExchangeRatesService {
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
        getExchangeRatesOnCurrentDates();
        getExchangeRatesOnYesterday();
    }

    @Override
    public List<String> getCurrencies() {
        return getExchangeRatesOnCurrentDates().getRates().keySet().stream().toList();
    }

    //сравнение валют
    public ExchangeRates compareCurrencies(){
        return this.historicalRates = getExchangeRatesOnYesterday();

    }
}
