package com.luiz.prime.data;

import java.util.ArrayList;
import java.util.List;

import com.luiz.prime.domain.CalculatePrimeNumbersArray;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DisplayName("Tests for CalculatePrimeNumbersArray")
public class CalculatePrimeNumbersArrayTest {

    @InjectMocks
    private CalculatePrimeNumbersArray sut;

    @Spy
    ArrayList<Long> mockList = new ArrayList<>(List.of(2L, 3L, 5L, 7L, 11L, 13L, 17L));
    
    @Test
    @DisplayName("Should return the position after the last position when the value is greater than the values in the list")
    void getAfterThanLastPosition() {
        int expected = mockList.size();
        int position = sut.findIndex(20L);
        Assertions.assertEquals(expected, position);
    }
    
    @Test
    @DisplayName("Should return the position before the first position when the value is less than the values in the list")
    void getBeforeThanFirstPosition() {
        int expected = -1;
        int position = sut.findIndex(1L);
        Assertions.assertEquals(expected, position);
    }
    
    @Test
    @DisplayName("Should return the position value when the value is found")
    void getPositionValueIsFound() {
        int expected = 1;
        int position = sut.findIndex(3L);
        Assertions.assertEquals(expected, position);
    }

    @Test
    @DisplayName("Should return the cut position when the value is not found")
    void getPositionValueIsNotFound() {
        int expected = 2;
        int position = sut.findIndex(4L);
        Assertions.assertEquals(expected, position);
    }
}
