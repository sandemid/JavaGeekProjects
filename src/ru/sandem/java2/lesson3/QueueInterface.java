package ru.sandem.java2.lesson3;

public interface QueueInterface {

    void addRight(int value);
    int removeLeft();
    boolean isFull();
    boolean isEmpty();
    int getSize();
    void display();

}
