package com.javacademy.insurance;

import com.javacademy.insurance.enums.TypeInsurance;
import com.javacademy.insurance.japanservice.InsuranceCalcJapanService;
import org.junit.jupiter.api.Assertions;
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
    public void insuranceCalcServiceSuccess() {
        BigDecimal result = insuranceCalcJapanService.insuranceCalcService(
                BigDecimal.valueOf(1_000_000), TypeInsurance.ROBBERY_PROTECTION);
        BigDecimal expected = BigDecimal.valueOf(20_000);

        BigDecimal result2 = insuranceCalcJapanService.insuranceCalcService(
                BigDecimal.valueOf(10_000_000), TypeInsurance.HEALTH_INSURANCE);
        BigDecimal expected2 = BigDecimal.valueOf(162_000);

        Assertions.assertEquals(expected, result);
        Assertions.assertEquals(expected2, result2);
    }
}
