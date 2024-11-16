package com.javacademy.insurance.services;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;


@Getter
@Slf4j
public class Archive {
    private Map<String, InsuranceContract> archiveOfContracts;

    public void addArchive(InsuranceContract insuranceContract) {
        if (archiveOfContracts.containsKey(insuranceContract.getContractNumber())) {
            throw new IllegalArgumentException("Номер контракта " +
                    insuranceContract.getContractNumber() + " уже существует.");
        }
        archiveOfContracts.put(insuranceContract.getContractNumber(), insuranceContract);
        log.info("Контракт № {}, успешно добавлен ", insuranceContract.getContractNumber());
    }

    public void removeContract(String contractNumber) {
        archiveOfContracts.remove(contractNumber);
        log.info("Контракт № {}, удален", contractNumber);
    }

    public InsuranceContract getContract(String contractNumber) {
        if (archiveOfContracts.containsKey(contractNumber)) {
            InsuranceContract insuranceContract = archiveOfContracts.get(contractNumber);
            return insuranceContract;
        }
        log.info("Страховки с номером: {} , нет в архиве", contractNumber);
        return null;
    }
}
