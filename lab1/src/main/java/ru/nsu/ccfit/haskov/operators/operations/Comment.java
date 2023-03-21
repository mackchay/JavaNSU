package ru.nsu.ccfit.haskov.operators.operations;

import ru.nsu.ccfit.haskov.executionContext.ExecutionContext;
import ru.nsu.ccfit.haskov.operators.operator.Operator;

public class Comment implements Operator {
    @Override
    public void execute(ExecutionContext executionContext) {
        String [] commentArray = executionContext.getInputData();
        StringBuilder commentString = new StringBuilder();
        for (int i = 1; i < commentArray.length; i++) {
            commentString.append(commentArray[i]).append(" ");
        }
        System.out.println(commentString.toString());
    }
}
