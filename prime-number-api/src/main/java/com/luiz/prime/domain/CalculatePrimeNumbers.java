package com.luiz.prime.domain;

import java.util.List;

public interface CalculatePrimeNumbers {
    public void calculate(Long upper);
    public List<Long> getPrimeNumbersWithinTheRange(Long lower, Long upper);
}
