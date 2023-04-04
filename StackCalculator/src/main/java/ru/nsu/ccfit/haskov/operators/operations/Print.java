package ru.nsu.ccfit.haskov.operators.operations;

import ru.nsu.ccfit.haskov.executionContext.ExecutionContext;
import ru.nsu.ccfit.haskov.operators.operator.Operator;

public class Print implements Operator {
    @Override
    public void execute(ExecutionContext executionContext) {
        System.out.println(executionContext.popFromStack());
    }
}
