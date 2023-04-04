package ru.nsu.ccfit.haskov.stackCalculatorException;


import ru.nsu.ccfit.haskov.calculator.StackCalculator;

public abstract class StackCalculatorException extends RuntimeException {
    public StackCalculatorException() {
        super();
    }
    public StackCalculatorException(String message) {
        super(message);
    }
}
