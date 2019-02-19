package ru.sandem.java3.lesson8;

import ru.sandem.java3.lesson8.client.ClientWindow;

public class StartClients {

    public static final String SERVER_HOST = "localhost"; //127.0.0.1
    public static final int SERVER_PORT = 9933;

    public static void main(String[] args) {
        new ClientWindow(SERVER_HOST, SERVER_PORT);
//        new ClientWindow(SERVER_HOST, SERVER_PORT);
    }
}
