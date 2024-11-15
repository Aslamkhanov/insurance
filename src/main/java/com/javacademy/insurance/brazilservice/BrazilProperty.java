package com.javacademy.insurance.brazilservice;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;

@ConfigurationProperties(prefix = "insurance")
@Data
public class BrazilProperty {
    private String country;
    private String currency;
    private BigDecimal ROBBERY_COEFFICIENT;
    private BigDecimal MEDICAL_CASE_RATIO;
    private BigDecimal THREE_HUNDRED;
    private BigDecimal EIGHT_HUNDRED;
}
