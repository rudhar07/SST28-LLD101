package com.example.pen;

public class BallPen extends Pen<Refill> {
    private Refill refill;

    public BallPen(Refill refill) {
        super();
        this.refill = refill;
    }

    @Override
    public void write(String text) {
        if (!isOpen) {
            System.out.println("Cannot write. Please start() the pen first.");
            return;
        }
        if (refill == null || refill.getInk().getLevel() <= 0) {
            System.out.println("Cannot write. The pen is out of ink!");
            return;
        }

        System.out.println("Writing with BallPen: '" + text + "' (Ink Color: " + refill.getInk().getColor() + ")");
        refill.getInk().reduceLevel(Math.max(1, text.length() / 2));
    }

    @Override
    public void refill(Refill newRefill) {
        this.refill = newRefill;
        System.out.println("BallPen successfully refilled with a new " + newRefill.getInk().getColor() + " refill.");
    }
}
