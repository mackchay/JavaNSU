package ru.nsu.ccfit.haskov.stackCalculatorException;

public class StackPopException extends StackCalculatorException{
    public StackPopException() {
        super("Can't pop from empty stack.");
    }
    public StackPopException(String message) {
        super(message);
    }
}
