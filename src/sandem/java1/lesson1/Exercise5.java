package sandem.java1.lesson1;

import java.util.Scanner;

/**
 * Проверка знака введенного целого числа
 * @author A.Demidenkov
 * @version dated 2018.09.05
 */

public class Exercise5 {
    static Scanner userInput = new Scanner(System.in);
    public static void main(String[] args) {
        int a = readInt('a');
        calculation(a);
        userInput.close();
    }

    static int readInt(char argName) {
        System.out.print("Введите целое число " + argName + ": ");
        int argum = userInput.nextInt();
        return argum;
    }

    static void calculation(int a) {
        if (a >= 0) {
            System.out.println("Это положительное число");
        } else {
            System.out.println("Это отрицательное число");
        }
    }
}
