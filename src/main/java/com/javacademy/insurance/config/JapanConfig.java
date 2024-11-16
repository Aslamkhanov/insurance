package com.javacademy.insurance.config;

import com.javacademy.insurance.japanservice.InsuranceCalcJapanService;
import com.javacademy.insurance.japanservice.InsuranceServiceJapan;
import com.javacademy.insurance.japanservice.JapanProperty;
import com.javacademy.insurance.services.Archive;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("japan")
@EnableConfigurationProperties(value = JapanProperty.class)
public class JapanConfig {
    @Bean
    public InsuranceCalcJapanService insuranceCalcJapanService() {
        return new InsuranceCalcJapanService(new JapanProperty());
    }

    @Bean
    public InsuranceServiceJapan insuranceServiceJapan() {
        return new InsuranceServiceJapan();
    }
}
