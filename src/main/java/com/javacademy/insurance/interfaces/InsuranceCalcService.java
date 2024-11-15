package com.javacademy.insurance.interfaces;

import com.javacademy.insurance.enums.TypeInsurance;
import com.javacademy.insurance.exceptions.ContractNotFoundException;

import java.math.BigDecimal;
@FunctionalInterface
public interface InsuranceCalcService {
    public BigDecimal insuranceCalcService(BigDecimal coverageAmount, TypeInsurance typeInsurance) throws ContractNotFoundException;
}
