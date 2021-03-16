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
    @DisplayName("Should return the cut position when the value is not found - with one more")
    void getPositionValueIsNotFoundWithOneMore() {
        int expected = 2;
        int position = sut.findIndex(4L, true);
        Assertions.assertEquals(expected, position);
    }
    
    @Test
    @DisplayName("Should return the position value more one when the value is found - with one more")
    void getPositionValueIsFoundWithOneMore() {
        int expected = 2;
        int position = sut.findIndex(3L, true);
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
    @Test
    @DisplayName("Should return the smallest prime number to the first prime number less than 8")
    void smallestPrimeNumberToTheLessThan8() {
        Long lower = 1L;
        Long upper = 8L;
        List<Long> expected = List.of(2L, 3L, 5L, 7L);
        List<Long> actual = sut.getPrimeNumbersWithinTheRange(lower, upper);
        Assertions.assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    @DisplayName("Should return a prime number between two non-prime numbers")
    void aPrimeBetweenTwoNonPrimeNumbers() {
        Long lower = 16L;
        Long upper = 18L;
        List<Long> expected = List.of(17L);
        List<Long> actual = sut.getPrimeNumbersWithinTheRange(lower, upper);
        Assertions.assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    @DisplayName("should only return a prime number that is equal to the upper range")
    void aPrimeAndEqualUpper() {
        Long lower = 16L;
        Long upper = 17L;
        List<Long> expected = List.of(17L);
        List<Long> actual = sut.getPrimeNumbersWithinTheRange(lower, upper);
        Assertions.assertArrayEquals(expected.toArray(), actual.toArray());
    }
    
    @Test
    @DisplayName("should only return a prime number that is equal to the lower range")
    void aPrimeAndEqualLower() {
        Long lower = 17L;
        Long upper = 18L;
        List<Long> expected = List.of(17L);
        List<Long> actual = sut.getPrimeNumbersWithinTheRange(lower, upper);
        Assertions.assertArrayEquals(expected.toArray(), actual.toArray());
    }
    
    @Test
    @DisplayName("should return a prime number when the two numbers are equal and are also a prime number")
    void aPrimeEqualLowerAndUpper() {
        Long lower = 17L;
        Long upper = 17L;
        List<Long> expected = List.of(17L);
        List<Long> actual = sut.getPrimeNumbersWithinTheRange(lower, upper);
        Assertions.assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    @DisplayName("Should work with a lower range less than zero")
    void lowerRangeLessThanZero() {
        Long lower = -20L;
        Long upper = 3L;
        List<Long> expected = List.of(2L, 3L);
        List<Long> actual = sut.getPrimeNumbersWithinTheRange(lower, upper);
        Assertions.assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    @DisplayName("An empty list must be returned if the upper range is less than the smallest prime number")
    void upperRangeIsLessThanTheSmallestPrime() {
        Long lower = -20L;
        Long upper = -2L;
        List<Long> expected = List.of();
        List<Long> actual = sut.getPrimeNumbersWithinTheRange(lower, upper);
        Assertions.assertArrayEquals(expected.toArray(), actual.toArray());
    }
}
