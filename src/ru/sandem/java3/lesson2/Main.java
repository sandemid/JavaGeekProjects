package ru.sandem.java3.lesson2;

import java.util.Scanner;

public class Main {
    
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
       
        int arraySize = 4;
        String[][] myArray = new String[arraySize][arraySize];
        final int ERROR_SUM = -123456789;
        int sumArray = ERROR_SUM;

        System.out.println("Введите элементы массива:");
        for (int i = 0; i < myArray.length; i++) {
            for (int j = 0; j < myArray[i].length; j++) {
                myArray[i][j] = scanner.nextLine();
            }
        }
        try {
            sumArray = refactorArray(myArray);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (sumArray == ERROR_SUM) {
                System.out.println("Массив не преобразован в числовой. Сумма элементов не вычислена");
            } else {
                System.out.println("Сумма элементов массива = " + sumArray);
            }
        }
        
    }

    public static int refactorArray (String[][] myArray) throws MyArrayDataException, MyArraySizeException {

        final int ALLOW_ARRAY_SIZE = 4;
        int[][] arr;
        int sum = 0;

        if (myArray.length != ALLOW_ARRAY_SIZE) {
            throw new MyArraySizeException("Некорректный размер массива. Допустимый = " + ALLOW_ARRAY_SIZE + ", получен = " + myArray.length);
        }
        for (int i = 0; i < myArray.length; i++) {
            if (myArray[i].length != ALLOW_ARRAY_SIZE) {
                throw new MyArraySizeException("Некорректный размер массива. Допустимый = " + ALLOW_ARRAY_SIZE + ", получен = " + myArray[i].length);
            }
        }
        
        arr = new int[ALLOW_ARRAY_SIZE][ALLOW_ARRAY_SIZE];
        
        for (int i = 0; i < ALLOW_ARRAY_SIZE; i++) {
            for (int j = 0; j < ALLOW_ARRAY_SIZE; j++) {
                try {
                    arr[i][j] = Integer.parseInt(myArray[i][j]);
                    sum += arr[i][j];
                } catch (Exception e) {
                    throw new MyArrayDataException("Ошибка значения в ячейке массива с координатами ", i, j);
                }
            }
        }

        return sum;
    }

}
