package com.luiz.prime.presenter;

import static org.mockito.ArgumentMatchers.eq;

import java.util.List;

import com.luiz.prime.domain.SearchForPrimeNumbersInTheRange;

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
    private SearchForPrimeNumbersInTheRangeStub service;

    @Service
    public class SearchForPrimeNumbersInTheRangeStub implements SearchForPrimeNumbersInTheRange {

        @Override
        public List<Long> getPrimeNumberList(Long lower, Long upper) {
            return List.of(2L, 3L, 5L, 7L, 11L, 13L, 17L);
        }

    }

    @Test
    @DisplayName("Should call the service passing the parameters in the correct order")
    void callServiceCorrectly() {
        Long lower = 1L;
        Long upper = 10L;

        sut.getPrimeNumberList(upper, lower);
        BDDMockito.verify(service).getPrimeNumberList(eq(lower), eq(upper));
    }
}
