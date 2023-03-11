package ru.nsu.ccfit.haskov.operators.operator;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class OperatorFactory {
    Map<String, Class<?>> operatorMap = new HashMap<>();
    String filename = "factoryConfig.txt";

    public OperatorFactory() throws IOException, ClassNotFoundException {
        InputStream inputStream = OperatorFactory.class.getResourceAsStream(filename);
        assert inputStream != null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String fileData;
        while ((fileData = reader.readLine()) != null) {
            String[] operatorData = fileData.split(" ");
            String operatorName = operatorData[0];
            Class<?> operatorClass = Class.forName(operatorData[1]);
            operatorMap.put(operatorName, operatorClass);
        }
    }

    public Operator createOperation(String operatorName) throws InstantiationException, IllegalAccessException,
            NoSuchMethodException, InvocationTargetException {
        Class<?> operatorClass = operatorMap.get(operatorName);
        return (Operator) operatorClass.getDeclaredConstructor().newInstance();
    }
}