package com.javacademy.insurance.japanservice;

import com.javacademy.insurance.enums.TypeInsurance;
import com.javacademy.insurance.interfaces.InsuranceCalcService;

import java.math.BigDecimal;

import static com.javacademy.insurance.enums.TypeInsurance.HEALTH_INSURANCE;
import static com.javacademy.insurance.enums.TypeInsurance.ROBBERY_PROTECTION;

public class InsuranceCalcJapanService implements InsuranceCalcService {
    private JapanProperty japanProperty;

    @Override
    public BigDecimal insuranceCalcService(BigDecimal coverageAmount, TypeInsurance typeInsurance) {
        if (typeInsurance.equals(ROBBERY_PROTECTION)) {
            return coverageAmount = coverageAmount.multiply(japanProperty.getRobberyCoefficient())
                    .add(japanProperty.getTenThousand());

        } else if (typeInsurance.equals(HEALTH_INSURANCE)) {
            return coverageAmount = coverageAmount.multiply(japanProperty.getMedicalCaseRatio())
                    .add(japanProperty.getTwelveThousand());
        } else {
            throw new IllegalArgumentException("У нас нет такого типа " + typeInsurance + " страхования");
        }
    }
}
