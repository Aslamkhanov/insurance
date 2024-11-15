package com.javacademy.insurance.services;

import java.util.concurrent.atomic.AtomicInteger;

public class ContractNumberGenerator {
    private static final String PREFIX = "CONTRACT-";
    private static final AtomicInteger counter = new AtomicInteger(0);

    public static String generateContractNumber() {
        return PREFIX + counter.incrementAndGet();
    }
}
