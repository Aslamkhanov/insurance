package com.javacademy.insurance;

import com.javacademy.insurance.enums.TypeInsurance;
import com.javacademy.insurance.japanservice.InsuranceCalcJapanService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
@SpringBootTest
@ActiveProfiles("japan")
public class InsuranceCalcJapanServiceTest {
    @Autowired
    private InsuranceCalcJapanService insuranceCalcJapanService;
    @Test
    @DisplayName("Расчет стоимости при грабеже для Японии")
    public void insuranceSettlementForRobberyJapanSuccess() {
        BigDecimal calcServiceResult = insuranceCalcJapanService.totalCostOfInsurance(
                BigDecimal.valueOf(1_000_000), TypeInsurance.ROBBERY_PROTECTION);
        BigDecimal expected = BigDecimal.valueOf(20_000);
        int result = expected.compareTo(calcServiceResult);
        Assertions.assertEquals(0, result);
    }
    @Test
    @DisplayName("Расчет стоимости при мед страховке для Японии")
    public void insuranceCalculationForMedicalInsuranceJapanSuccess() {
        BigDecimal calcServiceResult = insuranceCalcJapanService.totalCostOfInsurance(
                BigDecimal.valueOf(10_000_000), TypeInsurance.HEALTH_INSURANCE);
        BigDecimal expected = BigDecimal.valueOf(162_000);
        int result = expected.compareTo(calcServiceResult);
        Assertions.assertEquals(0, result);
    }
}
