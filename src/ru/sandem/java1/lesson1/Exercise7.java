package ru.sandem.java1.lesson1;

import java.util.Scanner;

/**
 * Вывод приветствия к введенному имени в методе
 * @author A.Demidenkov
 * @version dated 2018.09.05
 */

public class Exercise7 {
    static Scanner userInput = new Scanner(System.in);
    public static void main(String[] args) {
        String a = readString();
        createAnswer(a);
        userInput.close();
    }
    static String readString() {
        System.out.print("Введите имя: ");
        String argum = userInput.nextLine();
        return argum;
    }
    static void createAnswer(String a) {
        System.out.println("Привет, " + a + "!");
     }
}
