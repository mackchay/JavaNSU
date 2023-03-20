package ru.nsu.ccfit.haskov.operators.operations;

import ru.nsu.ccfit.haskov.executionContext.ExecutionContext;
import ru.nsu.ccfit.haskov.operators.operator.Operator;
import ru.nsu.ccfit.haskov.stackCalculatorException.SqrtException;

import static java.lang.Math.sqrt;

public class Sqrt implements Operator {

    public void execute(ExecutionContext executionContext) {
        double num = executionContext.popFromStack();
        if (num < 0) {
            throw new SqrtException("The root of a negative number '" + num + "'");
        }
        executionContext.pushInStack(sqrt(num));
    }
}
