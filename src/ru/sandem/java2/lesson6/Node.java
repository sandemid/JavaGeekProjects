package ru.sandem.java2.lesson6;

public interface Node {

    void setData(MyData value);
    MyData getData();

    int getKey();

    Node getLeftChild();
    Node getRightChild();

    void setRightChild(Node rightChild);
    void setLeftChild(Node leftChild);

    Node getChildByKey(int key);

    boolean isLeftChild(int key);

    boolean isLeaf();

    void display();
}
