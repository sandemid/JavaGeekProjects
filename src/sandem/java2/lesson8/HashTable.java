package sandem.java2.lesson8;

import java.util.ArrayList;

public class HashTable {

    private ArrayList<Item>[] hashArray;
    private int currentSize;

    public HashTable(int maxSize) {
        this.hashArray = new ArrayList[maxSize];
        this.currentSize = 0;
    }

    public int hashFunc(int key) {
        return key % hashArray.length;
    }


    public int hashFunc(Item item) {
        return hashFunc(item.hashCode());
    }

    public void insert(Item item) {
        int index = hashFunc(item);

        int count = 0;

        if (hashArray[index] == null){
            hashArray[index] = new ArrayList<Item>();
            hashArray[index].add(item);
        } else {
            hashArray[index].add(item);
        }
    }

    public boolean remove(Item item) {
        return hashArray[hashFunc(item.getData())].remove(item);
    }

    public boolean remove(int key){
        return remove(find(key));
    }

    public Item find(int key) {
        int index = hashFunc(key);
        int count = 0;

        while (hashArray[index] != null && count < hashArray[index].size()) {
            if (hashArray[index].get(count).getData() == key) {
                return hashArray[index].get(count);
            }
            count++;
        }

        return null;
    }

    public void display() {
        for (int i = 0; i < hashArray.length; i++) {
            if (hashArray[i] != null) {
                for (int j = 0; j < hashArray[i].size(); j++) {
                    System.out.println(hashArray[i].get(j));
                }
            }
        }
    }

}
