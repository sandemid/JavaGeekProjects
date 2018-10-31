package sandem.java2.lesson6;

public class NodeImpl implements Node {

    private MyData value;

    private Node leftChild;
    private Node rightChild;

    public NodeImpl(MyData value) {
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
    public Node getLeftChild() {
        return leftChild;
    }

    @Override
    public Node getRightChild() {
        return rightChild;
    }


    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }


    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public Node getChildByKey(int key) {
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
