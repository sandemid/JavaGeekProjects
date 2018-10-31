package sandem.java2.lesson6;

public interface Tree {

    boolean add(MyData value);

    MyData remove(int id);

    MyData find(int id);

    boolean isEmpty();

    void display();

    int getSize();

    void traverse(TraverseMode mode);

    boolean isBalanced();

}
