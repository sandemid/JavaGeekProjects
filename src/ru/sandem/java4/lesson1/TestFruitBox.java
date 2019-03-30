package ru.sandem.java4.lesson1;

import ru.sandem.java4.lesson1.Fruits.Apple;
import ru.sandem.java4.lesson1.Fruits.Orange;

public class TestFruitBox {
    public static void main(String[] args) {
        BoxWithFruits<Apple> box1 = new BoxWithFruits<>(new Apple());
        BoxWithFruits<Apple> box2 = new BoxWithFruits<>(new Apple());
        BoxWithFruits<Orange> box3 = new BoxWithFruits<>(new Orange());

        box1.addItem(new Apple());
        box1.addItem(new Apple());
        box3.addItem(new Orange());

        System.out.println("Вес коробки 1 = " + box1.getWeight());
        System.out.println("Вес коробки 2 = " + box2.getWeight());
        System.out.println("Вес коробки 3 = " + box3.getWeight());

        System.out.println("Результат сравнения о весу коробок 1 и 2 = " + box1.compareBox(box2));
        System.out.println("Результат сравнения о весу коробок 2 и 3 = " + box2.compareBox(box3));
        System.out.println("Результат сравнения о весу коробок 1 и 3 = " + box1.compareBox(box3));

        box1.putItemToOtherBox(box2);
        System.out.println("Переложили яблоки из коробки 1 в 2");
        System.out.println("Вес коробки 1 = " + box1.getWeight());
        System.out.println("Вес коробки 2 = " + box2.getWeight());
    }
}
