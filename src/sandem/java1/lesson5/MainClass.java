package sandem.java1.lesson5;

import sandem.java1.lesson5.Bowl;

import java.util.Random;

/**
 * Создаем объект миска со случайной емкостью от 50 до 100
 * Создаем массив котов, затем инициализируем каждый элемент со случайным аппетитом
 * Затем кормим котов и выводим в консоль сытость каждого и остаток еды в миске
 */

public class MainClass {
    public static void main(String[] args) {

        Random random = new Random();
        Bowl bowl = new Bowl(random.nextInt(50) + 50);
        Cat[] cats = new Cat[10];

        System.out.println("Создаем котов:");
        for (int i = 0; i < cats.length; i++) {
            cats[i] = new Cat("Cat " + (i + 1), random.nextInt(20));
            System.out.println(cats[i].getName() + ", аппетит = " + cats[i].getAppetite() + " ед. еды за 1 прием");
        }

        System.out.println("Кормим котов. Подождите...");
        for (Cat i: cats) {
            i.Eating(bowl);
        }

        for (Cat i: cats){
            System.out.println(i.getName() + ", аппетит = " + i.getAppetite() + ", сытость: " + i.isCatFullness());
        }

        System.out.println("Емкость миски = " + bowl.getVolume() + ", остаток еды = " + bowl.getFoodNow());
    }
}
