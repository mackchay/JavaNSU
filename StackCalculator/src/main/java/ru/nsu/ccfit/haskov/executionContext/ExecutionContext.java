package ru.nsu.ccfit.haskov.executionContext;

import ru.nsu.ccfit.haskov.stackCalculatorException.StackPopException;

import java.util.*;

public class ExecutionContext {
    private final Map<String, Double> map;
    private final Stack<Double> stack;
    private String[] inputData;

    public ExecutionContext() {
        map = new HashMap<>();
        stack = new Stack<>();
    }
    public void addToList(String string, Double number) {
        map.put(string, number);
    }

    public void pushInStack(Double number) {
        stack.add(number);
    }

    public Double popFromStack() {
        try {
            return stack.pop();
        }
        catch (EmptyStackException e) {
            throw new StackPopException("Operation stack is empty");
        }
    }

    public Double getFromList(String string) {
        return map.get(string);
    }

    public void setInputData(String [] inputData) {
        this.inputData = inputData;
    }

    public String [] getInputData() {
        return inputData;
    }
}
