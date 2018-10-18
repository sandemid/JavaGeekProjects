package sandem.java2.lesson3;

import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class StacksQueueTest {

    static Random random = new Random();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        testStack();
        stringReverse();
        testQueue();
        testDequeue();
    }

    private static void testDequeue() {
        int maxSize = 20;
        MyDequeue dequeue = new MyDequeue(maxSize);
        System.out.println("Создаем дек из 20 элементов");
        System.out.println("Заполняем дек справа, затем слева:");
        for (int i = 0; i < maxSize / 2 + 5; i++) {
            dequeue.addRight(random.nextInt(50));
        }
        dequeue.display();
        while (dequeue.getSize() != maxSize){
            dequeue.addLeft(random.nextInt(50));
        }
        dequeue.display();
        System.out.println("Добавляем еще 2 элемента в дек:");
        dequeue.addRight(random.nextInt(50));
        dequeue.addLeft(random.nextInt(50));
        dequeue.display();
        System.out.println("Удаляем элементы дека слева и справа:");
        dequeue.removeLeft();
        dequeue.removeLeft();
        dequeue.removeLeft();
        dequeue.display();
        dequeue.removeRight();
        dequeue.removeRight();
        dequeue.removeRight();
        dequeue.display();
        dequeue.removeLeft();
        dequeue.removeLeft();
        dequeue.removeLeft();
        dequeue.removeLeft();
        dequeue.display();
        System.out.println("Дозаполняем дек слева:");
        while (dequeue.getSize() != maxSize){
            dequeue.addLeft(random.nextInt(50));
        }
        dequeue.display();
        System.out.println("Удаляем элементы дека справа:");
        for (int i = 0; i < maxSize - 5 ; i++) {
            dequeue.removeRight();
        }
        dequeue.display();
    }

    private static void testQueue() {
        int maxSize = 20;
        MyQueue queue = new MyQueue(maxSize);

        System.out.println("Создаем очередь из 20 элементов");
        System.out.println("Заполняем очередь:");
        for (int i = 0; i < maxSize; i++) {
            queue.addRight(random.nextInt(50));
        }
        queue.display();
        System.out.println("Добавляем еще 2 элемента в очередь:");
        queue.addRight(random.nextInt(50));
        queue.addRight(random.nextInt(50));
        queue.display();
        System.out.println("Добавляем еще 1 элемент в очередь:");
        queue.addRight(random.nextInt(50));
        queue.display();
        System.out.println("Выводим 5 первых элементов:");
        System.out.println(queue.removeLeft());
        System.out.println(queue.removeLeft());
        System.out.println(queue.removeLeft());
        System.out.println(queue.removeLeft());
        System.out.println(queue.removeLeft());
        queue.display();
        System.out.println("Дозаполняем очередь до максимального размера:");
        while (queue.getSize() != maxSize){
            queue.addRight(random.nextInt(50));
        }
        queue.display();
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
