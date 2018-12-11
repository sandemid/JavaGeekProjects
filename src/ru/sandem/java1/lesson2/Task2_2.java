package ru.sandem.java1.lesson2;

/**
 * Задать пустой целочисленный массив размером 8.
 * С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21
 * @author A.Demidenkov
 * @version dated 2018.09.06
 */

public class Task2_2 {
    public static void main(String[] args) {
        int[] arr = new int[8];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i * 3;
        }
        printArray(arr);
     }
    static void printArray (int[] ar){
        for (int i = 0; i < ar.length; i++) {
            System.out.print(ar[i] + " ");
        }
    }
}
