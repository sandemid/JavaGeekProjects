package sandem.java2.lesson2;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ArrayTest {

    public static void main(String[] args) {
        Random random = new Random();
        int maxSize = 100000;
        MyArray array = new MyArray(maxSize);


        for (int i = 0; i < 35; i++) {
            array.add(random.nextInt(100));
        }

        array.display();
        System.out.println("Размер массива текущий = " + array.getSize());
        System.out.println("Индекс элемента со значением " + array.getElement(19) + " = " + array.getIndex(array.getElement(19)));
        System.out.println("Значение элемента с индексом 11 = " + array.getElement(11));
        System.out.println("Удаляем элемент с индексом 11");
        array.removeByIndex(11);
        array.display();
        System.out.println("Размер массива текущий = " + array.getSize());
        System.out.println("Удаляем элемент с индексом 22");
        array.removeByIndex(22);
        array.display();
        System.out.println("Размер массива текущий = " + array.getSize());
        System.out.println("Удаляем элемент с индексом 0");
        array.removeByIndex(0);
        array.display();
        System.out.println("Размер массива текущий = " + array.getSize());


        System.out.println("Удаляем элемент со значением " + array.getElement(15));
        array.remove(array.getElement(15));
        array.display();
        System.out.println("Размер массива текущий = " + array.getSize());
        System.out.println("Удаляем элемент со значением " + array.getElement(10));
        array.remove(array.getElement(10));
        array.display();
        System.out.println("Размер массива текущий = " + array.getSize());

//        System.out.println("Отсортированный массив:");
//        ArrayUtils.sortInsert(array);
//        array.display();


        System.out.println("Перезаполняем массив случайно полностью");
        for (int i = array.getSize(); i < maxSize; i++) {
            array.add(random.nextInt(maxSize));
        }

        int[] a1 = new int[array.getSize()];
        int[] a2 = new int[array.getSize()];
        int[] a3 = new int[array.getSize()];

        for (int i = 0; i < array.getSize(); i++) {
            a1[i] = array.getElement(i);
            a2[i] = array.getElement(i);
            a3[i] = array.getElement(i);
        }

        long start = System.nanoTime();
        ArrayUtils.sortBubble(a1);
        System.out.println("Сортировка пузырьком мс = " + TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start));

        start = System.nanoTime();
        ArrayUtils.sortSelect(a2);
        System.out.println("Сортировка выбором мс = " + TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start));

        start = System.nanoTime();
        ArrayUtils.sortInsert(a3);
        System.out.println("Сортировка вставкой мс = " + TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start));

        int t = random.nextInt(maxSize);
        ArrayUtils.sortInsert(array);
//        array.display();
        start = System.nanoTime();
        System.out.println("Линейный поиск значения " + array.getIndex(array.getElement(t)) + " нанос = " + TimeUnit.NANOSECONDS.toNanos(System.nanoTime() - start));
        start = System.nanoTime();
        System.out.println("Бинарный поиск значения " + array.getIndexBinary(array.getElement(t)) + " нанос = " + TimeUnit.NANOSECONDS.toNanos(System.nanoTime() - start));

//        for (int i = 0; i < a1.length; i++) {
//            System.out.print(a1[i] + ", ");
//        }

    }
}
