package ru.sandem.java3.lesson7.server;

import ru.sandem.java3.lesson7.utils.Properties;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer implements Runnable{
    private ServerSocket serverSocket;
    private List<ChatServerClient> clients = new ArrayList<>();

    public List<ChatServerClient> getClients() {
        return clients;
    }

    public ChatServer(int port) {
        try{
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("Проблемы с портом: " + port);
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        while (true) {
            try{
                ChatServerClient client = new ChatServerClient(serverSocket.accept(), this);
                clients.add(client);
                new Thread(client).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void sendPersonalMessage(String[] message, String inputID) {

        String outID = message[Properties.OUTCLIENT_POSITION];
        String outMessage = message[Properties.INCLIENT_POSITION] + " " ;
        for (int i = Properties.OUTCLIENT_POSITION + 1; i < message.length; i++) {
            outMessage = outMessage + message[i] + " ";
        }

        for (ChatServerClient client : clients) {
            if (inputID.equals(client.getClientID()) || outID.equals(client.getClientID())) {
                client.sendMessage(outMessage);
            }
        }
    }

    public synchronized void sendMessageForAll(String message) {
        for (ChatServerClient client : clients) {
            client.sendMessage(message);
        }
    }
}
