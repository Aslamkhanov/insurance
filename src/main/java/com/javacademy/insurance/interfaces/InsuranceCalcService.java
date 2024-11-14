package com.javacademy.insurance.interfaces;

import com.javacademy.insurance.enums.TypeInsurance;

import java.math.BigDecimal;
@FunctionalInterface
public interface InsuranceCalcService {
    public BigDecimal insuranceCalcService(BigDecimal coverageAmount, TypeInsurance typeInsurance);
}
