package ru.nsu.ccfit.haskov.executionContext;

import ru.nsu.ccfit.haskov.stackCalculatorException.StackException;
import ru.nsu.ccfit.haskov.stackCalculatorException.VariableListException;

import java.util.*;

public class ExecutionContext {
    Map<String, Double> map;
    Stack<Double> stack;
    String[] inputData;

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
        try {
            return stack.pop();
        }
        catch (EmptyStackException e) {
            throw new StackException("Operation stack is empty");
        }
    }

    public double getFromList(String string) {
        Double result = map.get(Objects.requireNonNull(string));
        if (Objects.nonNull(result)) {
            return result;
        }
        else {
            throw new VariableListException("Cannot find variable '" + string + "' in variable list.");
        }
    }

    public void setInputData(String [] inputData) {
        this.inputData = inputData;
    }

    public String [] getInputData() {
        return inputData;
    }
}
