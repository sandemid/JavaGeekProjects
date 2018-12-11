package ru.sandem.java2.lesson4;

public interface LinkedListInterface extends Iterable<Integer>{

    void add(int value);

    Link remove();

    boolean isEmpty();

    int getSize();

    Link search(int value);

    Link remove(int value);

    void display();

    Link getFirstElement();

    void setFirstElement(Link firstElement);

}
