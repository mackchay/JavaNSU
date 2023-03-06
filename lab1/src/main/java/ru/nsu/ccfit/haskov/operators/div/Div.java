package ru.nsu.ccfit.haskov.operators.div;

import ru.nsu.ccfit.haskov.executionContext.ExecutionContext;
import ru.nsu.ccfit.haskov.operators.operator.Operator;

public class Div implements Operator {
    public void execute(ExecutionContext executionContext) {
        Double num1 = executionContext.popFromStack();
        Double num2 = executionContext.popFromStack();
        Double result = num1 / num2;
        executionContext.pushInStack(result);
    }
}
