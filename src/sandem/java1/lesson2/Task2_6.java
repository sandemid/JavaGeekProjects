package sandem.java1.lesson2;

/**
 * Написать метод, в который передается не пустой одномерный целочисленный массив,
 * метод должен вернуть true если в массиве есть место,
 * в котором сумма левой и правой части массива равны
 * @author A.Demidenkov
 * @version dated 2018.09.06
 */

public class Task2_6 {
    public static void main(String[] args) {
        int[] arr = new int[6];
        for (int i = 0; i < arr.length; i++) { //заполняем массив случайным образом от 1 до 7
            arr[i] = (int) Math.floor(Math.random()*7) + 1;
        }
        printArray(arr);
        System.out.println(checkBalance(arr));
    }
    static void printArray (int[] ar){
        for (int i = 0; i < ar.length; i++) {
            System.out.print(ar[i] + " ");
        }
        System.out.println();
    }
    static boolean checkBalance (int[] ar) {
        int sumLeft = 0;
        int sumRight = 0;
        boolean result = false;
        for (int i = 0; i < ar.length; i++) {
            sumLeft += ar[i];
            for (int j = ar.length - 1; j > i; j--) {
                sumRight += ar[j];
            }
            if (sumLeft == sumRight) {
                result = true;
                break;
            }
            sumRight = 0;
        }
        return result;
    }
}
