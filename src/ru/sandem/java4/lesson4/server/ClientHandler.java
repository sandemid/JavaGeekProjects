package ru.sandem.java4.lesson4.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sandem.java4.lesson4.exceptions.AuthDoubleName;
import ru.sandem.java4.lesson4.exceptions.AuthFailException;
import ru.sandem.java4.lesson4.exceptions.AuthWrongPassword;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public class ClientHandler implements Runnable {

    private static final String CMD_END = "end";
    private static final String CH_NICK_PREFIX = "CHNICK";
    private Socket socket;
    private Server server;

    private static final Logger logger = LogManager.getLogger(ClientHandler.class.getName());

    private DataOutputStream out;
    private DataInputStream in;

    private static int clientsCount = 0;
    private String clientName;

    private static final String AUTH_WRONG_PASS = "WRONG_PASS";
    private static final String AUTH_OK = "OK";
    private static final String AUTH_ALREADY_IN_USE = "ALREADY_IN_USE";
    private boolean isAuthOK = false;
    private boolean isKilled = false;

    public  boolean isIsKilled() {
        return isKilled;
    }

    ClientHandler(Socket socket, Server server) {
        try {
            this.socket = socket;
            this.server = server;

            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());

            clientsCount++;
            clientName = "client" + clientsCount;

            System.out.println("Client \"" + clientName + "\" ready!");
            logger.info("Client \"" + clientName + "\" ready!");

        } catch (IOException e) {
            System.out.println(e.getMessage());
            logger.fatal(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        if (!socket.isInputShutdown()) {
            waitForAuth();
            waitForMessage();
        }
    }

    public void setAuthOK(boolean authOK) {
        isAuthOK = authOK;
    }

    public boolean isAuthOK() {
        return isAuthOK;
    }

    private void waitForMessage() {
        while (true) {
            String message = null;
            try {
                message = in.readUTF();
            } catch (IOException e) {
                if (isKilled){
                    return;
                } else {
                    e.printStackTrace();
                    logger.fatal(e.getMessage());
                }
            }
            if (isQuitCmd(message)) {
                killClient();
            } else if (isChangeNickCmd(message)) {
                try {
                    String[] parsedMessage = message.split("___",2);
                    if (SQLHandler.changeNick(clientName, parsedMessage[1])) {
                        new Thread(new MessagesSender(clientName + " changed nick on " + parsedMessage[1], this, server)).start();
                        System.out.println(clientName + " changed nick on " + parsedMessage[1]);
                        logger.info(clientName + " changed nick on " + parsedMessage[1]);
                        clientName = parsedMessage[1];
                        continue;
                    } else {
                        new Thread(new MessagesSender("nick change failed", this, server)).start();
                        continue;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    logger.fatal(e.getMessage());
                }
            } else {
                System.out.println(clientName + ": " + message);
            }

            new Thread(new MessagesSender(message, this, server)).start();
        }
    }

    public void killClient() {
        try {
            in.close();
            out.close();
            socket.close();
            isKilled = true;
        } catch (IOException e) {
            e.printStackTrace();
            logger.fatal(e.getMessage());
        }
        server.removeClient(this);
        Thread.currentThread().interrupt();
    }

    private void waitForAuth() {

        while (true) {
            String message = null;
            try {
                message = in.readUTF();
            } catch (IOException e) {
                if (isKilled){
                    return;
                } else {
                    e.printStackTrace();
                    logger.fatal(e.getMessage());
                }
            }
            if (isAuthOk(message)) {
                System.out.println(clientName + " auth ok and is ready for chat!");
                try {
                    out.writeUTF(AUTH_OK);
                } catch (IOException e1) {
                    e1.printStackTrace();
                    logger.fatal(e1.getMessage());
                }
                break;
            } else if (isQuitCmd(message)) {
                killClient();
            }
        }
    }

    private boolean isQuitCmd(String message) {
        return CMD_END.equalsIgnoreCase(message);
    }

    private boolean isChangeNickCmd(String message) {
        String[] parsedMessage = message.split("___",2);
        return CH_NICK_PREFIX.equals(parsedMessage[0]);
    }

    private boolean isAuthOk(String message) {
        if (message != null) {
            String[] parsedMessage = message.split("___");
            if (parsedMessage.length == 3) {
                try {
                    processAuthMessage(parsedMessage);
                } catch (AuthDoubleName ee) {
                    return getErrorMessage(ee, AUTH_ALREADY_IN_USE);
                } catch (AuthFailException e) {
                    return getErrorMessage(e, AUTH_WRONG_PASS);
                }
                isAuthOK = true;
                return true;
            }
        }
        return false;
    }

    private boolean getErrorMessage(AuthFailException e, String errorMessage) {
        System.out.println(e.getFailInfo());
        try {
            out.writeUTF(errorMessage);
        } catch (IOException e1) {
            e1.printStackTrace();
            logger.fatal(e1.getMessage());
        }
        return false;
    }

    private void processAuthMessage(String[] parsedMessage) throws AuthFailException {
        if (parsedMessage[0].equals("auth")) {
            System.out.println("Auth message from " + clientName);
            String login = parsedMessage[1];
            String password = parsedMessage[2];

            String nick = null;
            try {
                nick = SQLHandler.getNick(login, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (nick != null) {
                try {
                    server.addClient(this, nick);
                } catch (AuthDoubleName e) {
                    throw new AuthDoubleName(nick);
                }
                return;
            }

            throw new AuthWrongPassword(login, password);
        }
    }


    DataOutputStream getOut() {
        return out;
    }

    String getClientName() {
        return clientName;
    }

    void setNick(String nick) {
        this.clientName = nick;
    }
}
