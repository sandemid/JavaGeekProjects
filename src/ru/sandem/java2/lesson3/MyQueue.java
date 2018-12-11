package ru.sandem.java2.lesson3;

public class MyQueue implements QueueInterface {

    protected int[] data;
    protected int size;
    public static final int DEFAULT_FRONT = 0;
    public static final int DEFAULT_REAR = -1;
    protected int front;
    protected int rear;

    public MyQueue(int maxSize) {
        this.data = new int[maxSize];
        this.size = 0;
        this.front = DEFAULT_FRONT;
        this.rear = DEFAULT_REAR;
    }

    @Override
    public void addRight(int value) {
        if (isFull()) {
            System.out.println("Queue overflow");
            return;
        }
        if (rear == data.length - 1) {
            rear = DEFAULT_REAR;
        }
        data[++rear] = value;
        size++;
    }

    @Override
    public int removeLeft() {
        if (isEmpty()){
            System.out.println("Queue is empty");
            return -1;
        }
        int value = data[front++];
        if (front == data.length) {
            front = DEFAULT_FRONT;
        }
        size--;
        return value;
    }

    @Override
    public boolean isFull() {
        return size == data.length;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void display() {
        int currentSize = size;
        int currentFront = front;
        while (currentSize > 0) {
            System.out.print(data[currentFront++] + " ");
            if (currentFront == data.length) {
                currentFront = DEFAULT_FRONT;
            }
            currentSize--;
        }
        System.out.println();
    }
}
