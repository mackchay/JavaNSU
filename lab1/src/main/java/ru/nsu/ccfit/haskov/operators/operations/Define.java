package ru.nsu.ccfit.haskov.operators.operations;

import ru.nsu.ccfit.haskov.executionContext.ExecutionContext;
import ru.nsu.ccfit.haskov.operators.operator.Operator;
import ru.nsu.ccfit.haskov.stackCalculatorException.DefineFailException;

public class Define implements Operator {
    @Override
    public void execute(ExecutionContext executionContext) {
        String[] data = executionContext.getInputData();
        try {
            Double.parseDouble(data[0]);
            throw new DefineFailException();
        }
        catch (NumberFormatException e) {
            executionContext.addToList(data[0], Double.parseDouble(data[1]));
        }
    }
}
