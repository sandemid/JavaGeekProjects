package sandem.java1.lesson2;

/**
 * Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ],
 * пройти по нему циклом, и числа, меньшие 6, умножить на 2
 * @author A.Demidenkov
 * @version dated 2018.09.06
 */

public class Task2_3 {
    public static void main(String[] args) {
        int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        printArray(arr);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (arr[i] < 6) ? arr[i] * 2 : arr[i];
        }
        printArray(arr);
    }
    static void printArray (int[] ar){
        for (int i = 0; i < ar.length; i++) {
            System.out.print(ar[i] + " ");
        }
        System.out.println();
    }
}
