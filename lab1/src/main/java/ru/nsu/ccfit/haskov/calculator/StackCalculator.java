package ru.nsu.ccfit.haskov.calculator;


import ru.nsu.ccfit.haskov.executionContext.ExecutionContext;
import ru.nsu.ccfit.haskov.operators.operator.Operator;
import ru.nsu.ccfit.haskov.operators.operator.OperatorFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class StackCalculator {
    BufferedReader reader;
    ExecutionContext executionContext;
    OperatorFactory operatorFactory;

    StackCalculator() throws IOException, ClassNotFoundException {
        operatorFactory = new OperatorFactory();
        executionContext = new ExecutionContext();
        reader = new BufferedReader(new InputStreamReader(System.in));
    }
    public StackCalculator(String fileName) throws IOException, ClassNotFoundException {
        operatorFactory = new OperatorFactory();
        executionContext = new ExecutionContext();
        reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(StackCalculator.
                class.getResourceAsStream(fileName))));
    }

    public void run() throws IOException, InstantiationException, IllegalAccessException,
            InvocationTargetException, NoSuchMethodException {
        String string;
        while ((string = reader.readLine()) != null) {
            String [] inputData = string.split(" ");
            Operator operator = operatorFactory.createOperation(inputData[0]);
            executionContext.setInputData(inputData);
            operator.execute(executionContext);
        }
    }
}
