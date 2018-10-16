package sandem.java1.lesson4;

import java.util.Random;

/**
 * Задаем два массива кошек и собак
 * инициализируем их, передавая в конструктор случаные параметры бега, прыжков и плавания
 * В цикле выводим для каждого экзепляра возможность пробежать, прыгнуть или проплыть переданное значение
 */

public class MainClass_2 {
    public static void main(String[] args) {

        final int length = 3;
        Random random = new Random();

        Animal[] cats = new Cat[length];
        Animal[] dogs = new Dog[length];

        for (int i = 0; i < length; i++) {
            cats[i] = new Cat("sandem.java_1.lesson4_2.Cat" + (i + 1), random.nextInt(5), random.nextDouble() * 3, random.nextInt(300));
            dogs[i] = new Dog("sandem.java_1.lesson4_2.Dog" + (i + 1), random.nextInt(20), random.nextDouble() * 1.5, random.nextInt(700));
        }

        System.out.println(cats.getClass().getSimpleName());

        for (int i = 0; i < length; i++) {
            cats[i].swim(random.nextInt(5));
            cats[i].jump(random.nextDouble() * 3);
            cats[i].run(random.nextInt(300));
            dogs[i].swim(random.nextInt(20));
            dogs[i].jump(random.nextDouble() * 1.5);
            dogs[i].run(random.nextInt(700));
        }
    }
}
