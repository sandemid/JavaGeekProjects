package ru.sandem.java3.lesson5;

public class Main {

    private static final int ARRAY_SIZE = 100000000;
    private static final int HALF_SIZE = ARRAY_SIZE / 2;
    private static final float DEFAULT_VALUE = 1f;

    public static void main(String[] args) {
        arrayModify1();
        arrayModify2();
    }

    private static float[] initializeArray() {
        float[] arr = new float[ARRAY_SIZE];
        for (int i = 0; i < ARRAY_SIZE; i++) {
            arr[i] = DEFAULT_VALUE;
        }
        return arr;
    }

    private static void calculate(float[] arr, int startIndex, int endIndex) {
        for (int i = startIndex; i <= endIndex; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5)
                    * Math.cos(0.2f + i / 5)
                    * Math.cos(0.4f + i / 2));
        }
    }

    public static void arrayModify1 (){
        float[] arr = initializeArray();
        long a = System.currentTimeMillis();
        calculate(arr,0 , ARRAY_SIZE - 1);
        System.out.println("Время выполнения 1 потока мс = " + (System.currentTimeMillis() - a));
    }

    public static void arrayModify2 (){
        float[] arr = initializeArray();
        float[] arr1 = new float[ARRAY_SIZE];
        float[] arr2 = new float[ARRAY_SIZE];

        long a = System.currentTimeMillis();
        System.arraycopy(arr,0, arr1, 0, HALF_SIZE);
        System.arraycopy(arr, HALF_SIZE, arr2, HALF_SIZE, HALF_SIZE);

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                calculate(arr1, 0, HALF_SIZE - 1);
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                calculate(arr2, HALF_SIZE, ARRAY_SIZE - 1);
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(arr1, 0, arr, 0, HALF_SIZE);
        System.arraycopy(arr2, HALF_SIZE, arr, HALF_SIZE, HALF_SIZE);

        System.out.println("Время выполнения 2 потоков мс = " + (System.currentTimeMillis() - a));
//        display(arr);
    }

    public static void display(float[] arr){

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
