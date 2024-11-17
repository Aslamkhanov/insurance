package com.javacademy.insurance;

import com.javacademy.insurance.brazilservice.InsuranceCalcBrazilService;
import com.javacademy.insurance.enums.TypeInsurance;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

@SpringBootTest
@ActiveProfiles("brazil")
public class InsuranceCalcBrazilServiceTest {
    @Autowired
    private InsuranceCalcBrazilService insuranceCalcBrazilService;

    @Test
    @DisplayName("Расчет стоимости при грабеже для Бразилии")
    public void insuranceSettlementForRobberyBrazilSuccess() {
        BigDecimal calcServiceResult = insuranceCalcBrazilService.totalCostOfInsurance(
                BigDecimal.valueOf(50_000), TypeInsurance.ROBBERY_PROTECTION);
        BigDecimal expected = BigDecimal.valueOf(2800);
        int result = expected.compareTo(calcServiceResult);
        Assertions.assertEquals(0, result);
    }
    @Test
    @DisplayName("Расчет стоимости при мед страховке для Бразилии")
    public void insuranceCalculationForMedicalInsuranceBrazilSuccess() {
        BigDecimal calcServiceResult = insuranceCalcBrazilService.totalCostOfInsurance(
                BigDecimal.valueOf(200_000), TypeInsurance.HEALTH_INSURANCE);
        BigDecimal expected = BigDecimal.valueOf(6800);
        int result = expected.compareTo(calcServiceResult);
        Assertions.assertEquals(0, result);
    }


}
