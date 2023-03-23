package ru.nsu.ccfit.haskov.stackCalculatorException;

public class DefineFailException extends StackCalculatorException{
    public DefineFailException() {
        super("Failed define operation.");
    }

    public DefineFailException(String message) {
        super(message);
    }
}
