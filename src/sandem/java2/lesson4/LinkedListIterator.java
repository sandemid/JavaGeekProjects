package sandem.java2.lesson4;

import java.util.Iterator;

public class LinkedListIterator implements Iterator<Integer> {

    private LinkedListInterface list;
    private boolean firstIterate = true;

    private Link current;
    private Link previous;

    public LinkedListIterator(LinkedListInterface list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        if (firstIterate) {
            firstIterate = false;
            return list.getFirstElement() != null;
        }
        else {
            return current.getNext() != null;
        }
    }

    @Override
    public Integer next() {
        if (current == null) {
            current = list.getFirstElement();
        }
        else {
            previous = current;
            current = current.getNext();
        }
        return current.getData();
    }

    @Override
    public void remove() {
        if (previous == null){
            list.setFirstElement(current.getNext());
            reset();
        } else {
            previous.setNext(current.getNext());
            if ( !hasNext() ) {
                reset();
            } else {
                current = current.getNext();
            }
        }
    }

    public void reset() {
        firstIterate = false;
        current = list.getFirstElement();
        previous = null;
    }

    public void insertBefore(int value) {
        Link newItem = new Link(value);
        if(previous == null) {
            newItem.setNext(list.getFirstElement());
            list.setFirstElement(newItem);
            reset();
        }
        else {
            newItem.setNext(previous.getNext());
            previous.setNext(newItem);
            current = newItem;
        }

    }

    public void insertAfter(int value) {
        Link newItem = new Link(value);
        if (list.isEmpty()){
            list.setFirstElement(newItem);
            current = newItem;
        } else {
            newItem.setNext(current.getNext());
            current.setNext(newItem);
            next();
        }
    }
}
