package com.example.elevator;

public class WeightSensor {
    private double currentWeight;
    private double weightLimit;

    public WeightSensor(double weightLimit) {
        this.currentWeight = 0;
        this.weightLimit = weightLimit;
    }

    public void setCurrentWeight(double weight) {
        this.currentWeight = weight;
    }

    public void addWeight(double weight) {
        this.currentWeight += weight;
    }

    public void removeWeight(double weight) {
        this.currentWeight -= weight;
        if (this.currentWeight < 0) this.currentWeight = 0;
    }

    public boolean isOverloaded() {
        return currentWeight >= weightLimit;
    }

    public double getCurrentWeight() { return currentWeight; }
    public double getWeightLimit() { return weightLimit; }

    @Override
    public String toString() {
        return currentWeight + "/" + weightLimit + " kg" + (isOverloaded() ? " [OVERLOADED]" : "");
    }
}
