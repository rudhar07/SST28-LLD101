package com.example.pen;

public class Ink {
    private String color;
    private int level; // 0 to 100

    public Ink(String color, int level) {
        this.color = color;
        this.level = Math.max(0, Math.min(100, level));
    }

    public String getColor() { return color; }
    public int getLevel() { return level; }

    public void reduceLevel(int amount) {
        this.level = Math.max(0, this.level - amount);
    }
}
