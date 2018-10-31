package sandem.java2.lesson6;

public class Node implements NodeInterface{

    private MyData value;

    private NodeInterface leftChild;
    private NodeInterface rightChild;

    public Node(MyData value) {
        this.value = value;
    }

    @Override
    public void setData(MyData value) {
        this.value = value;
    }

    @Override
    public MyData getData() {
        return value;
    }

    @Override
    public int getKey() {
        return value.getId();
    }

    @Override
    public NodeInterface getLeftChild() {
        return leftChild;
    }

    @Override
    public NodeInterface getRightChild() {
        return rightChild;
    }


    public void setLeftChild(NodeInterface leftChild) {
        this.leftChild = leftChild;
    }


    public void setRightChild(NodeInterface rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public NodeInterface getChildByKey(int key) {
        if ( isLeftChild(key) ) {
            return getLeftChild();
        }
        else {
            return getRightChild();
        }
    }

    @Override
    public boolean isLeftChild(int key) {
        return key < getKey();
    }

    @Override
    public boolean isLeaf() {
        return getLeftChild() == null && getRightChild() == null;
    }

    @Override
    public void display() {
        System.out.println(getData());
    }

}
