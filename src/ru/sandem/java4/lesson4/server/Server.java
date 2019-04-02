package ru.sandem.java4.lesson4.server;

import ru.sandem.java4.lesson4.exceptions.AuthDoubleName;
import ru.sandem.java4.lesson4.exceptions.AuthFailException;
import ru.sandem.java4.lesson4.filters.ChatFilter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Server {
    private static final String MSG_END = "end session";
    private static volatile Server instance;

    private List<ClientHandler> clients;

    private List<ChatFilter> filters;

    private ServerSocket serverSocket = null;

    private static final long WAITING_TIMEOUT = 20000;

    public void addFilter(ChatFilter filter) {
        filters.add(filter);
        System.out.println("Filter is added!");
    }

    public synchronized void addClient(ClientHandler clientHandler, String nick) throws AuthFailException {
        for(ClientHandler client : clients) {
            if (client.getClientName().equals(nick)) {
                System.out.println("Client with nick " + nick + " is already exists!");
                throw new AuthDoubleName(nick);
            }
        }
        clientHandler.setNick(nick);
        clients.add(clientHandler);
        System.out.println(clientHandler.getClientName() + " is added to subscribers list!");
    }

    public Server(int serverPort, String dbName) {
        System.out.println("Server init start.");
        clients = new LinkedList<>();
        filters = new ArrayList<>();

        try {
            serverSocket = new ServerSocket(serverPort);
            System.out.println("Server socket init OK.");

            SQLHandler.connect(dbName);
            System.out.println("Server DB init OK.");

            System.out.println("Server ready and waiting for clients...");
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void waitForClient() {
        Socket socket = null;
        try {
            while(true) {
                socket = serverSocket.accept();
                System.out.println("Client connected.");
                ClientHandler client = new ClientHandler(socket, this);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        new Thread(client).start();
                        try {
                        Thread.sleep(WAITING_TIMEOUT);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (!client.isAuthOK()  && !client.isIsKilled()) {
                            newMessageFromClient(MSG_END, client);
                            client.killClient();
                        }
                    }
                }).start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
                System.out.println("Server closed.");
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void newMessageFromClient(String message, ClientHandler client) {

        if (message.equalsIgnoreCase(MSG_END)){
            try {
                    client.getOut().writeUTF(message);
                    client.getOut().flush();
                    return;
                } catch (IOException e) {
                    e.printStackTrace();

            }

        }

        for (ChatFilter filter : filters) {
            message = filter.filter(message);
        }
        for(ClientHandler cl : clients) {
            try {
                cl.getOut().writeUTF(client.getClientName() + ": " + message);
                cl.getOut().flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void removeClient(ClientHandler clientHandler) {
        try {
            clients.remove(clientHandler);
            System.out.println(clientHandler.getClientName() + " is closed");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
