package org.example;

public interface SquareCache {
    boolean inCache(SquarableObject squarableObject);

    void addToCache(SquarableObject squarableObject, double square);

    double get(SquarableObject squarableObject);
}
