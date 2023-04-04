package ru.nsu.ccfit.haskov.stackCalculatorException;

public class SqrtException extends StackCalculatorException{
    public SqrtException() {super("Sqrt of negative number.");}
    public SqrtException(String message) {
        super(message);
    }
}
