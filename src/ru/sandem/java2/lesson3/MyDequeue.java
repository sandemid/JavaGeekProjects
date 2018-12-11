package ru.sandem.java2.lesson3;

public class MyDequeue extends MyQueue {

    public MyDequeue(int maxSize) {
        super(maxSize);
    }

    public void addLeft(int value) {
        if (isFull()) {
            System.out.println("Queue overflow");
            return;
        }
        if (front == DEFAULT_FRONT) {
            front = data.length;
        }
        data[--front] = value;
        size++;
    }


    public int removeRight() {
        if (isEmpty()){
            System.out.println("Queue is empty");
            return -1;
        }
        int value = data[rear--];
        size--;
        if (rear == DEFAULT_REAR && !isEmpty()) {
            rear = data.length - 1;
        }
        return value;
    }
}
