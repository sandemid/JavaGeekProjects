package ru.sandem.java4.lesson1.Fruits;

public abstract class Fruit {
    private String name;
    private double weight;

    public Fruit(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }
}
