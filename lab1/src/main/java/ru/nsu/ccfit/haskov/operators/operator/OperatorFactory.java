package ru.nsu.ccfit.haskov.operators.operator;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class OperatorFactory {
    Map<String, Class<?>> operatorMap = new HashMap<>();
    String filename = "factoryConfig.txt";

    public OperatorFactory() throws IOException, ClassNotFoundException,
            InstantiationException, IllegalAccessException {
        InputStream inputStream = OperatorFactory.class.getResourceAsStream(filename);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String operatorName;
        while ((operatorName = reader.readLine()) != null) {
            Class<?> operator = Class.forName(operatorName);
            operatorMap.put(operatorName, operator);
        }
    }
}
