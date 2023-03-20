package ru.nsu.ccfit.haskov.main;

import ru.nsu.ccfit.haskov.calculator.StackCalculator;

public class Main {
    public static void main(String[] args) {
        StackCalculator stackCalculator = new StackCalculator("example.txt");
        stackCalculator.run();
    }
}