package com.javacademy.insurance.interfaces;

import com.javacademy.insurance.enums.TypeInsurance;
import com.javacademy.insurance.exceptions.ContractNotFoundException;
import com.javacademy.insurance.services.InsuranceContract;

import java.math.BigDecimal;

public interface InsuranceService {
    public InsuranceContract insuranceOffer(BigDecimal coverageAmount,
                                            String clientsFullName,
                                            TypeInsurance typeInsurance);

    public InsuranceContract insurancePayment(String contractNumber) throws ContractNotFoundException;
}
