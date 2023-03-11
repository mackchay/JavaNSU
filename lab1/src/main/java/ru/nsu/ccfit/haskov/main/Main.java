package ru.nsu.ccfit.haskov.main;

import ru.nsu.ccfit.haskov.calculator.StackCalculator;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws IOException,
            ClassNotFoundException, InstantiationException, IllegalAccessException,
            InvocationTargetException, NoSuchMethodException {
        StackCalculator stackCalculator = new StackCalculator("../calculator/example.txt");
        stackCalculator.run();
    }
}