package com.luiz.prime.data;

import java.util.List;
import java.util.SortedSet;
import java.util.stream.Collectors;

import com.luiz.prime.domain.SearchForPrimeNumbersInTheRange;

import org.springframework.stereotype.Service;

@Service
public class SearchForPrimeNumbersService implements SearchForPrimeNumbersInTheRange {
    private SortedSet<Long> setPrimeNumbers;

    public SearchForPrimeNumbersService(SortedSet<Long> setPrimeNumbers) {
        this.setPrimeNumbers = setPrimeNumbers;
        this.setPrimeNumbers.addAll(List.of(2L,3L,5L,7L));
        //TreeSet setPrimeNumbers
        //Collections.synchronizedSortedSet(this.setPrimeNumbers);
    }

    @Override
    public List<Long> getPrimeNumberList(Long lower, Long upper) {
        if (lower.equals(upper)) {
            return List.of();
        }
        return this.setPrimeNumbers.stream().filter(x -> x > lower && x < upper).collect(Collectors.toList());
    }

}
