package ru.sandem.java1.lesson1;

import java.util.Scanner;

/**
 * Проверка, является год високосным или нет
 * @author A.Demidenkov
 * @version dated 2018.09.05
 */

public class Exercise8 {
    static Scanner userInput = new Scanner(System.in);
    public static void main(String[] args) {
        int a = readInt();
        checkYear(a);
        userInput.close();
    }
    static int readInt() {
        System.out.print("Введите год: ");
        int argum = userInput.nextInt();
        return argum;
    }
    static void checkYear(int a) {
        if (((a % 4 == 0) && !(a % 100 == 0)) || (a % 400 == 0)) {
            System.out.println("Год високосный");
        } else {
            System.out.println("Год не високосный");
        }
    }
}