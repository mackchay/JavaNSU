package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import ru.nsu.ccfit.haskov.executionContext.ExecutionContext;
import ru.nsu.ccfit.haskov.operators.operations.*;
import ru.nsu.ccfit.haskov.stackCalculatorException.DivisionZeroException;
import ru.nsu.ccfit.haskov.stackCalculatorException.SqrtException;
import ru.nsu.ccfit.haskov.stackCalculatorException.StackException;

import java.io.PrintStream;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TestOperation {
    ExecutionContext executionContext = new ExecutionContext();

    @Test
    public void testSum() {
        Sum sum = new Sum();
        double a = 31.256, b = -150.00666;
        executionContext.pushInStack(a);
        executionContext.pushInStack(b);
        sum.execute(executionContext);
        double actual = executionContext.popFromStack();
        double expected = -118.75066000000001;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testSub() {
        Sub sub = new Sub();
        double a = 31.256, b = -150.00666;
        executionContext.pushInStack(a);
        executionContext.pushInStack(b);
        sub.execute(executionContext);
        double actual = executionContext.popFromStack();
        double expected = -181.26266;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testMul() {
        Mul mul = new Mul();
        double a = 31.256, b = -150.00666;
        executionContext.pushInStack(a);
        executionContext.pushInStack(b);
        mul.execute(executionContext);
        double actual = executionContext.popFromStack();
        double expected = -4688.60816496;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testDiv() {
        Div div = new Div();
        double a = 31.256, b = -150.00666;
        executionContext.pushInStack(a);
        executionContext.pushInStack(b);
        div.execute(executionContext);
        double actual = executionContext.popFromStack();
        double expected = -4.7992916560020475;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testDivException() {
        Div div = new Div();
        double a = 31.256;
        double b = 0.0;
        executionContext.pushInStack(b);
        executionContext.pushInStack(a);
        final Exception e = assertThrows(DivisionZeroException.class, ()
                -> div.execute(executionContext));

        assertThat(e.getMessage(), containsString("Division on zero"));

    }

    @Test
    public void testSqrt() {
        Sqrt sqrt = new Sqrt();
        double a = 31.256;
        executionContext.pushInStack(a);
        sqrt.execute(executionContext);
        double actual = executionContext.popFromStack();
        double expected = 5.590706574307044;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testSqrtException() {
        Sqrt sqrt = new Sqrt();
        double a = -31.256;
        executionContext.pushInStack(a);
        final Exception e = assertThrows(SqrtException.class, ()
                -> sqrt.execute(executionContext));
        assertThat(e.getMessage(), containsString("The root of a negative number '-31.256'"));
    }

    @Test
    public void testDefine() {
        Define define = new Define();
        String name = "name";
        String value = "31.256";
        String[] inputData = {"Define", name, value};
        executionContext.setInputData(inputData);
        define.execute(executionContext);
        double actual = executionContext.getFromList(name);
        double expected = 31.256;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testPush() {
        Push push = new Push();
        String name = "name";
        double value = 31.256;
        executionContext.addToList(name, value);
        String[] inputData = {"Push", "name"};
        executionContext.setInputData(inputData);
        push.execute(executionContext);
        double actual = executionContext.popFromStack();
        Assertions.assertEquals(value, actual);
    }

    @Test
    public void testPop() {
        Pop pop = new Pop();
        double value1 = 31.256;
        double value2 = 1.0;
        executionContext.pushInStack(value2);
        executionContext.pushInStack(value1);
        pop.execute(executionContext);
        double actual = executionContext.popFromStack();
        Assertions.assertEquals(value2, actual);
    }

    @Test
    public void testPopException() {
        Pop pop = new Pop();
        final Exception e = assertThrows(StackException.class, ()
                -> pop.execute(executionContext));
        assertThat(e.getMessage(), containsString("Operation stack is empty"));
    }


    @Test
    public void testComment() {
        Comment comment = new Comment();
        String[] inputData = {"#", "this", "is", "comment"};
        executionContext.setInputData(inputData);

        PrintStream stream = mock(PrintStream.class);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        System.setOut(stream);

        comment.execute(executionContext);
        String expected = "this is comment ";

        verify(stream).println(captor.capture());
        assertEquals(expected, captor.getValue());
    }
}
