package com.javacademy.insurance.japanservice;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;

@Data
@ConfigurationProperties(prefix = "insurance")
public class JapanProperty {
    private String country;
    private String currency;
    private BigDecimal robberyCoefficient;
    private BigDecimal medicalCaseRatio;
}
