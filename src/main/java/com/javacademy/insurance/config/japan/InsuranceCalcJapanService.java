package com.javacademy.insurance.config.japan;

import com.javacademy.insurance.enums.TypeInsurance;
import com.javacademy.insurance.interfaces.InsuranceCalcService;

import java.math.BigDecimal;

import static com.javacademy.insurance.enums.TypeInsurance.HEALTH_INSURANCE;
import static com.javacademy.insurance.enums.TypeInsurance.ROBBERY_PROTECTION;

public class InsuranceCalcJapanService implements InsuranceCalcService {
    private final static BigDecimal ROBBERY_COEFFICIENT = BigDecimal.valueOf(0.1);
    private final static BigDecimal MEDICAL_CASE_RATIO = BigDecimal.valueOf(0.015);
    private final static BigDecimal TEN_THOUSAND = BigDecimal.valueOf(10_000);
    private final static BigDecimal TWELVE_THOUSAND = BigDecimal.valueOf(10_000);

    @Override
    public BigDecimal insuranceCalcService(BigDecimal coverageAmount, TypeInsurance typeInsurance) {
        if (typeInsurance.equals(ROBBERY_PROTECTION)) {
            return coverageAmount = coverageAmount.multiply(ROBBERY_COEFFICIENT).add(TEN_THOUSAND);

        } else if (typeInsurance.equals(HEALTH_INSURANCE)) {
            return coverageAmount = coverageAmount.multiply(MEDICAL_CASE_RATIO).add(TWELVE_THOUSAND);
        } else {
            throw new IllegalArgumentException("У нас нет такого типа " + typeInsurance + " страхования");
        }
    }
}
