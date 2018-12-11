package ru.sandem.java2.lesson6;

import java.util.Stack;

public class TreeImpl implements Tree {

    private Node root;

    private int size;
    private int maxLevel;

    public TreeImpl() {
        this(0);
    }

    public TreeImpl(int maxLevel) {
        this.size = 0;
        this.maxLevel = maxLevel;
    }

    @Override
    public boolean add(MyData value) {
        Node node = new NodeImpl(value);
        if (root == null) {
            root = node;
            return true;
        }

        Node current = root;
        Node parent = null;

        while (current != null) {
            parent = current;
            if (current.getKey() == value.getId()) {
                current.setData(value);
                return true;
            }
            current = current.getChildByKey(value.getId());
        }

        int level = height(root, parent.getKey()) + 1;
        if (level > maxLevel) {
            return false;
        }

        if ( parent.isLeftChild(value.getId()) ) {
            parent.setLeftChild(node);
        }
        else {
            parent.setRightChild(node);
        }
        size++;
        return true;
    }

    private NodeAndParent getNodeAndParentByKey(int id) {
        Node current = root;
        Node parent = null;

        while (current != null) {
            if (current.getKey() == id) {
                break;
            }

            parent = current;
            current = current.getChildByKey(id);
        }

        return new NodeAndParent(parent, current);
    }

    @Override
    public MyData remove(int id) {
        NodeAndParent nodeAndParent = getNodeAndParentByKey(id);

        Node current = nodeAndParent.current;
        Node parent = nodeAndParent.parent;

        if (current == null) {
            return null;
        }

        if (current.isLeaf()) {
            removeLeafNode(id, parent);

        }
        else if (current.getLeftChild() == null || current.getRightChild() == null) {
            removeNodeWithSingleChild(current, parent);
        }
        else {
            removeNodeWithBothChildren(id, current, parent);
        }

        size--;
        return current.getData();
    }

    private void removeNodeWithBothChildren(int id, Node current, Node parent) {
        Node successor = getSuccessor(current);
        if (current == root) {
            root = successor;
        }
        else if ( parent.isLeftChild(id) ) {
            parent.setLeftChild(successor);
        }
        else {
            parent.setRightChild(successor);
        }
        successor.setLeftChild(current.getLeftChild());
    }

    private void removeNodeWithSingleChild(Node current, Node parent) {
        Node childNode = current.getLeftChild() != null ? current.getLeftChild() : current.getRightChild();
        if ( parent.isLeftChild(current.getKey()) ) {
            parent.setLeftChild(childNode);
        }
        else {
            parent.setRightChild(childNode);
        }
    }

    private void removeLeafNode(int id, Node parent) {
        if (parent == null) {
            root = null;
        }
        else if ( parent.isLeftChild(id) ) {
            parent.setLeftChild(null);
        }
        else {
            parent.setRightChild(null);
        }
    }

    private Node getSuccessor(Node node) {
        Node successor = node;
        Node successorParent = node;
        Node current = node.getRightChild();

        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.getLeftChild();
        }

        if (successor != node.getRightChild()) {
            successorParent.setLeftChild(successor.getRightChild());
            successor.setRightChild(node.getRightChild());
        }

        return successor;


    }

    @Override
    public MyData find(int id) {
        Node current = root;

        while (current != null) {
            if (current.getKey() == id) {
                return current.getData();
            }

            current = current.getChildByKey(id);
        }

        return null;
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
        Stack<Node> globalStack = new Stack();
        globalStack.push(root);
        int nBlanks = 64;

        boolean isRowEmpty = false;
        System.out.println(".....");

        while (!isRowEmpty) {
            Stack<Node> localStack = new Stack<>();

            isRowEmpty = true;
            for (int i = 0; i < nBlanks; i++) {
                System.out.print(" ");
            }

            while (!globalStack.isEmpty()) {
                Node tempNode = globalStack.pop();
                if (tempNode != null) {
                    System.out.print(tempNode.getKey());
                    localStack.push(tempNode.getLeftChild());
                    localStack.push(tempNode.getRightChild());
                    if (tempNode.getLeftChild() != null || tempNode.getRightChild() != null) {
                        isRowEmpty = false;
                    }
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlanks * 2 - 2; j++) {
                    System.out.print(" ");
                }
            }

            System.out.println();

            while (!localStack.isEmpty()) {
                globalStack.push(localStack.pop());
            }

            nBlanks /= 2;
        }
        System.out.println(".....");
    }

    private class NodeAndParent {
        Node parent;
        Node current;

        public NodeAndParent(Node parent, Node current) {
            this.parent = parent;
            this.current = current;
        }
    }

    @Override
    public void traverse(TraverseMode mode) {
        switch (mode) {
            case IN_ORDER:
                inOrder(root);
                break;
            case PRE_ORDER:
                preOrder(root);
                break;
            case POST_ORDER:
                postOrder(root);
                break;
            default:
                throw new IllegalArgumentException("Invalid traverse mode = " + mode);
        }
    }

    private void postOrder(Node root) {
        if (root != null) {
            preOrder(root.getLeftChild());
            preOrder(root.getRightChild());
            root.display();
        }
    }

    private void preOrder(Node root) {
        if (root != null) {
            root.display();
            preOrder(root.getLeftChild());
            preOrder(root.getRightChild());
        }
    }

    private void inOrder(Node root) {
        if (root != null) {
            inOrder(root.getLeftChild());
            root.display();
            inOrder(root.getRightChild());
        }
    }

    @Override
    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {
        return (node == null) ||
                isBalanced(node.getLeftChild()) &&
                        isBalanced(node.getRightChild()) &&
                        Math.abs(height(node.getLeftChild()) - height(node.getRightChild())) <= 1;
    }

    private int height(Node node, int key) {
        if (node.getKey() == key) {
            return 1;
        } else  {
            return 1 + height(node.getChildByKey(key),key);
        }
    }

    private int height(Node node){
        return node == null ? 0 : 1 + Math.max(height(node.getLeftChild()), height(node.getRightChild()));
    }
}
