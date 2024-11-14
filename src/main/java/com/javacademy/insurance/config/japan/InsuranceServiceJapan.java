package com.javacademy.insurance.config.japan;

import com.javacademy.insurance.*;
import com.javacademy.insurance.enums.TypeInsurance;
import com.javacademy.insurance.interfaces.InsuranceService;

import java.math.BigDecimal;

import static com.javacademy.insurance.enums.ContractStatus.PAID_CONTRACT;
import static com.javacademy.insurance.enums.ContractStatus.UNPAID_CONTRACT;
import static com.javacademy.insurance.enums.Country.JAPAN;
import static com.javacademy.insurance.enums.Currency.YEN;

public class InsuranceServiceJapan implements InsuranceService {
    private Archive archive;

    @Override
    public InsuranceContract insuranceOffer(BigDecimal coverageAmount,
                                            ClientsFullName clientsFullName,
                                            TypeInsurance typeInsurance) {
        InsuranceCalcJapanService insuranceCalcJapanService = new InsuranceCalcJapanService();
        BigDecimal calcServicePrice = insuranceCalcJapanService.insuranceCalcService(coverageAmount, typeInsurance);
        String contractNumber = ContractNumberGenerator.generateContractNumber();

        return new InsuranceContract(contractNumber,
                calcServicePrice, coverageAmount, YEN, clientsFullName,
                JAPAN, typeInsurance, UNPAID_CONTRACT);
    }

    @Override
    public InsuranceContract insurancePayment(String contractNumber) {
        if (archive.getArchiveOfContracts().containsKey(contractNumber)) {
            InsuranceContract insuranceContract = archive.getArchiveOfContracts().get(contractNumber);
            insuranceContract.setContractStatus(PAID_CONTRACT);
            return insuranceContract;
        } else {
            throw new IllegalArgumentException("Договор с номером " + contractNumber + " не найден.");
        }
    }
}
