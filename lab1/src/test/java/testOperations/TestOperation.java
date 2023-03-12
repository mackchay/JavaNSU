package testOperations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.ccfit.haskov.executionContext.ExecutionContext;
import ru.nsu.ccfit.haskov.operators.operations.*;

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
    public void testDefine() {
        Define define = new Define();
        String name = "name";
        String value = "31.256";
        String [] inputData = {"Define", name, value};
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
        String [] inputData = {"Push", "name"};
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
}
