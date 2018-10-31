package sandem.java2.lesson6;

public interface NodeInterface {

    void setData(MyData value);
    MyData getData();

    int getKey();

    NodeInterface getLeftChild();
    NodeInterface getRightChild();

    void setRightChild(NodeInterface rightChild);
    void setLeftChild(NodeInterface leftChild);

    NodeInterface getChildByKey(int key);

    boolean isLeftChild(int key);

    boolean isLeaf();

    void display();
}
