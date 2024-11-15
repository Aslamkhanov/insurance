package com.javacademy.insurance.interfaces;

import com.javacademy.insurance.services.InsuranceContract;
import com.javacademy.insurance.enums.TypeInsurance;
import com.javacademy.insurance.exceptions.ContractNotFoundException;

import java.math.BigDecimal;

public interface InsuranceService {
    public InsuranceContract insuranceOffer(BigDecimal coverageAmount,
                                            String clientsFullName,
                                            TypeInsurance typeInsurance) throws ContractNotFoundException;

    public InsuranceContract insurancePayment(String contractNumber) throws ContractNotFoundException;
}
