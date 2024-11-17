package com.javacademy.insurance;

import com.javacademy.insurance.exceptions.ContractNotFoundException;
import com.javacademy.insurance.japanservice.InsuranceCalcJapanService;
import com.javacademy.insurance.japanservice.InsuranceServiceJapan;
import com.javacademy.insurance.japanservice.JapanProperty;
import com.javacademy.insurance.services.Archive;
import com.javacademy.insurance.services.ContractNumberGenerator;
import com.javacademy.insurance.services.InsuranceContract;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Map;

import static com.javacademy.insurance.enums.ContractStatus.PAID_CONTRACT;
import static com.javacademy.insurance.enums.ContractStatus.UNPAID_CONTRACT;
import static com.javacademy.insurance.enums.TypeInsurance.HEALTH_INSURANCE;
import static com.javacademy.insurance.enums.TypeInsurance.ROBBERY_PROTECTION;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("japan")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class InsuranceServiceJapanTest {
    private static final BigDecimal COVERAGE_AMOUNT_ROBBERY = BigDecimal.valueOf(1_000_000);
    private static final BigDecimal EXPECTED_ROBBERY = BigDecimal.valueOf(20_000);
    private static final BigDecimal COVERAGE_AMOUNT_MEDICAL = BigDecimal.valueOf(10_000_000);
    private static final BigDecimal EXPECTED_MEDICAL = BigDecimal.valueOf(162_000);
    @Autowired
    private InsuranceServiceJapan insuranceServiceJapan;
    @MockBean
    private Archive archive;
    @Autowired
    private JapanProperty japanProperty;
    @MockBean
    private InsuranceCalcJapanService insuranceCalcJapanService;
    @MockBean
    private ContractNumberGenerator contractNumberGenerator;

    @Test
    @DisplayName("Предложение по страховке для Японии, тип от грабежа ")
    public void insuranceOfferRobberySuccess() {
        when(contractNumberGenerator.generateContractNumber()).thenReturn("001");
        when(insuranceCalcJapanService.totalCostOfInsurance(COVERAGE_AMOUNT_ROBBERY,
                ROBBERY_PROTECTION)).thenReturn(EXPECTED_ROBBERY);

        InsuranceContract insuranceContract = insuranceServiceJapan.insuranceOffer(COVERAGE_AMOUNT_ROBBERY,
                "Иванов Иван Иванович", ROBBERY_PROTECTION);
        assertEquals("001", insuranceContract.getContractNumber());
        assertEquals(EXPECTED_ROBBERY, insuranceContract.getInsuranceCost());
        assertEquals(COVERAGE_AMOUNT_ROBBERY, insuranceContract.getCoverageAmount());
        assertEquals("YEN", insuranceContract.getContractCurrency());
        assertEquals("Иванов Иван Иванович", insuranceContract.getClientsFullName());
        assertEquals("JAPAN", insuranceContract.getCountryOfAction());
        assertEquals(ROBBERY_PROTECTION, insuranceContract.getTypeInsurance());
        assertEquals(UNPAID_CONTRACT, insuranceContract.getContractStatus());
    }

    @Test
    @DisplayName("Предложение по страховке для Японии, тип мед страховка ")
    public void insuranceOfferMedicalSuccess() {
        when(contractNumberGenerator.generateContractNumber()).thenReturn("001");
        when(insuranceCalcJapanService.totalCostOfInsurance(COVERAGE_AMOUNT_MEDICAL,
                HEALTH_INSURANCE)).thenReturn(EXPECTED_MEDICAL);

        InsuranceContract insuranceContract = insuranceServiceJapan.insuranceOffer(COVERAGE_AMOUNT_MEDICAL,
                "Иванов Иван Иванович", HEALTH_INSURANCE);
        assertEquals("001", insuranceContract.getContractNumber());
        assertEquals(EXPECTED_MEDICAL, insuranceContract.getInsuranceCost());
        assertEquals(COVERAGE_AMOUNT_MEDICAL, insuranceContract.getCoverageAmount());
        assertEquals("YEN", insuranceContract.getContractCurrency());
        assertEquals("Иванов Иван Иванович", insuranceContract.getClientsFullName());
        assertEquals("JAPAN", insuranceContract.getCountryOfAction());
        assertEquals(HEALTH_INSURANCE, insuranceContract.getTypeInsurance());
        assertEquals(UNPAID_CONTRACT, insuranceContract.getContractStatus());
    }

    @Test
    @DisplayName("Оплата мед страховки")
    public void paySuccess() throws ContractNotFoundException {
        String contractNumber = "001";
        when(contractNumberGenerator.generateContractNumber()).thenReturn(contractNumber);
        when(insuranceCalcJapanService.totalCostOfInsurance(COVERAGE_AMOUNT_MEDICAL,
                HEALTH_INSURANCE)).thenReturn(EXPECTED_MEDICAL);

        InsuranceContract insuranceContract = insuranceServiceJapan.insuranceOffer(COVERAGE_AMOUNT_MEDICAL,
                "Иванов Иван Иванович", HEALTH_INSURANCE);

        when(archive.getArchiveOfContracts()).thenReturn(Map.of(contractNumber, insuranceContract));

        InsuranceContract result = insuranceServiceJapan.insurancePayment(contractNumber);

        assertNotNull(result);
        assertEquals(contractNumber, insuranceContract.getContractNumber());
        assertEquals(EXPECTED_MEDICAL, insuranceContract.getInsuranceCost());
        assertEquals(COVERAGE_AMOUNT_MEDICAL, insuranceContract.getCoverageAmount());
        assertEquals("YEN", insuranceContract.getContractCurrency());
        assertEquals("Иванов Иван Иванович", insuranceContract.getClientsFullName());
        assertEquals("JAPAN", insuranceContract.getCountryOfAction());
        assertEquals(HEALTH_INSURANCE, insuranceContract.getTypeInsurance());
        assertEquals(PAID_CONTRACT, insuranceContract.getContractStatus());
    }
}
