package sandem.java2.lesson3;

public class MyStack implements StackInterface {

    private int[] data;
    private int size;

    public MyStack(int maxSize) {
        this.data = new int[maxSize];
        this.size = 0;
    }

    @Override
    public void push(int value) {
        data[size++] = value;
    }

    @Override
    public int pop() {
        return 0;
    }

    @Override
    public int peek() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public void display() {

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
