package ru.sandem.java4.lesson2.exceptions;

public class AuthFailException extends Exception {
    static private final String AUTH_ERROR = "Authorisation error";

    public String getFailInfo() {
        return AUTH_ERROR;
    }
}
