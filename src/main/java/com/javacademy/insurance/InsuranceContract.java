package com.javacademy.insurance;

import com.javacademy.insurance.enums.ContractStatus;
import com.javacademy.insurance.enums.Country;
import com.javacademy.insurance.enums.Currency;
import com.javacademy.insurance.enums.TypeInsurance;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class InsuranceContract {
    private String contractNumber;
    private BigDecimal insuranceCost;
    private BigDecimal coverageAmount;
    private Currency contractCurrency;
    private ClientsFullName clientsFullName;
    private Country countryOfAction;
    private TypeInsurance typeInsurance;
    private ContractStatus contractStatus;

    public InsuranceContract(String contractNumber, BigDecimal insuranceCost, BigDecimal coverageAmount,
                             Currency contractCurrency, ClientsFullName clientsFullName, Country countryOfAction,
                             TypeInsurance typeInsurance, ContractStatus contractStatus) {
        this.contractNumber = contractNumber;
        this.insuranceCost = insuranceCost;
        this.coverageAmount = coverageAmount;
        this.contractCurrency = contractCurrency;
        this.clientsFullName = clientsFullName;
        this.countryOfAction = countryOfAction;
        this.typeInsurance = typeInsurance;
        this.contractStatus = contractStatus;
    }
}
