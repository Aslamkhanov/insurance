package com.javacademy.insurance.services;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Getter
@Slf4j
@Component
public class Archive {
    private Map<String, InsuranceContract> archiveOfContracts = new HashMap<>();

    public void addArchive(InsuranceContract insuranceContract) {
        archiveOfContracts.put(insuranceContract.getContractNumber(), insuranceContract);
        log.info("Контракт № {}, успешно добавлен ", insuranceContract.getContractNumber());
    }

    public InsuranceContract getContract(String number) {
        InsuranceContract contract = archiveOfContracts.get(number);
        return contract;
    }
}
