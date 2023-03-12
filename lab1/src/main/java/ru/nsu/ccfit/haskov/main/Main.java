package ru.nsu.ccfit.haskov.main;

import ru.nsu.ccfit.haskov.calculator.StackCalculator;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {
        StackCalculator stackCalculator = new StackCalculator("../calculator/example.txt");
        stackCalculator.run();
    }
}