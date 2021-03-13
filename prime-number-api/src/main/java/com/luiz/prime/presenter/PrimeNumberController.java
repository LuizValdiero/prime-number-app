package com.luiz.prime.presenter;

import java.util.List;

import javax.validation.Valid;

import com.luiz.prime.domain.SearchForPrimeNumbersInTheRange;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PrimeNumberController {
    private final SearchForPrimeNumbersInTheRange searchForPrimeNumbers;

    @GetMapping("")
    public List<Long> getPrimeNumberList(@RequestParam @Valid Long value1, @RequestParam @Valid Long value2) {

        long lower, upper;
        if(value1 <= value2) {
            lower = value1;
            upper = value2;
        } else {
            lower = value2;
            upper = value1;
        }
        List<Long> list = searchForPrimeNumbers.getPrimeNumberList(lower, upper);
        return list;
    }
}