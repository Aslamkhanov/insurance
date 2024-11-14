package com.javacademy.insurance;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
@AllArgsConstructor
@Getter
public class Archive {
    private Map<String,InsuranceContract> archiveOfContracts;

    public void addArchive(InsuranceContract insuranceContract){
        if (archiveOfContracts.containsKey(insuranceContract.getContractNumber())){
            throw new IllegalArgumentException("Номер контракта " +
                    insuranceContract.getContractNumber() + " уже существует.");
        }
        archiveOfContracts.put(insuranceContract.getContractNumber(), insuranceContract);
    }
    public void removeContract(String contractNumber) {
        archiveOfContracts.remove(contractNumber);
    }
}
