package com.javacademy.insurance.japanservice;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;

@Data
@ConfigurationProperties(prefix = "insurance")
public class JapanProperty {
    private String country;
    private String currency;
    private BigDecimal ROBBERY_COEFFICIENT;
    private BigDecimal MEDICAL_CASE_RATIO;
    private BigDecimal TEN_THOUSAND;
    private BigDecimal TWELVE_THOUSAND;
}
