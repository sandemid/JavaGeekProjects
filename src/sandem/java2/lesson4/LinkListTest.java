package sandem.java2.lesson4;

import java.util.Random;

public class LinkListTest {

    public static void main(String[] args) {
        LinkedListInterface list = new SimpleLinkedList();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            list.add(random.nextInt(15));
        }
        list.display();
        display(list);
    }

    private static void display(LinkedListInterface list) {
        for (Integer value : list) {
            System.out.print(value + " -> ");
        }
    }

}
