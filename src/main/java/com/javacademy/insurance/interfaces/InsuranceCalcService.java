package com.javacademy.insurance.interfaces;

import com.javacademy.insurance.enums.TypeInsurance;

import java.math.BigDecimal;

@FunctionalInterface
public interface InsuranceCalcService {
    public BigDecimal totalCostOfInsurance(BigDecimal coverageAmount, TypeInsurance typeInsurance);
}
