package com.luiz.prime.domain;

import java.util.ArrayList;
import java.util.Collections;

import com.luiz.prime.data.CalculatePrimeNumbers;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CalculatePrimeNumbersArray implements CalculatePrimeNumbers {
    private ArrayList<Long> primeNumberList;

    public CalculatePrimeNumbersArray() {
        this.primeNumberList = new ArrayList<Long>();
        this.primeNumberList.add(2L);
    }
    
    public int findIndex(Long value) {
        int position = Collections.binarySearch(this.primeNumberList, value);
        if(position < -1)
            return (position*-1)-1;
        return position;
    }
}
