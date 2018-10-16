import java.util.Scanner;

/**
 * Проверка суммы двух чисел на принадлежность к диапазону от 10 до 20 включительно
 * @author A.Demidenkov
 * @version dated 2018.09.05
 */

public class Exercise4 {
    static Scanner userInput = new Scanner(System.in);
    public static void main(String[] args) {
        double a = readDouble('a');
        double b = readDouble('b');
        System.out.println(calculation(a,b));
        userInput.close();
    }

    static double readDouble(char argName) {
        System.out.print("Введите число " + argName + ": ");
        double argum = userInput.nextDouble();
        return argum;
    }

    static boolean calculation(double a, double b) {
        return ((a + b) >= 10 && (a + b) <= 20);
    }
}
