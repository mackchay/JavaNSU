package ru.nsu.ccfit.haskov.calculator;


import ru.nsu.ccfit.haskov.executionContext.ExecutionContext;
import ru.nsu.ccfit.haskov.operators.operator.Operator;
import ru.nsu.ccfit.haskov.operators.operator.OperatorFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class StackCalculator {
    private BufferedReader reader;
    private String sourceName = "System.in";
    final private ExecutionContext executionContext = new ExecutionContext();
    ;
    final private OperatorFactory operatorFactory = new OperatorFactory();

    StackCalculator() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public StackCalculator(String fileName) {
        try {
            reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(StackCalculator.
                    class.getResourceAsStream(fileName))));
        } catch (Exception e) {
            System.err.println("Can't find source file " + fileName);
            System.exit(1);
        }
        sourceName = fileName;
    }

    public void run() {
        String string;
        int line = 0;
        try {
            while ((string = reader.readLine()) != null) {
                try {
                    line++;
                    String[] inputData = string.split(" ");
                    Operator operator = operatorFactory.createOperation(inputData[0]);
                    executionContext.setInputData(inputData);
                    operator.execute(executionContext);
                } catch (Exception e) {
                    System.err.println("Error input in " + line + " line of " + sourceName);
                    System.err.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Can't read file " + sourceName);
        }
    }
}