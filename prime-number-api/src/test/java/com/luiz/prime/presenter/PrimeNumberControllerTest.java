package com.luiz.prime.presenter;

import static org.mockito.ArgumentMatchers.eq;

import java.util.List;

import com.luiz.prime.domain.CalculatePrimeNumbers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
class PrimeNumberControllerTest {

    @InjectMocks
    private PrimeNumberController sut;

    @Mock
    private CalculatePrimeNumbersStub service;

    @Service
    public class CalculatePrimeNumbersStub implements CalculatePrimeNumbers {
        public List<Long> getPrimeNumbers(Long lower, Long upper) {
            return List.of(2L, 3L, 5L, 7L, 11L, 13L, 17L);
        }
    }

    @Test
    @DisplayName("Should call the service.calculate passing the parameters correctly")
    void callServiceGetPrimeNumbersCorrectly() {
        Long value1 = 1L;
        Long value2 = 10L;

        sut.getPrimeNumberList(value1, value2);
        BDDMockito.verify(service).getPrimeNumbers(eq(value1), eq(value2));
    }

}
