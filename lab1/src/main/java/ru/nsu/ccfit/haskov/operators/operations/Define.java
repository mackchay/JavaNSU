package ru.nsu.ccfit.haskov.operators.operations;

import ru.nsu.ccfit.haskov.executionContext.ExecutionContext;
import ru.nsu.ccfit.haskov.operators.operator.Operator;

public class Define implements Operator {
    String name;
    Double value;
    @Override
    public void execute(ExecutionContext executionContext) {
        executionContext.addToList(name, value);
    }
}
