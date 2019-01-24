package ru.sandem.java3.lesson8.exceptions;

public class AuthDoubleName extends AuthFailException {
    private String nickname;

    public AuthDoubleName(String nick) {
        super();
        this.nickname = nick;
    }
}
