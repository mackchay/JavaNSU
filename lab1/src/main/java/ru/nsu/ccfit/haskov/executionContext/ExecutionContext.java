package ru.nsu.ccfit.haskov.executionContext;

import java.util.Map;
import java.util.Stack;

public class ExecutionContext {
    Map<String, Double> list;
    Stack<Double> stack;

    public void addToList(String string, Double number) {
        list.put(string, number);
    }

    public void pushInStack(Double number) {
        stack.add(number);
    }

    public double popFromStack() {
        return stack.pop();
    }

    public double getFromList(String string) {
        return list.get(string);
    }
}
