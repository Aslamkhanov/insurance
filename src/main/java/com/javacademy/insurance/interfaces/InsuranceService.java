package com.javacademy.insurance.interfaces;

import com.javacademy.insurance.ClientsFullName;
import com.javacademy.insurance.InsuranceContract;
import com.javacademy.insurance.enums.TypeInsurance;

import java.math.BigDecimal;

public interface InsuranceService {
    public InsuranceContract insuranceOffer(BigDecimal coverageAmount,
                                            ClientsFullName clientsFullName,
                                            TypeInsurance typeInsurance);


    public InsuranceContract insurancePayment(String contractNumber);
    

//        отвечает за выдачу предложения страховки (на вход сумма покрытия,
//                фио клиента, тип страховки) - на выход страховой договор (статус неоплачен).

//                Созданный договор должен быть помещен в архив.
//        отвечает за оплату страховки (на вход номер договора) -
//                на выход страховой договор с измененным статусом оплаченный договор.
//        Если договора нет, выкидывается ошибка такого договора несуществует.

}
