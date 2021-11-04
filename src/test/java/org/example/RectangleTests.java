package org.example;

import org.junit.Assert;
import org.junit.Test;

public class RectangleTests {
    @Test
    public void square() {
        Rectangle rectangle = new Rectangle(4, 7);
        Assert.assertEquals(28d, rectangle.calculateSquare(), 0);
    }
}
