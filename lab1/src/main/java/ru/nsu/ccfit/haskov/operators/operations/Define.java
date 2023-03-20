package ru.nsu.ccfit.haskov.operators.operations;

import ru.nsu.ccfit.haskov.executionContext.ExecutionContext;
import ru.nsu.ccfit.haskov.operators.operator.Operator;
import ru.nsu.ccfit.haskov.stackCalculatorException.DefineFailException;

public class Define implements Operator {
    @Override
    public void execute(ExecutionContext executionContext) {
        String[] data = executionContext.getInputData();
        try {
            Double.parseDouble(data[1]);
            throw new DefineFailException("Incorrect format of variable name '" + data[1] + "'.");
        }
        catch (NumberFormatException e) {
            executionContext.addToList(data[1], Double.parseDouble(data[2]));
        }
    }
}
