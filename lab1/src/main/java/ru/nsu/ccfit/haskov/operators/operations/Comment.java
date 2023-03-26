package ru.nsu.ccfit.haskov.operators.operations;

import ru.nsu.ccfit.haskov.executionContext.ExecutionContext;
import ru.nsu.ccfit.haskov.operators.operator.Operator;

public class Comment implements Operator {
    public StringBuilder commentData = new StringBuilder();
    @Override
    public void execute(ExecutionContext executionContext) {
        String [] commentArray = executionContext.getInputData();

        for (String s : commentArray) {
            commentData.append(s).append(" ");
        }
    }
}
