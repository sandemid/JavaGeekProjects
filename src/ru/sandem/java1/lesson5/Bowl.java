package ru.sandem.java1.lesson5;

import java.util.Scanner;

/**
 * Класс для Миски
 */

public class Bowl {

    private int volume;
    private final int VOLUME_DEFAULT = 40; //емкость по умолчанию, если в конструктор передано некорректное значение
    private int foodNow;
    private Scanner scanner = new Scanner(System.in);

    public Bowl(int _volume){
        volume = (_volume > 0) ? _volume : VOLUME_DEFAULT;
        foodNow = volume;
    }

    public Bowl() {
        volume = VOLUME_DEFAULT;
        foodNow = VOLUME_DEFAULT;
    }

    public int getVolume() {
        return volume;
    }

    public int getFoodNow() {
        return foodNow;
    }

    public void setVolume(int volume) {
        if (volume > 0) {
            this.volume = volume;
        }
    }

    public boolean setFoodNow(int foodNow) {
        boolean result = true;
        int userInput = -1;

        if (foodNow > 0){
            this.foodNow = foodNow;
        } else if (foodNow == 0){ //если еда закончилась, предлагаем выбор - заполнить или нет
            do {
                System.out.print("Еда в миске закончилась. Наполнить миску? (1 - да, 0 - нет): ");
                userInput = scanner.nextInt();
            } while (userInput != 1 && userInput != 0);
            if (userInput == 1) {
                this.foodNow = this.volume;
            } else {
                this.foodNow = foodNow;
            }
        } else {
            result = false;
        }

        return result;
    }
}
