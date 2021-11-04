package org.example;

public class Rectangle implements SquarableObject {
    private int x;
    private int y;

    public Rectangle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double calculateSquare() {
        return x * y;
    }
}
