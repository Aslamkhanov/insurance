package com.javacademy.insurance.japanservice;

import com.javacademy.insurance.enums.TypeInsurance;
import com.javacademy.insurance.interfaces.InsuranceCalcService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.javacademy.insurance.enums.TypeInsurance.HEALTH_INSURANCE;
import static com.javacademy.insurance.enums.TypeInsurance.ROBBERY_PROTECTION;

@AllArgsConstructor
@Profile("japan")
@Component
public class InsuranceCalcJapanService implements InsuranceCalcService {
    private final static BigDecimal TEN_THOUSAND = BigDecimal.valueOf(10_000);
    private final static BigDecimal TWELVE_THOUSAND = BigDecimal.valueOf(12_000);
    private final JapanProperty japanProperty;

    @Override
    public BigDecimal totalCostOfInsurance(BigDecimal coverageAmount, TypeInsurance typeInsurance) {
        if (typeInsurance.equals(ROBBERY_PROTECTION)) {
            return coverageAmount.multiply(japanProperty.getRobberyCoefficient())
                    .add(TEN_THOUSAND);

        } else if (typeInsurance.equals(HEALTH_INSURANCE)) {
            return coverageAmount.multiply(japanProperty.getMedicalCaseRatio())
                    .add(TWELVE_THOUSAND);
        } else {
            throw new IllegalArgumentException("У нас нет такого типа " + typeInsurance + " страхования");
        }
    }
}
