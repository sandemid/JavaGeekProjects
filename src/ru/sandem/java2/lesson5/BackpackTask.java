package ru.sandem.java2.lesson5;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class BackpackTask {

    private int backpackCapacity; //вместимость рюкзака
    private int setSize; //размер набора штук
    private int[][] packageSet; //двумерный массив: вес предмета / стоимость
    private int[] optimal; //бинарный вектор, в котором 0 - предмет не используется, 1 - используется
    private int[] optimalTemp; //для фиксации промежуточного лучшего результата
    private int tempSum = 0;
    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();
    private int count = 0;

    public static void main(String[] args) {
        System.out.print("Введите вместимость рюкзака кг: ");
        BackpackTask backpack = new BackpackTask(scanner.nextInt());
        System.out.print("Введите размер набора: ");
        backpack.setSize = scanner.nextInt();
        backpack.setPackageSet();
        backpack.displaySet();
        backpack.optimal = new int[backpack.setSize];
        backpack.getOptimalSet();
        backpack.displayOptimal();
        System.out.println("Число рекурсивных проходов = " + backpack.count);
    }

    private void displayOptimal() {
        System.out.println("=======================");
        System.out.println("Оптимальный набор предметов");
        System.out.print("Вес:\t\t");
        for (int i = 0; i < setSize ; i++) {
            if (optimalTemp[i] !=0) System.out.print(packageSet[i][0] + "\t");
        }
        System.out.println();
        System.out.print("Стоимость:\t");
        for (int i = 0; i < setSize ; i++) {
            if (optimalTemp[i] !=0)System.out.print(packageSet[i][1] + "\t");
        }
        System.out.println();
    }

    private void getOptimalSet(){

        for (int i = 0; i < setSize; i++) { //добавляем в бинарный вектор по 1 за проход, потом в рекурсии перебираем все комбинации
            optimal[i] = 1;
            if (i <= backpackCapacity) { //для случаев, когда число используемых предметов заведомо
                getOptimalSet(setSize);  //превышает макс. вес, рекурсию не запускаем. Снижает операции в 4 раза для маленького веса и большого числа элементов
            }
        }
    }

    private void getOptimalSet(int size){
        if (size == 1){
            count++;
            return;
        }

        for (int i = 0; i < size; i++) {
            if (!checkEquals(size - 1)) getOptimalSet(size - 1); // если все предыдущие элементы одинаковые, не входим в рекурсию, т.к. нет смысла менять что-то местами. Сокращает число операций в 2 раза
            int t = getSetSum(1);
            if (t > tempSum &&  getSetSum(0) <= backpackCapacity){
                tempSum = t;
                optimalTemp =  Arrays.copyOf(optimal,setSize);
            }
            rotate(size);
            count++;
        }
    }

    private boolean checkEquals(int Size) {
        for (int i = 1; i <= Size; i++) {
            if (optimal[i] != optimal [i - 1]) return false;
        }
        return true;
    }

    private int getSetSum(int index) { //0 возвращает суммарный вес, 1 - суммарную стоимость
        int temp = 0;
        for (int i = 0; i < setSize; i++) {
            temp = temp + packageSet[i][index] * optimal[i];
        }
        return temp;
    }

    private void rotate(int newSize) {
        int temp = optimal[newSize - 1];
        for (int i = newSize - 1; i > 0; i--) {
            optimal[i] = optimal[i - 1];
        }
        optimal[0] = temp;
    }

    private void displaySet() {
        System.out.println("=======================");
        System.out.println("Текущий набор предметов");
        System.out.print("Вес:\t\t");
        for (int i = 0; i < setSize ; i++) {
            System.out.print(packageSet[i][0] + "\t");
        }
        System.out.println();
        System.out.print("Стоимость:\t");
        for (int i = 0; i < setSize ; i++) {
            System.out.print(packageSet[i][1] + "\t");
        }
        System.out.println();
    }

    public void setPackageSet() {
        packageSet = new int[setSize][2];
        for (int i = 0; i < setSize; i++) {
            packageSet[i][0] = random.nextInt(backpackCapacity) + 1;
            packageSet[i][1] = random.nextInt(backpackCapacity * 10) + 1;
        }
        tempSum = 0;
    }

    public BackpackTask(int backpackCapacity) {
        this.backpackCapacity = backpackCapacity;
    }
}
