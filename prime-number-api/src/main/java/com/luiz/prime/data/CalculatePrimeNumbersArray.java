package com.luiz.prime.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.luiz.prime.domain.CalculatePrimeNumbers;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CalculatePrimeNumbersArray implements CalculatePrimeNumbers {
    @Autowired
    private ArrayList<Long> primeNumberList;
    private Long upperCalculated;

    public CalculatePrimeNumbersArray() {
        this.primeNumberList = new ArrayList<>();
        this.primeNumberList.add(2L);
        this.upperCalculated = 2L;
    }
    
    public void calculate(Long upper) {
        if(upper <= this.upperCalculated)
            return;
        Long max = this.upperCalculated/2;
        boolean isPrime;
        for(Long value = this.upperCalculated+1; value <= upper; value++) {
            max += value%2;
            isPrime = true;
            int maxPosition = this.findIndex(max, true);
            List<Long> list = this.primeNumberList.subList(0, maxPosition);
            for(Long prime: list) {
                if(value%prime == 0) {
                    isPrime = false;
                    break;
                } 
            }
            if(isPrime) {
                this.primeNumberList.add(value);
            }
        }
        this.upperCalculated = upper;
    }

    public List<Long> getPrimeNumbersWithinTheRange(Long lower, Long upper) {
        List<Long> list;
        int lowerPosition = this.findIndex(lower);
        int upperPosition = this.findIndex(upper, true);

        if (lowerPosition < 0)
            lowerPosition = 0;
        if (upperPosition <=0) {
            list = List.of();
        } else {
            if(upperPosition >= this.primeNumberList.size())
                upperPosition = this.primeNumberList.size();
            list = this.primeNumberList.subList(lowerPosition, upperPosition);
        }
        return list;
    }

    public int findIndex(Long value) {
        return findIndex(value, false);
    }

    public int findIndex(Long value, boolean oneMore) {
        int position = Collections.binarySearch(this.primeNumberList, value);
        if(position < -1)
            return (position*-1) - 1;
        
        if (oneMore && position >= 0)
            position++;
             
        return position;
    }
}
