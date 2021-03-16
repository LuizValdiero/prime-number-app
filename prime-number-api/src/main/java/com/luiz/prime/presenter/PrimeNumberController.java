package com.luiz.prime.presenter;

import java.util.List;

import javax.validation.Valid;

import com.luiz.prime.domain.CalculatePrimeNumbers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PrimeNumberController {
    @Autowired
    private CalculatePrimeNumbers calculator;
    
    @GetMapping("")
    public List<Long> getPrimeNumberList(@RequestParam @Valid Long value1, @RequestParam @Valid Long value2) {
        List<Long> list = this.calculator.getPrimeNumbers(value1, value2);
        return list;
    }
}