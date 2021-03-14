package com.luiz.prime.data;

import java.util.List;

import com.luiz.prime.domain.CalculatePrimeNumbers;
import com.luiz.prime.domain.SearchForPrimeNumbersInTheRange;

import org.springframework.stereotype.Service;

@Service
public class SearchForPrimeNumbersService implements SearchForPrimeNumbersInTheRange {
    private CalculatePrimeNumbers calculator;

    public SearchForPrimeNumbersService() {
        this.calculator = new CalculatePrimeNumbersArray();
    }

    public List<Long> getPrimeNumberList(Long lower, Long upper) {
        if (lower.equals(upper)) {
            return List.of();
        }
        this.calculator.calculate(upper);
        return this.calculator.getPrimeNumbersWithinTheRange(lower, upper);
    }
}
