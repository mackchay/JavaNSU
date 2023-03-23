package ru.nsu.ccfit.haskov.operators.operator;

import ru.nsu.ccfit.haskov.stackCalculatorException.NotOperatorConstructedException;
import ru.nsu.ccfit.haskov.stackCalculatorException.StackCalculatorException;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class OperatorFactory {

    private final Map<String, Class<?>> operatorMap = new HashMap<>();

    public OperatorFactory() {
        String filename = "factoryConfig.txt";
        try {
            InputStream inputStream = OperatorFactory.class.getResourceAsStream(filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)));
            String fileData;
            while ((fileData = reader.readLine()) != null) {
                String[] operatorData = fileData.split(" ");
                String operatorName = operatorData[0];
                Class<?> operatorClass = Class.forName(operatorData[1]);
                if (Operator.class.isAssignableFrom(operatorClass)) {
                    operatorMap.put(operatorName, operatorClass);
                } else {
                    throw new NotOperatorConstructedException("Class '" + operatorData[1] +
                            "' is not extended from 'Operator'.");
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Incorrect input data format. Expected 2 words instead of 1.");
        } catch (ClassNotFoundException e) {
            System.err.println("Class " + e.getMessage() + " is not found.");
        } catch (StackCalculatorException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println("Can't read file " + filename);
            System.exit(1);
        } catch (NullPointerException e) {
            System.err.println("Can't find file");
            System.exit(1);
        }
    }

    public Operator createOperation(String operatorName) throws InstantiationException, IllegalAccessException,
            NoSuchMethodException, InvocationTargetException {
        Class<?> operatorClass = operatorMap.get(operatorName);
        return (Operator) operatorClass.getConstructor().newInstance();
    }
}