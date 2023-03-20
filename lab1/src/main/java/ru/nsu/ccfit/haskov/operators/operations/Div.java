package ru.nsu.ccfit.haskov.operators.operations;

import ru.nsu.ccfit.haskov.executionContext.ExecutionContext;
import ru.nsu.ccfit.haskov.operators.operator.Operator;
import ru.nsu.ccfit.haskov.stackCalculatorException.DivisionZeroException;

public class Div implements Operator {
    public void execute(ExecutionContext executionContext) {
        Double num1 = executionContext.popFromStack();
        Double num2 = executionContext.popFromStack();
        if (num2.equals(0.0)) {
            throw new DivisionZeroException("Division on zero.");
        }
        Double result = num1 / num2;
        executionContext.pushInStack(result);
    }
}
