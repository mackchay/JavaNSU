package ru.nsu.ccfit.haskov.operators.operations;

import ru.nsu.ccfit.haskov.executionContext.ExecutionContext;
import ru.nsu.ccfit.haskov.operators.operator.Operator;

import java.util.Objects;

public class Push implements Operator {

    @Override
    public void execute(ExecutionContext executionContext) {
        String var = executionContext.getInputData()[1];
        Double number = executionContext.getFromList(var);
        if (Objects.isNull(number)) {
            executionContext.pushInStack(Double.parseDouble(var));
        } else {
            executionContext.pushInStack(number);
        }
    }
}
