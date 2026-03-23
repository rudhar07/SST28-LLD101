package com.example.pen;

public class App {
    public static void main(String[] args) {
        System.out.println("=== Pen Operations Simulation ===\n");

        Ink blueInk = new Ink("Blue", 10); 
        Refill blueRefill = new Refill(blueInk, 0.5);

        Pen<Refill> myPen = new BallPen(blueRefill);

        // 1. Try to write 
        System.out.println("Attempting to write...");
        myPen.write("Hello World");
        
        // 2. Start 
        System.out.println("\nStarting pen...");
        myPen.start();

        // 3. Write
        System.out.println("\nWriting...");
        myPen.write("Hello World!");

        System.out.println("\nExhausting ink...");
        myPen.write("This is a very long sentence carefully designed to use up the remaining ink inside the cartridge completely.");
        myPen.write("Can I still write?");

        // 5. Close pen
        System.out.println("\nClosing pen...");
        myPen.close();

        // 6. Refill operations
        System.out.println("\nRefilling pen...");
        Ink redInk = new Ink("Red", 100);
        Refill redRefill = new Refill(redInk, 0.7);
        myPen.refill(redRefill);

        // 7. Write again
        System.out.println("\nWriting after refill...");
        myPen.start();
        myPen.write("Wow! The new refill works perfectly.");
        myPen.close();

        System.out.println("\n=== Simulation Complete ===");
    }
}
