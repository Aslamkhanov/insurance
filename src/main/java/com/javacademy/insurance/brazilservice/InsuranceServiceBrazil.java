package com.javacademy.insurance.brazilservice;

import com.javacademy.insurance.enums.TypeInsurance;
import com.javacademy.insurance.exceptions.ContractNotFoundException;
import com.javacademy.insurance.interfaces.InsuranceService;
import com.javacademy.insurance.services.Archive;
import com.javacademy.insurance.services.ContractNumberGenerator;
import com.javacademy.insurance.services.InsuranceContract;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.javacademy.insurance.enums.ContractStatus.PAID_CONTRACT;
import static com.javacademy.insurance.enums.ContractStatus.UNPAID_CONTRACT;
@RequiredArgsConstructor
@Profile("brazil")
@EnableConfigurationProperties(value = BrazilProperty.class)
@Component
public class InsuranceServiceBrazil implements InsuranceService {
    private final Archive archive;
    private final BrazilProperty brazilProperty;
    private final InsuranceCalcBrazilService insuranceCalcBrazilService;
    private final ContractNumberGenerator contractNumberGenerator;

    @Override
    public InsuranceContract insuranceOffer(BigDecimal coverageAmount,
                                            String clientsFullName,
                                            TypeInsurance typeInsurance) {

        BigDecimal calcServicePrice = insuranceCalcBrazilService.totalCostOfInsurance(coverageAmount, typeInsurance);
        String contractNumber = contractNumberGenerator.generateContractNumber();

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
