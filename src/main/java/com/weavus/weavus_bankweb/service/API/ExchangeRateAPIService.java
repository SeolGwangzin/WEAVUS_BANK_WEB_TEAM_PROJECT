package com.weavus.weavus_bankweb.service.API;

import com.weavus.weavus_bankweb.entity.API.ExchangeRateAPIEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExchangeRateAPIService {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/30abfca8f0d7a62a220ba00b/latest/JPY";

    public ExchangeRateAPIEntity getLatestRatesFromJpy(){
        try {
            RestTemplate restTemplate = new RestTemplate();
            ExchangeRateAPIEntity rt = restTemplate.getForObject(API_URL, ExchangeRateAPIEntity.class);
            if (rt == null || rt.getConversionRates() == null) {
                return null;
            }

            List<String> currencyCodes = Arrays.asList("USD", "KRW", "CNY", "EUR", "GBP", "AUD", "CAD");

            Map<String, Double> filteredMap = new HashMap<>();

            for (Map.Entry<String, Double> entry : rt.getConversionRates().entrySet()) {
                String currencyCode = entry.getKey();
                Double rate = entry.getValue();

                if (currencyCodes.contains(currencyCode)) {
                    filteredMap.put(currencyCode, rate);
                }
            }

            rt.setConversionRates(filteredMap);
            return rt;

        } catch (Exception e) {
            System.err.println("APIを使う時にERRORが発生: " + e.getMessage());
            return null;
        }

    }
}
