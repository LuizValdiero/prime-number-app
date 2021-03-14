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
            int maxPosition = this.findIndex(max);
            List<Long> list = this.primeNumberList.subList(0, maxPosition);
            for(Long prime: list) {
                if(value%prime == 0) {
                    isPrime = false;
                    break;
                } 
            }
            if(isPrime)
                this.primeNumberList.add(value);
        }
        this.upperCalculated = upper;
    }
    
    public int findIndex(Long value) {
        int position = Collections.binarySearch(this.primeNumberList, value);
        if(position < -1)
            return (position*-1)-1;
        return position;
    }
}
