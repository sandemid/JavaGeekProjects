package sandem.java2.lesson4;

public class Link {
    private final int data;
    private Link next;

    public Link(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public Link getNext() {
        return next;
    }

    public void setNext(Link next) {
        this.next = next;
    }

    @Override
    public String toString() {
        String str = new String();
        str = str + data;
        return str;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return data == link.data;
    }

}
