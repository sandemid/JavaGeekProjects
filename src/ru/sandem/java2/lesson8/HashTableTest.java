package ru.sandem.java2.lesson8;

public class HashTableTest {

    public static void main(String[] args) {

        HashTable hashTable = new HashTable(10);

        Item item1 = new Item(2);
        Item item2 = new Item(7);
        Item item3 = new Item(32);
        Item item4 = new Item(15);
        Item item5 = new Item(17);

        hashTable.insert(item1);
        hashTable.insert(item2);
        hashTable.insert(item3);
        hashTable.insert(item4);
        hashTable.insert(item5);

        hashTable.display();

        System.out.println("---");

        System.out.println("Find 15:" + hashTable.find(15));
        System.out.println("Find 32:" + hashTable.find(32));
        System.out.println("Find 50:" + hashTable.find(50));

        hashTable.remove(2);
        hashTable.remove(item5);

        System.out.println("---");

        hashTable.display();

        System.out.println("---");

        hashTable.insert(new Item(25));
        hashTable.insert(new Item(25));
        hashTable.insert(new Item(35));
        hashTable.insert(new Item(45));
        hashTable.insert(new Item(14));
        hashTable.insert(new Item(244));
        hashTable.insert(new Item(18));
        hashTable.display();

        System.out.println("---");

        hashTable.remove(25);
        hashTable.remove(item5);
        hashTable.remove(item4);
        hashTable.display();
    }

}
