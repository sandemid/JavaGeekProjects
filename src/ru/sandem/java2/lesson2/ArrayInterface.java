package ru.sandem.java2.lesson2;

public interface ArrayInterface {

    public boolean add(int value);
    public void display();
    public boolean remove(int value);
    public boolean removeByIndex(int index);
    public boolean isEmpty();
    public boolean isFull();
    public int getSize();
    public int getElement(int index);
    public int getIndex (int value);
    public int[] getArray();
    public ArrayInterface copy();

}
