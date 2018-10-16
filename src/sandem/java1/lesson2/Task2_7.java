import java.util.Scanner;

/**
 * Написать метод, которому на вход подается одномерный массив и число n (может быть положительным
 * или отрицательным), при этом метод должен сместить все элементы массива на n позиций.
 * Нельзя пользоваться вспомогательными массивами
 * @author A.Demidenkov
 * @version dated 2018.09.06
 */

public class Task2_7 {
    static Scanner userInput = new Scanner(System.in);
    public static void main(String[] args) {
        int[] arr = new int[36];
        for (int i = 0; i < arr.length; i++) { //заполняем массив случайным образом от -50 до 50
            arr[i] = (int) Math.floor(Math.random()*101) - 50;
        }
        printArray(arr);
        int a = readInt();
        if (Math.abs(a) <= arr.length) {
            arr = changeArray(arr, a);
            printArray(arr);
        } else {
            System.out.println("Число находится вне границ допустимого диапазона!");
        }
    }
    static void printArray (int[] ar){
        for (int i = 0; i < ar.length; i++) {
            System.out.print(ar[i] + " ");
        }
        System.out.println();
    }
    static int readInt() {
        System.out.print("Введите целое число от -20 до 20: ");
        int argum = userInput.nextInt();
        return argum;
    }
    static int[] changeArray (int[] ar, int index) {
        int temp1 = 0;
        int temp2 = ar[0];
        int k = 0;
        int z = 0;
        for (int i = 0; i < ar.length; i++) {
            if (index >= 0) {
                if (k % 2 == 0 && !(k != 0 && (k * index) % ar.length == 0)) {
                    temp1 = ar[((k + 1) * index) % ar.length + z];
                    ar[((k + 1) * index) % ar.length + z] = temp2;
                } else if (!(k != 0 && (k * index) % ar.length == 0)) {
                    temp2 = ar[((k + 1) * index) % ar.length + z];
                    ar[((k + 1) * index) % ar.length + z] = temp1;
                } else {
                    z++;
                    k = 0;
                    temp2 = ar[((k + 2) * index) % ar.length + z];
                    temp1 = ar[((k + 1) * index) % ar.length + z];
                    ar[((k + 1) * index) % ar.length + z] = ar[(k * index) % ar.length + z];
                }
                k++;
            } else {
                if (k % 2 == 0 && !(k != 0 && (ar.length + (k * index) % ar.length) % ar.length == 0)) {
                    temp1 = ar[(ar.length + ((k + 1) * index) % ar.length + z) % ar.length];
                    ar[(((k + 1) * index) % ar.length + z + ar.length) % ar.length] = temp2;
                } else if (!(k != 0 && (ar.length + (k * index) % ar.length) % ar.length == 0)) {
                    temp2 = ar[(((k + 1) * index) % ar.length + z + ar.length) % ar.length];
                    ar[(((k + 1) * index) % ar.length + z + ar.length) % ar.length] = temp1;
                } else {
                    z++;
                    k = 0;
                    temp2 = ar[(((k + 2) * index) % ar.length + z + ar.length) % ar.length];
                    temp1 = ar[(((k + 1) * index) % ar.length + z + ar.length) % ar.length];
                    ar[(((k + 1) * index) % ar.length + z + ar.length) % ar.length] = ar[((k * index) % ar.length + z + ar.length) % ar.length];
                }
                k++;
            }
        }
/*        for (int j = 1; j <= Math.abs(index); j++) {
            temp = (index >= 0) ? ar[ar.length - 1] : ar[0];
            for (int i = 0; i < ar.length; i++) {
                if (index >= 0) {
                    ar[ar.length - i - 1] = (ar.length - i - 2 >= 0) ? ar[ar.length - i - 2] : 0;
                } else {
                    ar[i] = (i + 1 < ar.length) ? ar[i + 1] : 0;
                }
            }
            if (index >= 0) {
                ar[0] = temp;
            } else {
                ar[ar.length - 1] = temp;
            }
        } */
        return ar;
    }
}
