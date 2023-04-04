package ru.nsu.ccfit.haskov.stackCalculatorException;

public class NotOperatorConstructedException extends StackCalculatorException {
    public NotOperatorConstructedException() {
        super("Class is not Operator.");
    }
    public NotOperatorConstructedException(String message) {
        super(message);
    }
}
