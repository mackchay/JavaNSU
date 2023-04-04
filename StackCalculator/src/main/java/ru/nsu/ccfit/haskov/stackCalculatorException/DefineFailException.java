package ru.nsu.ccfit.haskov.stackCalculatorException;

public class DefineFailException extends StackCalculatorException{
    public DefineFailException() {
        super("Failed define operation. Incorrect format of variable name.");
    }

    public DefineFailException(String message) {
        super(message);
    }
}
