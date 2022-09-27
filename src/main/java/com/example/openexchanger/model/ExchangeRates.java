package com.example.openexchanger.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
public class ExchangeRates {
    private String disclaimer;
    private String license;
    private int timestamp;
    private String base;
    private Map<String, Double> rates;
}
