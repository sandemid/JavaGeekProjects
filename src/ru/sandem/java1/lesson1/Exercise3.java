package ru.sandem.java1.lesson1;

import java.util.Scanner;

/**
 * Метод, вычисляющий выражение a * (b + (c / d)) и возвращающий результат
 * @author A.Demidenkov
 * @version dated 2018.09.05
 */

public class Exercise3 {
    static Scanner userInput = new Scanner(System.in);
    public static void main(String[] args) {
        double a = readDouble('a');
        double b = readDouble('b');
        double c = readDouble('c');
        double d = readDouble('d');
        System.out.println("Результат формулы a * (b + (c / d)) = " + calculation(a,b,c,d));
        userInput.close();
    }

    static double readDouble(char argName) {
        System.out.print("Введите число " + argName + ": ");
        double argum = userInput.nextDouble();
        return argum;
    }

    static double calculation(double a, double b, double c, double d) {
        return a*(b+(c/d));
    }

}