package com.javacademy.insurance.services;

import com.javacademy.insurance.enums.ContractStatus;
import com.javacademy.insurance.enums.TypeInsurance;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
public class InsuranceContract {
    private String contractNumber;
    private BigDecimal insuranceCost;
    private BigDecimal coverageAmount;
    private String contractCurrency;
    private String clientsFullName;
    private String countryOfAction;
    private TypeInsurance typeInsurance;
    private ContractStatus contractStatus;
}
