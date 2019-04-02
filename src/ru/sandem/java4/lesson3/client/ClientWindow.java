package ru.sandem.java4.lesson3.client;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientWindow extends JFrame {

    private static final String AUTH_OK = "OK";
    private static final String AUTH_WRONG_PASS = "WRONG_PASS";
    private static final String AUTH_ALREADY_IN_USE = "ALREADY_IN_USE";
    private static final String WELCOME_TO_CHAT = "Welcome to chat room.";
    private static final String MSG_WRONG_PASSWORD = "Wrong username or password.";
    private static final String MSG_USERNAME_ALREADY_IN_USE = "Username is already in use. Choose another.";
    private static final String CMD_END = "end";
    private static final String MSG_END = "end session";
    private static final String CH_NICK_PREFIX = "CHNICK___";
    private final String fileName = "d:\\client_log.txt";
    private JTextField clientMsgElement;
    private JTextArea serverMsgElement;
    private JPanel authPanel;

    final String serverHost;
    final int serverPort;
    private static boolean isClosed = false;

    Socket socket;
    DataInputStream in;
    DataOutputStream out;

    private File f;
    private FileReader fileReader;
    private BufferedReader bufferedReader;
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private ArrayList<String> log;

    public ClientWindow(String host, int port) {
        serverHost = host;
        serverPort = port;

        initConnection();
        initServerListner();
        initGUI();
        initLogFile();
    }

    private void initLogFile() {
        try {
            f = new File(fileName);
            f.createNewFile();
            fileWriter = new FileWriter(fileName, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
            log = new ArrayList<>();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initConnection() {
        try {
            socket = new Socket(serverHost, serverPort);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initGUI() {
        setBounds(600, 300, 500, 500);
        setTitle("Chat Client");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //for auth
        authPanel = new JPanel(new GridLayout());
        JTextField jtfLogin = new JTextField(); //"Login"
        JTextField jtfPass = new JTextField(); //"Password"
        JButton jbAuth = new JButton("Auth me");
        authPanel.add(jtfLogin);
        authPanel.add(jtfPass);
        authPanel.add(jbAuth);
        jtfLogin.setToolTipText("Enter Login");
        jtfPass.setToolTipText("Enter Password");
        add(authPanel, BorderLayout.NORTH);
        jbAuth.addActionListener(e -> {
            try {
                sendAuthCommand(jtfLogin.getText(), jtfPass.getText());
            } catch (IOException e1) {
                if (isClosed) {
                    return;
                } else {
                    e1.printStackTrace();
                }
            }
        });


        //многострочный элемент для всех сообщений
        serverMsgElement = new JTextArea();
        serverMsgElement.setEditable(false);
        serverMsgElement.setLineWrap(true);
        //автопрокрутка текстовой области
        DefaultCaret caret = (DefaultCaret) serverMsgElement.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        JScrollPane scrollPane = new JScrollPane(serverMsgElement);
        add(scrollPane, BorderLayout.CENTER);
        //альтернативный вариант автопрокрутки
//        scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
//            @Override
//            public void adjustmentValueChanged(AdjustmentEvent e) {
//                e.getAdjustable().setValue(e.getAdjustable().getMaximum());
//            }
//        });

        JPanel bottomPanel = new JPanel(new BorderLayout());
        add(bottomPanel, BorderLayout.SOUTH);

        //кнопка отправки сообщения
        JButton sendButton = new JButton("SEND");
        bottomPanel.add(sendButton, BorderLayout.EAST);
        clientMsgElement = new JTextField();
        bottomPanel.add(clientMsgElement, BorderLayout.CENTER);

        //кнопка смены ника
        JButton changeNickButton = new JButton("CHANGE NICK");
        bottomPanel.add(changeNickButton, BorderLayout.WEST);


        //сменить ник по кнопкке
        changeNickButton.addActionListener(e -> {
            String nick = clientMsgElement.getText();
            if (nick.trim().isEmpty()) {
                return;
            } else {
                try {
                    changeNick(nick);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            clientMsgElement.grabFocus();
        });

        //отправка по кнопке
        sendButton.addActionListener(e -> {
            String message = clientMsgElement.getText();
            if (message.equalsIgnoreCase(CMD_END)) isClosed = true;
            try {
                sendMessage(message);
            } catch (IOException e1) {
                if (isClosed) {
                    return;
                } else {
                    e1.printStackTrace();
                }
            }
            clientMsgElement.grabFocus();
        });

        //отправка по Enter
        clientMsgElement.addActionListener(e -> {
            String message = clientMsgElement.getText();
            try {
                sendMessage(message);
                if (message.equalsIgnoreCase(CMD_END)) isClosed = true;
            } catch (IOException e1) {
                if (isClosed) {
                    return;
                } else {
                    e1.printStackTrace();
                }
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    if (!isClosed) out.writeUTF(CMD_END);
                    out.flush();
                    out.close();
                    in.close();
                    socket.close();
                    isClosed = true;
                    bufferedWriter.close();
                    fileWriter.close();
                } catch (IOException exc) {
                    System.err.println(exc.getMessage());
                    exc.printStackTrace();
                }
            }
        });

        setVisible(true);
    }

    private void changeNick (String nick) throws IOException {
        sendMessage(CH_NICK_PREFIX + nick);
    }

    private void sendAuthCommand(String login, String password) throws IOException {
        String command = "auth___" + login + "___" + password;
        out.writeUTF(command);
        out.flush();
    }

    private void initServerListner() {
        new Thread(() -> {
            try {
                while (true) {
                    String message = in.readUTF();
                    if(message.equalsIgnoreCase(MSG_END)) {
                        isClosed = true;
                        break;
                    }
                    if(message.equals(AUTH_OK)) {
                        authPanel.setVisible(false);
                        printLog();
                        printMessage(WELCOME_TO_CHAT + "\n");
                    }
                    if(message.equals(AUTH_WRONG_PASS)) {
                        // says user about wrong password or username
                        printMessage(MSG_WRONG_PASSWORD + "\n");
                    }
                    else if(message.equals(AUTH_ALREADY_IN_USE)) {
                        // says, that username is already in use
                        printMessage(MSG_USERNAME_ALREADY_IN_USE + "\n");
                    }
                    else {
                        printMessage(message + "\n");
                    }
                }
            } catch (Exception e) {
                if (isClosed) {
                    return;
                } else {
                    System.err.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void printLog() throws Exception {
        String s;
        while ((s = bufferedReader.readLine()) != null) {
            log.add(s);
        }
        for (int i = (log.size() <= 100) ? 0 : log.size() - 100; i < log.size(); i++) {
            serverMsgElement.append(log.get(i) + "\n");
        }
        bufferedReader.close();
        fileReader.close();
    }

    private void printMessage (String message) throws Exception {
        serverMsgElement.append(message);
        bufferedWriter.write(message);
        bufferedWriter.flush();
    }

    private void sendMessage(String message) throws IOException {
        if (!message.trim().isEmpty()) {
            out.writeUTF(message);
            out.flush();
            clientMsgElement.setText("");
        }
    }
}
