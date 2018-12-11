package ru.sandem.java2.lesson3;

public interface StackInterface {

    void push(int value);

    int pop();

    int peek();

    boolean isEmpty();

    boolean isFull();

    int getSize();

    void display();

}
