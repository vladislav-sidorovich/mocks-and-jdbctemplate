package org.example;

public class SquareCalculator {

    private SquareCache cache;

    public double calculate(SquarableObject squarableObject) {
        if (squarableObject == null) {
            return 0;
        }

        if (cache.inCache(squarableObject)) {
            return cache.get(squarableObject);
        }

        try {
            double square = squarableObject.calculateSquare();
            cache.addToCache(squarableObject, square);

            return square;
        } catch (RuntimeException ignore) {
            return 0;
        }
    }

    public void setCache(SquareCache cache) {
        this.cache = cache;
    }
}
