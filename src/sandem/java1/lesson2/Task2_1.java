/**
 * Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
 * С помощью цикла и условия заменить 0 на 1, 1 на 0
 * @author A.Demidenkov
 * @version dated 2018.09.06
 */

public class Task2_1 {

    public static void main(String[] args) {
        short[] arr = new short[20];
        for (int i = 0; i < arr.length; i++) { //заполняем массив 0 и 1 случайным образом
            arr[i] = (short) Math.floor(Math.random()*2);
        }
        printArray(arr);
        for (int i = 0; i < arr.length; i++) { //меняем 0 на 1 и 1 на 0
            arr[i] = (arr[i] == 0) ? (short)1 : (short)0;
        }
        printArray(arr);
    }
    static void printArray (short[] ar){ //метод вывода массива на экран
        for (int i = 0; i < ar.length; i++) {
            System.out.print(ar[i] + " ");
        }
        System.out.println();
    }
}
