package ru.sandem.java3.lesson6.clientwindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientWindow extends JFrame {

    private JTextField messageTextField;
    private JTextArea chatTextArea;
    private JButton sendButton;
    private static final String SERVER_ADDRES = "localhost";
    private static final int SERVER_PORT = 8080;

    private Socket socket;
    private Scanner scanner;
    private PrintWriter out;

    public ClientWindow() {
        try {
            socket = new Socket(SERVER_ADDRES, SERVER_PORT);
            scanner = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        setBounds(400, 400, 400, 400);
        setTitle("My first chat");
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

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
                messageTextField.grabFocus();
            }
        });

        messageTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        if (scanner.hasNext()) {
                            String message = scanner.nextLine();
                            chatTextArea.append(message);
                            chatTextArea.append("\n");
                        }
                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    out.println("end");
                    out.flush();
                    socket.close();
                    out.close();
                    scanner.close();
                } catch (IOException ioe) {
                    System.err.println(ioe.getMessage());
                }
            }
        });


        setVisible(true);
    }

    private void sendMessage() {
        out.println(messageTextField.getText());
        out.flush();
        messageTextField.setText("");
    }

}
