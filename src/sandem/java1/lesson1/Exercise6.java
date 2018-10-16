import java.util.Scanner;

/**
 * Проверка принадлежности целого числа к отрицательному
 * @author A.Demidenkov
 * @version dated 2018.09.05
 */

public class Exercise6 {
    static Scanner userInput = new Scanner(System.in);
    public static void main(String[] args) {
        int a = readInt('a');
        System.out.println(calculation(a));
        userInput.close();
    }

    static int readInt(char argName) {
        System.out.print("Введите целое число " + argName + ": ");
        int argum = userInput.nextInt();
        return argum;
    }

    static boolean calculation(int a) {
        return a < 0;
    }
}
