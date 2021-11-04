package org.example;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SquareCalculatorTests {
    private SquareCalculator calculator;

    @Mock
    private SquarableObject squarableObject;
    @Mock
    private SquareCache cache;

    @Before
    public void setUp() throws Exception {
        calculator = new SquareCalculator();
        calculator.setCache(cache);
    }

    @Test
    public void cache() {
        when(cache.inCache(squarableObject)).thenReturn(true);
        when(cache.get(squarableObject)).thenReturn(100d);

        Assert.assertEquals(100d, calculator.calculate(squarableObject), 0);

        verify(cache).inCache(squarableObject);
        verify(squarableObject, never()).calculateSquare();
    }

    @Test
    public void squareCalculation() {
        when(squarableObject.calculateSquare()).thenReturn(10d);

        Assert.assertEquals(10d, calculator.calculate(squarableObject), 0);

        verify(squarableObject, times(1)).calculateSquare();
    }

    @Test
    public void nullCase() {
        Assert.assertEquals(0.0, calculator.calculate(null), 0);
    }

    @Test
    public void exception() {
        when(squarableObject.calculateSquare()).thenThrow(new RuntimeException("no square"));

        Assert.assertEquals(0, calculator.calculate(squarableObject), 0);

        verify(squarableObject, times(1)).calculateSquare();
    }
}
