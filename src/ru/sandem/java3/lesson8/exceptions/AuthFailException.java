package ru.sandem.java3.lesson8.exceptions;

public class AuthFailException extends Exception {
    static private final String AUTH_ERROR = "Authorisation error";

    public String getFailInfo() {
        return AUTH_ERROR;
    }
}
