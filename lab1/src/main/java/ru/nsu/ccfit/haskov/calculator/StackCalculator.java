package ru.nsu.ccfit.haskov.calculator;


import ru.nsu.ccfit.haskov.executionContext.ExecutionContext;
import ru.nsu.ccfit.haskov.operators.operator.Operator;
import ru.nsu.ccfit.haskov.operators.operator.OperatorFactory;
import ru.nsu.ccfit.haskov.stackCalculatorException.StackCalculatorException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class StackCalculator {
    private BufferedReader reader;
    private String sourceName = "System.in";
    final private ExecutionContext executionContext = new ExecutionContext();
    final private OperatorFactory operatorFactory = new OperatorFactory();

    public StackCalculator() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public StackCalculator(String fileName) {
        try {
            reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(StackCalculator.
                    class.getResourceAsStream(fileName))));
        } catch (NullPointerException e) {
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
                String[] inputData = new String[0];
                try {
                    line++;
                    inputData = string.split(" ");
                    Operator operator = operatorFactory.createOperation(inputData[0]);
                    executionContext.setInputData(inputData);
                    operator.execute(executionContext);
                } catch (StackCalculatorException e) {
                    System.err.println("Error input in " + line + " line of " + sourceName);
                    System.err.println(e.getMessage());
                } catch (InstantiationException e) {
                    System.err.println("Error input in " + line + " line of " + sourceName);
                    System.err.println("Can't construct class (abstract class, interface, primitive type) " +
                            "'" + inputData[0] + "'");
                } catch (IllegalAccessException e) {
                    System.err.println("Error input in " + line + " line of " + sourceName);
                    System.err.println("Can't call constructor of class " +
                            "'" + inputData[0] + "'");
                } catch (InvocationTargetException e) {
                    System.err.println("Error input in " + line + " line of " + sourceName);
                    System.err.println("Thrown exception in class " +
                            "'" + inputData[0] + "'");
                    System.err.println(e.getMessage());
                } catch (NoSuchMethodException e) {
                    System.err.println("Error input in " + line + " line of " + sourceName);
                    System.err.println("Constructor of class doesn't exist" +
                            "'" + inputData[0] + "'");
                } catch (NullPointerException e) {
                    System.err.println("Error input in " + line + " line of " + sourceName);
                    System.err.println("Class " +
                            "'" + inputData[0] + "' is not Operator.");
                }
            }
        } catch (IOException e) {
            System.err.println("Can't read file " + sourceName);
        }
    }
}