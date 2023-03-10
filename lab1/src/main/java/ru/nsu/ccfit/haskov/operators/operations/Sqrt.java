package ru.nsu.ccfit.haskov.operators.operations;

import ru.nsu.ccfit.haskov.executionContext.ExecutionContext;
import ru.nsu.ccfit.haskov.operators.operator.Operator;

import static java.lang.Math.sqrt;

public class Sqrt implements Operator {

    public void execute(ExecutionContext executionContext) {
        double num = executionContext.popFromStack();
        executionContext.pushInStack(sqrt(num));
    }
}
