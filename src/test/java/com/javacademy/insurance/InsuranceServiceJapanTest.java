package com.javacademy.insurance;

import com.javacademy.insurance.enums.ContractStatus;
import com.javacademy.insurance.enums.TypeInsurance;
import com.javacademy.insurance.japanservice.InsuranceCalcJapanService;
import com.javacademy.insurance.japanservice.InsuranceServiceJapan;
import com.javacademy.insurance.japanservice.JapanProperty;
import com.javacademy.insurance.services.Archive;
import com.javacademy.insurance.services.ContractNumberGenerator;
import com.javacademy.insurance.services.InsuranceContract;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static com.javacademy.insurance.enums.ContractStatus.*;
import static com.javacademy.insurance.enums.TypeInsurance.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("japan")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class InsuranceServiceJapanTest {
    private static final BigDecimal COVERAGE_AMOUNT_OF_ROBBERY = BigDecimal.valueOf(1_000_000);
    private static final BigDecimal EXPECTED_OF_ROBBERY = BigDecimal.valueOf(20_000);
    @Autowired
    private InsuranceServiceJapan insuranceServiceJapan;
    @Autowired
    private Archive archive;
    @MockBean
    private InsuranceCalcJapanService insuranceCalcJapanService;
    @MockBean
    private JapanProperty japanProperty;
    @MockBean
    private ContractNumberGenerator contractNumberGenerator;

    @Test
    public void insuranceOfferSuccess() {
        when(contractNumberGenerator.generateContractNumber()).thenReturn("001");
        when(insuranceCalcJapanService.totalCostOfInsurance(COVERAGE_AMOUNT_OF_ROBBERY,
                ROBBERY_PROTECTION)).thenReturn(EXPECTED_OF_ROBBERY);

        InsuranceContract insuranceContract = insuranceServiceJapan.insuranceOffer(COVERAGE_AMOUNT_OF_ROBBERY,
                "Иванов Иван Иванович", ROBBERY_PROTECTION);
        Assertions.assertEquals("001",insuranceContract.getContractNumber());
        Assertions.assertEquals(EXPECTED_OF_ROBBERY,insuranceContract.getInsuranceCost());
        Assertions.assertEquals(COVERAGE_AMOUNT_OF_ROBBERY,insuranceContract.getCoverageAmount());
        Assertions.assertEquals("YEN",insuranceContract.getContractCurrency());
        Assertions.assertEquals("Иванов Иван Иванович",insuranceContract.getClientsFullName());
        Assertions.assertEquals("JAPAN",insuranceContract.getCountryOfAction());
        Assertions.assertEquals(ROBBERY_PROTECTION,insuranceContract.getTypeInsurance());
        Assertions.assertEquals(UNPAID_CONTRACT,insuranceContract.getContractStatus());
    }
}
