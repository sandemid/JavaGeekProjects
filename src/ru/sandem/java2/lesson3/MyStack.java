package ru.sandem.java2.lesson3;

public class MyStack implements StackInterface {

    private int[] data;
    private int size;

    public MyStack(int maxSize) {
        this.data = new int[maxSize];
        this.size = 0;
    }

    @Override
    public void push(int value) {
        if (isFull()) {
            System.out.println("Stack overflow");
            return;
        } else {
            data[size++] = value;
        }
    }

    @Override
    public int pop() {
        if (isEmpty()){
            System.out.println("Stack is empty");
            return -1;
        } else  {
            return data[--size];
        }
    }

    @Override
    public int peek() {
        return data[size - 1];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == data.length;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void display() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("{");
        for (int i = 0; i < size; i++) {
            str.append(data[i]);
            if (i != size - 1) str.append(", ");
        }
        str.append("}");
        return str.toString();
    }
}
