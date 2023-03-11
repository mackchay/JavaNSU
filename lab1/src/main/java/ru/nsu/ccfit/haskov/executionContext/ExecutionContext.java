package ru.nsu.ccfit.haskov.executionContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ExecutionContext {
    Map<String, Double> map;
    Stack<Double> stack;
    String [] inputData;

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

    public double popFromStack() {
        return stack.pop();
    }

    public double getFromList(String string) {
        return map.get(string);
    }

    public void setInputData(String [] inputData) {
        this.inputData = inputData;
    }

    public String [] getInputData() {
        return inputData;
    }
}
