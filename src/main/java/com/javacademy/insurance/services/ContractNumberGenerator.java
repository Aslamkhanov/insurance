package com.javacademy.insurance.services;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;
@Component
public class ContractNumberGenerator {
    private static final String PREFIX = "CONTRACT-";
    private static final AtomicInteger counter = new AtomicInteger(0);

    public String generateContractNumber() {
        return PREFIX + counter.incrementAndGet();
    }
}
