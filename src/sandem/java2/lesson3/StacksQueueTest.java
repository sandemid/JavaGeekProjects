package sandem.java2.lesson3;

import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class StacksQueueTest {

    static Random random = new Random();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
//        testStack();
//        stringReverse();
        testQueue();
    }

    private static void testQueue() {


    }

    private static void stringReverse() {
        char[] ch;
        String str = new String();
        Stack stack = new Stack();

        System.out.println("Input string:");
        ch = scanner.nextLine().toCharArray();

        for (int i = 0; i < ch.length; i++) {
            stack.push(ch[i]);
        }

        while (stack.size() != 0) {
            str = str + stack.pop();
        }
        System.out.println(str);

    }

    private static void testStack() {
        int maxSize = 20;
        MyStack stack = new MyStack(maxSize);
        System.out.println("Создаем стек из 20 элементов");
        System.out.println("Заполняем стек:");
        for (int i = 0; i < maxSize - 2; i++) {
            stack.push(random.nextInt(50));
        }
        stack.display();
        stack.push(random.nextInt(50));
        stack.push(random.nextInt(50));
        System.out.println("Добавляем 2 элемента в стек:");
        stack.display();
        System.out.println("Добавляем 1 элемент в стек:");
        stack.push(random.nextInt(50));
        stack.display();
        System.out.print("Читаем элемент: " + stack.peek());
        System.out.println();
        System.out.print("Извлекаем 5 элементов: " + stack.pop() + " "+ stack.pop() + " "+ stack.pop() + " "+ stack.pop() + " "+ stack.pop() + " ");
        System.out.println("Стек после извлечения:");
        stack.display();
    }
}
