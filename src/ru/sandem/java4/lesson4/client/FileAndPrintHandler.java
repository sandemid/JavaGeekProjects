package ru.sandem.java4.lesson4.client;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;


//класс чтения и записи в файл логов, вывода лога на экран клиента
public class FileAndPrintHandler {
    private File f;
    private FileReader fileReader;
    private BufferedReader bufferedReader;
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private ArrayList<String> log;

    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }

    private JTextArea textArea;

    public FileAndPrintHandler(String fileName) {
        try {
            f = new File(fileName);
            f.createNewFile();
            fileWriter = new FileWriter(fileName, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
            log = new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printLog() throws Exception {
        String s;
        while ((s = bufferedReader.readLine()) != null) {
            log.add(s);
        }
        for (int i = (log.size() <= 100) ? 0 : log.size() - 100; i < log.size(); i++) {
            textArea.append(log.get(i) + "\n");
        }
    }

    public void printMessage (String message) throws IOException {
        textArea.append(message);
        bufferedWriter.write(message);
        bufferedWriter.flush();
    }

    public void shutdown () throws IOException {
        bufferedReader.close();
        fileReader.close();
        bufferedReader.close();
        fileWriter.close();
    }
}
