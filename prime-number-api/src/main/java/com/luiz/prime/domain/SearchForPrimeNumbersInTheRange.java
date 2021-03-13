
package com.luiz.prime.domain;

import java.util.List;

/**
 * PrimeNumbersInTheRange
 */
public interface SearchForPrimeNumbersInTheRange {

    // números naturais maiores do que 1 que possuem somente dois divisores, ou seja, são divisíveis por 1 e por ele mesmo.
    public List<Long> getPrimeNumberList(Long lower, Long upper);
}