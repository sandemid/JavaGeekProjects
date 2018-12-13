package ru.sandem.java3.lesson1;

public class Player {

    private String name;
    private int canOvercomeBarrier;

    public Player(String name, int canOvercomeBarrier) {
        this.name = name;
        this.canOvercomeBarrier = canOvercomeBarrier;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCanOvercomeBarrier(int canOvercomeBarrier) {
        this.canOvercomeBarrier = canOvercomeBarrier;
    }

    public String getName() {
        return name;
    }

    public int getCanOvercomeBarrier() {
        return canOvercomeBarrier;
    }
}
