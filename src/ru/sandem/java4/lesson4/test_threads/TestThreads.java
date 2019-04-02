package ru.sandem.java4.lesson4.test_threads;

public class TestThreads {

    private final Object monitor = new Object();
    private volatile char curLetter = 'A';

    public static void main(String[] args) {
        TestThreads t = new TestThreads();
        Thread t1 = new Thread(() -> {
            t.printA();
        });
        Thread t2 = new Thread(() -> {
            t.printB();
        });
        Thread t3 = new Thread(() -> {
            t.printC();
        });
        t1.start();
        t2.start();
        t3.start();
    }

    private void printC() {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (curLetter != 'C') {
                        monitor.wait();
                    }
                    System.out.print("C");
                    curLetter = 'A';
                    monitor.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void printB() {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (curLetter != 'B') {
                        monitor.wait();
                    }
                    System.out.print("B");
                    curLetter = 'C';
                    monitor.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void printA() {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (curLetter != 'A') {
                        monitor.wait();
                    }
                    System.out.print("A");
                    curLetter = 'B';
                    monitor.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
