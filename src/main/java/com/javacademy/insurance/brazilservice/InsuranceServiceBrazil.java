package com.javacademy.insurance.brazilservice;

import com.javacademy.insurance.enums.TypeInsurance;
import com.javacademy.insurance.exceptions.ContractNotFoundException;
import com.javacademy.insurance.interfaces.InsuranceService;
import com.javacademy.insurance.services.Archive;
import com.javacademy.insurance.services.ContractNumberGenerator;
import com.javacademy.insurance.services.InsuranceContract;

import java.math.BigDecimal;

import static com.javacademy.insurance.enums.ContractStatus.PAID_CONTRACT;
import static com.javacademy.insurance.enums.ContractStatus.UNPAID_CONTRACT;

public class InsuranceServiceBrazil implements InsuranceService {
    private Archive archive;
    private BrazilProperty brazilProperty;

    @Override
    public InsuranceContract insuranceOffer(BigDecimal coverageAmount,
                                            String clientsFullName,
                                            TypeInsurance typeInsurance) {

        InsuranceCalcBrazilService insuranceCalcBrazilService = new InsuranceCalcBrazilService();
        BigDecimal calcServicePrice = insuranceCalcBrazilService.insuranceCalcService(coverageAmount, typeInsurance);
        String contractNumber = ContractNumberGenerator.generateContractNumber();

        InsuranceContract insuranceContract = new InsuranceContract(contractNumber,
                calcServicePrice, coverageAmount, brazilProperty.getCurrency(), clientsFullName,
                brazilProperty.getCountry(), typeInsurance, UNPAID_CONTRACT);
        archive.addArchive(insuranceContract);
        return insuranceContract;
    }

    @Override
    public InsuranceContract insurancePayment(String contractNumber) throws ContractNotFoundException {
        if (archive.getArchiveOfContracts().containsKey(contractNumber)) {
            InsuranceContract insuranceContract = archive.getArchiveOfContracts().get(contractNumber);
            insuranceContract.setContractStatus(PAID_CONTRACT);
            return insuranceContract;
        } else {
            throw new ContractNotFoundException("Договор с номером " + contractNumber + " не найден.");
        }
    }
}
