package ru.sandem.java3.lesson7.server;

import ru.sandem.java3.lesson7.utils.Properties;

import java.io.*;
import java.net.Socket;

public class ChatServerClient implements Runnable{

    private BufferedReader socketReader;
    private BufferedWriter socketWriter;
    private ChatServer server;
    private String clientID;


    public ChatServerClient(Socket socket, ChatServer serverSocket) throws IOException {
        this.server = serverSocket;
        socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    public String getClientID() {
        return clientID;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String message = socketReader.readLine();
                if (Properties.HALT_MESSAGE.equalsIgnoreCase(message)){
                    socketReader.close();
                    socketWriter.close();
                    server.getClients().remove(this);
                    return;
                } else {
                    String[] subMessage = message.split(" ");
                    if (subMessage[Properties.INCLIENT_POSITION].equalsIgnoreCase("/id")) {
                        this.clientID = subMessage[Properties.PREFIX_POSITION];
                        socketWriter.flush();
                    } else if(subMessage[Properties.PREFIX_POSITION].equalsIgnoreCase("/w")) {
                        server.sendPersonalMessage(subMessage, this.clientID);
                    } else {
                        server.sendMessageForAll(message);
                    }
                }
            }
        } catch (IOException ioe) {
            server.getClients().remove(this);
            System.err.println("Server error: " + ioe.getMessage());
            ioe.printStackTrace();
        }

    }

    public void sendMessage(String message) {
        try {
            socketWriter.write(message + "\n");
            socketWriter.flush();
        } catch (IOException ioe) {
            System.err.println("Server error: " + ioe.getMessage());
            ioe.printStackTrace();
        }
    }
}
