package ru.sandem.java3.lesson7.client;

import ru.sandem.java3.lesson7.utils.Properties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class ClientWindow extends JFrame {


    private JTextField messageTextField;
    private JTextArea chatTextArea;
    private JButton sendButton;

    private ChatClient client;

    public ClientWindow(String title, ChatClient client) {
        this.client = client;
        setBounds(400, 400, 400, 400);
        setTitle(title);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        chatTextArea = new JTextArea();
        chatTextArea.setEditable(false);
        chatTextArea.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane(chatTextArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        sendButton = new JButton("SEND");

        bottomPanel.add(sendButton, BorderLayout.EAST);
        messageTextField = new JTextField();
        bottomPanel.add(messageTextField, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        sendButton.addActionListener(e -> {
            sendMessage();
            messageTextField.grabFocus();
        });

        messageTextField.addActionListener(e -> sendMessage());

        PrintStream printStream = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                chatTextArea.append(String.valueOf((char) b));
            }
        });
        client.setOutPrintStream(printStream);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    client.sendMessage(Properties.HALT_MESSAGE);
                    client.setCLIENT_IS_CLOSED(true);
                    client.getOutPrintStream().flush();
                    client.getOutPrintStream().close();
                    client.getSocketReader();
                    client.getSocketWriter();
                    client.getSocket().close();
                } catch (IOException ioe) {
                    System.err.println(ioe.getMessage());
                }
            }
        });
        client.sendMessage(client.getClientName());
        setVisible(true);
    }

    private void sendMessage() {
        String message = messageTextField.getText();
        if (message == null || message.isEmpty()) {
            return;
        }
        messageTextField.setText("");
        client.sendMessage(message);
    }
}
