package ru.nsu.ccfit.haskov.operators.operations;

import ru.nsu.ccfit.haskov.executionContext.ExecutionContext;
import ru.nsu.ccfit.haskov.operators.operator.Operator;

public class Comment implements Operator {
    String commentData = "";
    @Override
    public void execute(ExecutionContext executionContext) {
        String [] commentArray = executionContext.getInputData();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < commentArray.length; i++) {
            commentData = stringBuilder.append(commentArray[i]).append(" ").toString();
        }
    }
}
