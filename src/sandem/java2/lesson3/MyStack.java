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
        if (isFull()) {
            System.out.println("Overflow");
            return;
        } else {
            data[size++] = value;
        }
    }

    @Override
    public int pop() {
        if (isEmpty()){
            return -1;
        } else  {
            return data[--size];
        }
    }

    @Override
    public int peek() {
        return 0;
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

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
