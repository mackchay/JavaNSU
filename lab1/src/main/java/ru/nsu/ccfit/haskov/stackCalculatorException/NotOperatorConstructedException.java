package ru.nsu.ccfit.haskov.stackCalculatorException;

import ru.nsu.ccfit.haskov.stackCalculatorException.StackCalculatorException;

public class NotOperatorConstructedException extends StackCalculatorException {
    public NotOperatorConstructedException() {
        super();
    }
    public NotOperatorConstructedException(String message) {
        super(message);
    }
}
