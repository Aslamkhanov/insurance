package com.javacademy.insurance.config;

import com.javacademy.insurance.japanservice.InsuranceCalcJapanService;
import com.javacademy.insurance.japanservice.InsuranceServiceJapan;
import com.javacademy.insurance.japanservice.JapanProperty;
import com.javacademy.insurance.services.Archive;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("japan")
public class JapanConfig {
    @Bean
    public JapanProperty japanProperty() {
        return new JapanProperty();
    }
    @Bean
    public InsuranceCalcJapanService insuranceCalcJapanService() {
        return new InsuranceCalcJapanService();
    }
    @Bean
    public Archive archive() {
        return new Archive();
    }
    @Bean
    public InsuranceServiceJapan insuranceServiceJapan() {
        return new InsuranceServiceJapan();
    }
}
