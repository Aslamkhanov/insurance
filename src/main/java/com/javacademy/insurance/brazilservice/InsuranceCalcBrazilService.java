package com.javacademy.insurance.brazilservice;

import com.javacademy.insurance.enums.TypeInsurance;
import com.javacademy.insurance.interfaces.InsuranceCalcService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.math.BigDecimal;

import static com.javacademy.insurance.enums.TypeInsurance.HEALTH_INSURANCE;
import static com.javacademy.insurance.enums.TypeInsurance.ROBBERY_PROTECTION;
@RequiredArgsConstructor
@EnableConfigurationProperties(value = BrazilProperty.class)
public class InsuranceCalcBrazilService implements InsuranceCalcService {
    private final BrazilProperty brazilProperty;

    @Override
    public BigDecimal insuranceCalcService(BigDecimal coverageAmount, TypeInsurance typeInsurance) {
        if (typeInsurance.equals(ROBBERY_PROTECTION)) {
            return coverageAmount = coverageAmount.multiply(brazilProperty.getRobberyCoefficient())
                    .add(brazilProperty.getThreeHundred());

        } else if (typeInsurance.equals(HEALTH_INSURANCE)) {
            return coverageAmount = coverageAmount.multiply(brazilProperty.getMedicalCaseRatio())
                    .add(brazilProperty.getEightHundred());
        } else {
            throw new IllegalArgumentException("У нас нет такого типа " + typeInsurance + " страхования");
        }
    }
}
