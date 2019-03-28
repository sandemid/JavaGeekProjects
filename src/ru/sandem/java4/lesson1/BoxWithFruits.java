package ru.sandem.java4.lesson1;

import ru.sandem.java4.lesson1.Fruits.Fruit;

import java.util.ArrayList;

public class BoxWithFruits <T extends Fruit> {

    private ArrayList<T> items;
    private double weigthItem;

    public BoxWithFruits(T item) {
        this.items = new ArrayList<T>();
        this.items.add(item);
        weigthItem = item.getWeight();
    }

    public void addItem(T item) {
        items.add(item);
    }

    public double getWeight(){
        return items.size() * weigthItem;
    }

    public boolean compareBox(BoxWithFruits<?> box){
        return (box.getWeight() == this.getWeight());
    }

    public void putItemToOtherBox(BoxWithFruits<T> box){
        while (this.items.size() != 0){
            box.addItem(this.items.get(0));
            this.items.remove(0);
        }
    }
}
