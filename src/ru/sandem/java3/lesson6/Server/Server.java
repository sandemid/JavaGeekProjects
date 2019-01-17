package ru.sandem.java3.lesson6.Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    private static ServerSocket serverSocket = null;

    public static void main(String[] args) {

        Socket client = null;

        try {
            serverSocket = new ServerSocket(8080);
            System.out.println("Сервер ожидает подключения");
            client = serverSocket.accept();
            System.out.println("Клиент подключен");
            Scanner scanner = new Scanner(client.getInputStream());
            Scanner scannerConsole = new Scanner(System.in);
            PrintWriter printWriter = new PrintWriter(client.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        String message = scanner.nextLine();
                        System.out.println("Client: " + message);
                        if ("end".equalsIgnoreCase(message)) {
                            closing();
                        }
                    }
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        String message = scannerConsole.nextLine();
                        printWriter.println(message);
                        printWriter.flush();
                        if ("end".equalsIgnoreCase(message)) {
                            closing();
                        }
                    }
                }
            }).start();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void closing(){
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

}
