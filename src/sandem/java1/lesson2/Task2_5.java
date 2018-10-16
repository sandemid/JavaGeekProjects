package sandem.java1.lesson2;

/**
 * Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета) :)
 * @author A.Demidenkov
 * @version dated 2018.09.06
 */

public class Task2_5 {
    public static void main(String[] args) {
        int[] arr = new int[20];
        for (int i = 0; i < arr.length; i++) { //заполняем массив случайным образом от -50 до 50
            arr[i] = (int) Math.floor(Math.random()*101) - 50;
        }
        int max = arr[0];
        int min = arr[0];
        printArray(arr);
        for (int i = 1; i < arr.length; i++) {
            max = arr[i] > max ? arr[i] : max;
            min = arr[i] < min ? arr[i] : min;
        }
        System.out.println("Максимальное значение в массиве = " + max);
        System.out.println("Минимальное значение в массиве = " + min);
    }
    static void printArray (int[] ar){
        for (int i = 0; i < ar.length; i++) {
            System.out.print(ar[i] + " ");
        }
        System.out.println();
    }
}
