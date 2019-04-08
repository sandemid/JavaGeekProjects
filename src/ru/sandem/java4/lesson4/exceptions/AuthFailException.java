package ru.sandem.java4.lesson4.exceptions;

public class AuthFailException extends Exception {
    static private final String AUTH_ERROR = "Authorisation error";

    public String getFailInfo() {
        return AUTH_ERROR;
    }
}
