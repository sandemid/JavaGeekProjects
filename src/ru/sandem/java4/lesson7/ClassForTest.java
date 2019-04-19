package ru.sandem.java4.lesson7;

public class ClassForTest {

    private String name;

    public ClassForTest(String name) {
        this.name = name;
    }

    private boolean checkStringsLength(String... strings) {
        int sum = 0;
        for (int i = 0; i < strings.length; i++) {
            sum += strings[i].length();
        }
        return  sum > 30;
    }

    private int getFactorial(int i) throws IllegalArgumentException {
        if (i <= 0) {
            throw new IllegalArgumentException("Parameter must be 1 <= i <= 12!");
        }
        if (i == 1) {
            return i;
        } else if (i <= 12) {
            return i * getFactorial(i - 1);
        } else {
            throw new IllegalArgumentException("Parameter must be 1 <= i <= 12!");
        }
    }
}
