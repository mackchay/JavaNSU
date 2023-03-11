package ru.nsu.ccfit.haskov.operators.operations;

import ru.nsu.ccfit.haskov.executionContext.ExecutionContext;
import ru.nsu.ccfit.haskov.operators.operator.Operator;

public class Define implements Operator {
    @Override
    public void execute(ExecutionContext executionContext) {
        String[] data = executionContext.getInputData();
        executionContext.addToList(data[1], Double.parseDouble(data[2]));
    }
}
