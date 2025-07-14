package com.weavus.weavus_bankweb.entity.API;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRateAPIEntity {
    //どの国を元にするか決める。
    @JsonProperty("base_code")
    private String baseCode;

    //UPDATE TIME
    //JSON name: time_last_update_utc"
    @JsonProperty("time_last_update_utc")
    private String timeLastUpdateUtc;

    //為替レートを適用する国々。
    //JSON name: "conversion_rates"
    @JsonProperty("conversion_rates")
    private Map<String, Double> conversionRates;
}
