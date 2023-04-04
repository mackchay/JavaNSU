package ru.nsu.ccfit.haskov.stackCalculatorException;

public class DivisionZeroException extends StackCalculatorException {
    public DivisionZeroException() {
        super("Division on zero.");
    }
    public DivisionZeroException(String message) {
        super(message);
    }
}
