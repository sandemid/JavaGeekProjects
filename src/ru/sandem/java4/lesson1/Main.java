package ru.sandem.java4.lesson1;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Integer[] a1 = new Integer[]{1, 2, 3, 4, 5};
        Double[] a2 = new Double[]{1.5, 2.0, 3.8, 4.6, 5.9};


        System.out.print("Исходный массив 1:");
        printArray(a1);
        System.out.print("Исходный массив 2:");
        printArray(a2);

        changeElements(1, 2, a1);
        changeElements(4, 0, a2);

        System.out.print("Массив 1, поменяли местами 2-3 и преобразовали в список:");
        printArray(convertToArrrayList(a1));
        System.out.print("Массив 2, поменяли местами 5-1 и преобразовали в список:");
        printArray(convertToArrrayList(a2));
    }

    // метод смены элементов массива местами

    static public <T extends Object> void changeElements (int pos1, int pos2, T... array) {
        T temp;
        temp = array[pos2];
        array[pos2] = array[pos1];
        array[pos1] = temp;
    }

    // метод преобразования массива в ArrayList

    static public <T> ArrayList<T> convertToArrrayList (T... array){
        ArrayList<T> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    //метод вывода массива на экран

    static public <T> void printArray (T[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i].toString() + " ");
        }
        System.out.println();
    }

    static public <T extends List<?>> void printArray (T list){
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i).toString() + " ");
        }
        System.out.println();
    }

}
