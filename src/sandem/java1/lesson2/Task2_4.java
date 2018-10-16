/**
 * Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое),
 * и с помощью цикла(-ов) заполнить его диагональные элементы единицами
 * @author A.Demidenkov
 * @version dated 2018.09.06
 */

public class Task2_4 {
    public static void main(String[] args) {
        int[][] arr = new int[9][9];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = (i == j || i == arr[i].length - j - 1) ? 1 : 0;
            }
        }
        printArray(arr);
    }
    static void printArray (int[][] ar){
        for (int i = 0; i < ar.length; i++) {
            for (int j = 0; j < ar[i].length; j++) {
                System.out.print(ar[i][j] + " ");
            }
            System.out.println();
        }
    }
}
