package com.javacademy.insurance.brazilservice;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;

@ConfigurationProperties(prefix = "insurance")
@Data
public class BrazilProperty {
    private String country;
    private String currency;
    private BigDecimal robberyCoefficient;
    private BigDecimal medicalCaseRatio;
}
