package ru.sandem.java3.lesson7.client;

import ru.sandem.java3.lesson7.utils.Properties;

public class Client {
    public static void main(String[] args) {
        String clientName = "client_3";
        ChatClient client = new ChatClient(Properties.HOST, Properties.PORT, clientName);
        client.setOutPrintStream(System.out);
        new Thread(client).start();
        new ClientWindow(clientName, client);
    }
}
