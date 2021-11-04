package org.example;

public class Cycle  implements SquarableObject {
    private double r;

    public Cycle(double r) {
        this.r = r;
    }

    @Override
    public double calculateSquare() {
        return Math.PI * r * r;
    }
}
