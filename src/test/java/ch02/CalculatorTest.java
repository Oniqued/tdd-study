package ch02;

import org.example.ch02.Calculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    void plus() {
        int sum = Calculator.plus(1, 2);
        Assertions.assertEquals(3, sum);
        Assertions.assertEquals(5, Calculator.plus(4,1));
    }
}
