package ru.sandem.java4.lesson6;

public class CheckArray {

    public CheckArray() {
        super();
    }

    public boolean checkArray(int... array) {

        final int numForCheck1 = 1;
        final int numForCheck2 = 4;
        boolean checkNum1 = false;
        boolean checkNum2 = false;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == numForCheck1) {
                checkNum1 = true;
                continue;
            }
            if (array[i] == numForCheck2) {
                checkNum2 = true;
                continue;
            }
            return false;
        }

        if (checkNum1 && checkNum2) {
            return true;
        } else {
            return false;
        }
    }
}
