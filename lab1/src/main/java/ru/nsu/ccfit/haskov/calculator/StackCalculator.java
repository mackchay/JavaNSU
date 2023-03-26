package ru.nsu.ccfit.haskov.calculator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.ccfit.haskov.executionContext.ExecutionContext;
import ru.nsu.ccfit.haskov.operators.operator.Operator;
import ru.nsu.ccfit.haskov.operators.operator.OperatorFactory;
import ru.nsu.ccfit.haskov.stackCalculatorException.StackCalculatorException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Objects;


public class StackCalculator {


    private final static Logger log = LogManager.getLogger(StackCalculator.class.getName());


    private BufferedReader reader;
    private String sourceName = "System.in";
    final private ExecutionContext executionContext = new ExecutionContext();
    final private OperatorFactory operatorFactory = new OperatorFactory();

    private void errorInLine(int line) {
        System.err.println("Error input in " + line + " line of " + sourceName);
    }

    //If we didn't get any String, StackCalculator read from System.in
    public StackCalculator() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public StackCalculator(String fileName) {
        try {
            log.info("Initializing reader...");
            reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(StackCalculator.
                    class.getResourceAsStream(fileName))));
            log.info("Initializing done.");
        } catch (NullPointerException e) {
            log.info("Can't initialize reader. Can't find source file " + fileName);
            System.err.println("Can't find source file " + fileName);
            System.exit(1);
        }
        sourceName = fileName;
    }

    public void run() {
        log.info("Function 'run' was called.");
        String string;
        int line = 0;
        try {
            while ((string = reader.readLine()) != null) {
                String[] inputData = new String[0];
                String[] commandArgs;
                if (string.equals("")) {
                    break;
                }
                try {
                    line++;
                    inputData = string.split(" ");
                    commandArgs = Arrays.copyOfRange(inputData, 1, inputData.length);
                    log.info("Getting operator from file...");
                    Operator operator = operatorFactory.createOperation(inputData[0]);

                    log.info(operator.getClass().getName() + " was created.");
                    executionContext.setInputData(commandArgs);

                    log.info("Setting inputData in " + operator.getClass().getName());
                    operator.execute(executionContext);

                    log.info(operator.getClass().getName() + " ran successfully.");
                } catch (StackCalculatorException e) {
                    log.warn(e.getMessage());
                    errorInLine(line);
                    System.err.println(e.getMessage());
                } catch (InstantiationException e) {
                    log.warn("Can't construct class (abstract class, interface, primitive type) " +
                            "'" + inputData[0] + "'");
                    errorInLine(line);
                    System.err.println("Can't construct class (abstract class, interface, primitive type) " +
                            "'" + inputData[0] + "'");
                } catch (IllegalAccessException e) {
                    log.warn("Can't call constructor of class " +
                            "'" + inputData[0] + "'");
                    errorInLine(line);
                    System.err.println("Can't call constructor of class " +
                            "'" + inputData[0] + "'");
                } catch (InvocationTargetException e) {
                    log.warn("Thrown exception in class " +
                            "'" + inputData[0] + "'");
                    errorInLine(line);
                    System.err.println("Thrown exception in class " +
                            "'" + inputData[0] + "'");
                    System.err.println(e.getMessage());
                } catch (NoSuchMethodException e) {
                    log.warn("Constructor of class doesn't exist" +
                            "'" + inputData[0] + "'");
                    errorInLine(line);
                    System.err.println("Constructor of class doesn't exist" +
                            "'" + inputData[0] + "'");
                } catch (NullPointerException e) {
                    log.warn("Class " +
                            "'" + inputData[0] + "' is not Operator.");
                    errorInLine(line);
                    System.err.println("Class " +
                            "'" + inputData[0] + "' is not Operator.");
                }
            }
        } catch (IOException e) {
            log.warn("Can't read file " + sourceName);
            System.err.println("Can't read file " + sourceName);
        }
        log.info("Function 'run' ran successfully.");
    }
}