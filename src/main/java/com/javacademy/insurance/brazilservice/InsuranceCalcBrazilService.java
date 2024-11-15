package com.javacademy.insurance.brazilservice;

import com.javacademy.insurance.enums.TypeInsurance;
import com.javacademy.insurance.interfaces.InsuranceCalcService;

import java.math.BigDecimal;

import static com.javacademy.insurance.enums.TypeInsurance.HEALTH_INSURANCE;
import static com.javacademy.insurance.enums.TypeInsurance.ROBBERY_PROTECTION;

public class InsuranceCalcBrazilService implements InsuranceCalcService {
    private BrazilProperty brazilProperty;

    @Override
    public BigDecimal insuranceCalcService(BigDecimal coverageAmount, TypeInsurance typeInsurance) {
        if (typeInsurance.equals(ROBBERY_PROTECTION)) {
            return coverageAmount = coverageAmount.multiply(brazilProperty.getROBBERY_COEFFICIENT())
                    .add(brazilProperty.getTHREE_HUNDRED());

        } else if (typeInsurance.equals(HEALTH_INSURANCE)) {
            return coverageAmount = coverageAmount.multiply(brazilProperty.getMEDICAL_CASE_RATIO())
                    .add(brazilProperty.getEIGHT_HUNDRED());
        } else {
            throw new IllegalArgumentException("У нас нет такого типа " + typeInsurance + " страхования");
        }
    }
}
