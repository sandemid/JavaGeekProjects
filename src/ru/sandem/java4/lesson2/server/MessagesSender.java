package ru.sandem.java4.lesson2.server;

public class MessagesSender implements Runnable {
    private String message;
    private ClientHandler client;
    private Server server;

    public MessagesSender(String message, ClientHandler client, Server server) {
        this.message = message;
        this.client = client;
        this.server = server;
    }

    @Override
    public void run() {
        server.newMessageFromClient(this.message, client);
    }
}
