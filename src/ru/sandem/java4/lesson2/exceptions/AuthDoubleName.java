package ru.sandem.java4.lesson2.exceptions;

public class AuthDoubleName extends AuthFailException {
    private String nickname;

    public AuthDoubleName(String nick) {
        super();
        this.nickname = nick;
    }
}
