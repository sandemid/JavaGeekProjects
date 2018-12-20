package ru.sandem.java3.lesson2;

public class MyArrayDataException extends Exception{

    public MyArrayDataException(String message, int i, int j) {
        super(message + i + ", " + j);
    }
}
