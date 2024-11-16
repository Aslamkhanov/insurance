package com.javacademy.insurance.config;

import com.javacademy.insurance.brazilservice.BrazilProperty;
import com.javacademy.insurance.brazilservice.InsuranceCalcBrazilService;
import com.javacademy.insurance.brazilservice.InsuranceServiceBrazil;
import com.javacademy.insurance.services.Archive;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("brazil")
@EnableConfigurationProperties(value = BrazilProperty.class)
public class BrazilConfig {
    @Bean
    public BrazilProperty brazilProperty() {
        return new BrazilProperty();
    }

    @Bean
    public Archive archive() {
        return new Archive();
    }

    @Bean
    public InsuranceCalcBrazilService insuranceCalcBrazilService() {
        return new InsuranceCalcBrazilService(brazilProperty());
    }

    @Bean
    public InsuranceServiceBrazil insuranceServiceBrazil() {
        return new InsuranceServiceBrazil(archive(), brazilProperty());
    }
}
