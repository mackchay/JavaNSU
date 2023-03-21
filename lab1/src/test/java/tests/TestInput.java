package tests;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import ru.nsu.ccfit.haskov.calculator.StackCalculator;
import ru.nsu.ccfit.haskov.operators.operator.OperatorFactory;

import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TestInput {
    @Test
    public void TestNoClassFound() {
        OperatorFactory operatorFactory = new OperatorFactory();
        final Exception e = assertThrows(NullPointerException.class, ()
    -> operatorFactory.createOperation("GarbageInfoLMAO"));
        assertThat(e.getMessage(), containsString("Cannot invoke"));
    }

    @Test
    public void TestExample1() {
        StackCalculator stackCalculator = new StackCalculator("testExample1.txt");
        PrintStream stream = mock(PrintStream.class);
        ArgumentCaptor<Double> captor = ArgumentCaptor.forClass(Double.class);
        System.setOut(stream);

        stackCalculator.run();
        Double params = 2.0;

        verify(stream).println(captor.capture());
        assertEquals(params, captor.getValue());
    }


}
