package com.javacademy.insurance.japanservice;

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
@Profile("japan")
@EnableConfigurationProperties(value = JapanProperty.class)
@Component
public class InsuranceServiceJapan implements InsuranceService {
    private final Archive archive;
    private final JapanProperty japanProperty;
    private final InsuranceCalcJapanService insuranceCalcJapanService;


    @Override
    public InsuranceContract insuranceOffer(BigDecimal coverageAmount,
                                            String clientsFullName,
                                            TypeInsurance typeInsurance) {
        BigDecimal calcServicePrice = insuranceCalcJapanService.insuranceCalcService(coverageAmount, typeInsurance);
        String contractNumber = ContractNumberGenerator.generateContractNumber();

        InsuranceContract insuranceContract = new InsuranceContract(contractNumber,
                calcServicePrice, coverageAmount, japanProperty.getCurrency(), clientsFullName,
                japanProperty.getCountry(), typeInsurance, UNPAID_CONTRACT);
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
