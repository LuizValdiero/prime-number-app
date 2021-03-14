package com.luiz.prime.data;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(SpringExtension.class)
@DisplayName("Tests for CalculatePrimeNumbersArray")
public class CalculatePrimeNumbersArrayTest {

    @InjectMocks
    private CalculatePrimeNumbersArray sut;

    @Spy
    ArrayList<Long> mockList = new ArrayList<>(List.of(2L, 3L, 5L, 7L, 11L, 13L, 17L));

    @BeforeEach
    public void before() throws Exception {
        ReflectionTestUtils.setField(sut, "upperCalculated", 17L);
    }
    
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

    @Test
    @DisplayName("Should not insert new itens to list if value less than upperCalculated")
    void valueIsLessThanUpperCalculated() {
        Long upper = 15L;
        List<Long> oldList = (List<Long>) mockList.clone();
        sut.calculate(upper);
        Assertions.assertNotEquals(upper, ReflectionTestUtils.getField(sut, "upperCalculated"));
        Assertions.assertArrayEquals(oldList.toArray(), mockList.toArray());
    }

    @Test
    @DisplayName("Should not insert new itens but update upperCalculated")
    void valueIsGreaterThanUpperCalculated() {
        Long upper = 18L;
        List<Long> oldList = (List<Long>) mockList.clone();
        sut.calculate(upper);
        Assertions.assertEquals(upper, ReflectionTestUtils.getField(sut, "upperCalculated"));
        Assertions.assertArrayEquals(oldList.toArray(), mockList.toArray());
    }

    @Test
    @DisplayName("Should insert new prime number")
    void addNewPrimeNumber() {
        Long upper = 19L;
        List<Long> oldList = (List<Long>) mockList.clone();
        oldList.add(19L);
        sut.calculate(upper);
        Assertions.assertEquals(upper, ReflectionTestUtils.getField(sut, "upperCalculated"));
        Assertions.assertArrayEquals(oldList.toArray(), mockList.toArray());
    }
}
