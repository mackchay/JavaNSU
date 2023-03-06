package ru.nsu.ccfit.haskov.operators.push;

import ru.nsu.ccfit.haskov.executionContext.ExecutionContext;
import ru.nsu.ccfit.haskov.operators.operator.Operator;

public class Push implements Operator {
    String var;
    @Override
    public void execute(ExecutionContext executionContext) {
        double number = executionContext.getFromList(var);
        executionContext.pushInStack(number);
    }
}
