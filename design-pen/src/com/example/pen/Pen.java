package com.example.pen;

/**
 * T represents the refillable material type (e.g., Refill for BallPen, Ink for FountainPen).
 */
public abstract class Pen<T> {
    protected boolean isOpen;

    public Pen() {
        this.isOpen = false;
    }

    public void start() {
        if (isOpen) {
            System.out.println("Pen is already open.");
        } else {
            isOpen = true;
            System.out.println("Pen started (uncapped/clicked). Ready to write.");
        }
    }

    public void close() {
        if (!isOpen) {
            System.out.println("Pen is already closed.");
        } else {
            isOpen = false;
            System.out.println("Pen closed.");
        }
    }

    public abstract void write(String text);
    public abstract void refill(T refillItem);
}
