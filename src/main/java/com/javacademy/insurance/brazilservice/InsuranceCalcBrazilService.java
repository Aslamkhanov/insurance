package com.javacademy.insurance.brazilservice;

import com.javacademy.insurance.enums.TypeInsurance;
import com.javacademy.insurance.interfaces.InsuranceCalcService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.javacademy.insurance.enums.TypeInsurance.HEALTH_INSURANCE;
import static com.javacademy.insurance.enums.TypeInsurance.ROBBERY_PROTECTION;

@AllArgsConstructor
@Profile("brazil")
@Component
public class InsuranceCalcBrazilService implements InsuranceCalcService {
    private final static BigDecimal THREE_HUNDRED = BigDecimal.valueOf(300);
    private final static BigDecimal EIGHT_HUNDRED = BigDecimal.valueOf(800);
    private final BrazilProperty brazilProperty;

    @Override
    public BigDecimal totalCostOfInsurance(BigDecimal coverageAmount, TypeInsurance typeInsurance) {
        if (typeInsurance.equals(ROBBERY_PROTECTION)) {
            return coverageAmount = coverageAmount.multiply(brazilProperty.getRobberyCoefficient())
                    .add(THREE_HUNDRED);

        } else if (typeInsurance.equals(HEALTH_INSURANCE)) {
            return coverageAmount = coverageAmount.multiply(brazilProperty.getMedicalCaseRatio())
                    .add(EIGHT_HUNDRED);
        } else {
            throw new IllegalArgumentException("У нас нет такого типа " + typeInsurance + " страхования");
        }
    }
}
