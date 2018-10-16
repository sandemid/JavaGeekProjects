package sandem.java2.lesson2;

public class MyArray implements ArrayInterface {

    private int[] array;
    private int size;
    private boolean sorted = false;

    public MyArray (int maxSize){
        if (maxSize <= 0) {
            this.array = new int[1];
            System.out.println("Задан некорректный максимальный размер массива, создан массив из 1 элемента");
        } else {
            this.array = new int[maxSize];
        }
        this.size = 0;
    }

    @Override
    public boolean add(int value) {
        if (isFull()) return false;
        this.array[size++] = value;
        return false;
    }

    @Override
    public void display() {
        System.out.println(this);
    }

    @Override
    public boolean remove(int value) {
        int index;
        if (sorted) index = getIndexBinary(value);
                else index = getIndex(value);
        return removeByIndex(index);
    }

    @Override
    public boolean removeByIndex(int index) {
        if (index < 0 || index >= size){
            return false;
        }
        for (int i = index; i < size - 1; i++) {
            this.array[i] = this.array[i + 1];
        }
        size--;
        return true;
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
    public boolean isFull() {
        return size == this.array.length;
    }

    @Override
    public int getElement(int index) {
        return this.array[index];
    }

    @Override
    public int getIndex(int value) {
        for (int i = 0; i < this.size; i++) {
            if (this.array[i] == value) return i;
        }
        return -1;
    }

    public int getIndexBinary (int value){
        if (!sorted) return -1;
        int low = 0;
        int high = this.size - 1;
        int mid;

        while (low < high){
            mid = (high + low) / 2;
            if (value == this.array[mid]){
                return mid;
            } else {
                if (value < this.array[mid]) {
                    high = mid;
                } else  {
                    low = mid;
                }
            }
        }

        return -1;
    }

    @Override
    public int[] getArray() {
        return this.array;
    }

    public void setSorted(boolean sorted) {
        this.sorted = sorted;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("{");
        for (int i = 0; i < size; i++) {
            str.append(this.array[i]);
            if (i != size - 1) str.append(", ");
        }
        str.append("}");
        return str.toString();
    }
}
