package ru.sandem.java4.lesson4.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final String MSG_END = "end session";
    private static volatile Server instance;

    private List<ClientHandler> clients;

    private List<ChatFilter> filters;

    private ServerSocket serverSocket = null;

    private static final long WAITING_TIMEOUT = 20000;

    private static final Logger logger = LogManager.getLogger(Server.class.getName());

    public void addFilter(ChatFilter filter) {
        filters.add(filter);
        System.out.println("Filter is added!");
        logger.info("Filter is added!");
    }

    public synchronized void addClient(ClientHandler clientHandler, String nick) throws AuthFailException {
        for(ClientHandler client : clients) {
            if (client.getClientName().equals(nick)) {
                System.out.println("Client with nick " + nick + " is already exists!");
                logger.warn("Client with nick " + nick + " is already exists!");
                throw new AuthDoubleName(nick);
            }
        }
        clientHandler.setNick(nick);
        clients.add(clientHandler);
        System.out.println(clientHandler.getClientName() + " is added to subscribers list!");
        logger.info(clientHandler.getClientName() + " is added to subscribers list!");
    }

    public Server(int serverPort, String dbName) {
        System.out.println("Server init start.");
        logger.info("Server init start.");
        clients = new LinkedList<>();
        filters = new ArrayList<>();

        try {
            serverSocket = new ServerSocket(serverPort);
            System.out.println("Server socket init OK.");
            logger.info("Server socket init OK.");

            SQLHandler.connect(dbName);
            System.out.println("Server DB init OK.");
            logger.info("Server DB init OK.");

            System.out.println("Server ready and waiting for clients...");
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            logger.fatal("Server initialisation exception");
        }
    }

    public void waitForClient() {
        Socket socket = null;
        try {
            while(true) {
                socket = serverSocket.accept();
                System.out.println("Client connected.");
                logger.info("Client connected.");
                ClientHandler client = new ClientHandler(socket, this);
                ExecutorService service = Executors.newCachedThreadPool();
                service.submit(new Thread(() -> {
                    new Thread(client).start();
                    try {
                        Thread.sleep(WAITING_TIMEOUT);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        logger.info("InterruptedException in thread");
                    }
                    if (!client.isAuthOK()  && !client.isIsKilled()) {
                        newMessageFromClient(MSG_END, client);
                        client.killClient();
                    }
                }));
                service.shutdown();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
                System.out.println("Server closed.");
                logger.info("Server closed.");
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
                logger.info("IOException in waitForClient");
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
                    logger.info("IOException in newMessageFromClient");

            }

        }

        String[] parsedMessage = message.split("___",2);
        if (parsedMessage[0].equalsIgnoreCase("CHATBOT!")) {
            try {
                client.getOut().writeUTF(parsedMessage[1]);
                client.getOut().flush();
                return;
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("IOException in newMessageFromClient");
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
                logger.info("IOException in newMessageFromClient");
            }
        }
    }

    public synchronized void removeClient(ClientHandler clientHandler) {
        try {
            clients.remove(clientHandler);
            System.out.println(clientHandler.getClientName() + " is closed");
            logger.info(clientHandler.getClientName() + " is closed");
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception in removeClient");
        }
    }
}
