package ru.nsu.ccfit.haskov.operators.pop;

import ru.nsu.ccfit.haskov.executionContext.ExecutionContext;
import ru.nsu.ccfit.haskov.operators.operator.Operator;

public class Pop implements Operator {
    @Override
    public void execute(ExecutionContext executionContext) {
        executionContext.popFromStack();
    }
}
