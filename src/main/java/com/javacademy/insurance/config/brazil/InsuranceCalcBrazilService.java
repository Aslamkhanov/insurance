package com.javacademy.insurance.config.brazil;

import com.javacademy.insurance.enums.TypeInsurance;
import com.javacademy.insurance.interfaces.InsuranceCalcService;

import java.math.BigDecimal;

import static com.javacademy.insurance.enums.TypeInsurance.HEALTH_INSURANCE;
import static com.javacademy.insurance.enums.TypeInsurance.ROBBERY_PROTECTION;

public class InsuranceCalcBrazilService implements InsuranceCalcService {
    private final static BigDecimal ROBBERY_COEFFICIENT = BigDecimal.valueOf(0.05);
    private final static BigDecimal MEDICAL_CASE_RATIO = BigDecimal.valueOf(0.03);
    private final static BigDecimal THREE_HUNDRED = BigDecimal.valueOf(300);
    private final static BigDecimal EIGHT_HUNDRED = BigDecimal.valueOf(800);

    @Override
    public BigDecimal insuranceCalcService(BigDecimal coverageAmount, TypeInsurance typeInsurance) {
        if (typeInsurance.equals(ROBBERY_PROTECTION)) {
            return coverageAmount = coverageAmount.multiply(ROBBERY_COEFFICIENT).add(THREE_HUNDRED);

        } else if (typeInsurance.equals(HEALTH_INSURANCE)) {
            return coverageAmount = coverageAmount.multiply(MEDICAL_CASE_RATIO).add(EIGHT_HUNDRED);
        } else {
            throw new IllegalArgumentException("У нас нет такого типа " + typeInsurance + " страхования");
        }
    }
}
