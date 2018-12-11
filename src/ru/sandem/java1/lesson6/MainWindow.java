package ru.sandem.java1.lesson6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {

    private final int FRAME_WIDTH = 350;
    private final int FRAME_HEIGHT = 350;
    private final String PLUS_COMMAND = "A + B";
    private final String MINUS_COMMAND = "A - B";
    private final String DIV_COMMAND = "A / B";
    private final String MULTI_COMMAND = "A * B";
    private final String EXPO_COMMAND = "A в степени B";
    private final String RESET_COMMAND = "reset";
    private Double fieldA, fieldB, fieldC;
    private Integer fieldBInt;
    private JPanel panelButton = new JPanel();//Панели
    private JPanel panelText = new JPanel();
    private JPanel panelLabel = new JPanel();
    private JTextField inputText_1 = new JTextField(5); //Поля ввода-вывода
    private JTextField inputText_2 = new JTextField(5);
    private JTextField outputText = new JTextField(5);

    public MainWindow(){
        setTitle("Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        ActionListener operation = new insertOperation();

        //Инициализируем кнопки и метки
        addButton(PLUS_COMMAND, operation);
        addButton(MINUS_COMMAND, operation);
        addButton(DIV_COMMAND, operation);
        addButton(MULTI_COMMAND, operation);
        addButton(EXPO_COMMAND, operation);
        addButton(RESET_COMMAND, operation);
        addLabel("Поле ввода величины А:");
        addLabel("Поле ввода величины B:");
        addLabel("Результат операции: ");
        //Инициализируем менеджеры размещения для каждой панели
        GridLayout layout1 = new GridLayout(0, 2, 0, 0 );
        GridLayout layout2 = new GridLayout(0, 1, 0, 0 );
        GridLayout layout3 = new GridLayout(0, 1, 0, 0 );

        panelButton.setLayout(layout1);
        panelText.setLayout(layout2);
        panelLabel.setLayout(layout3);
        outputText.setEditable(false);

        //Помещаем поля ввода-вывода в панели
        panelText.add(inputText_1);
        panelText.add(inputText_2);
        panelText.add(outputText);

        //Задаем положение и размер панелей
        panelButton.setBounds(10, FRAME_HEIGHT - 150, FRAME_WIDTH - 38, FRAME_HEIGHT / 4);
        panelText.setBounds(FRAME_WIDTH / 2 + 25, 25, FRAME_WIDTH / 3, FRAME_HEIGHT / 3);
        panelLabel.setBounds(10, 25, FRAME_WIDTH / 2, FRAME_HEIGHT / 3);

        //Помещаем панели содержимого в многослойную панель
        getLayeredPane().add(panelButton, BorderLayout.SOUTH);
        getLayeredPane().add(panelText, BorderLayout.EAST);
        getLayeredPane().add(panelLabel, BorderLayout.WEST);

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocation(600,200);
        setResizable(false);
        setVisible(true);
    }
    //Рекурсивный метод вычисления целой степени числа
    private Double getExponential(Double a, Integer b) {
        Double result;

        if (b == 0) {
            result = new Double(1);
        } else {
            result = a * getExponential(a, b - 1);
        }

        return result;
    }
    //метод добавления кнопок на панель сразу с отслеживанием событий
    private void addButton(String label, ActionListener action) {
        JButton button = new JButton(label);
        button.addActionListener(action);
        panelButton.add(button);
    }
    //метод добавления текстовых меток на панель меток
    private void addLabel (String text) {
        JLabel label = new JLabel(text);
        panelLabel.add(label);
    }
    //метод-обработчик событий на кнопках
    private class insertOperation implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String operation = ((JButton) e.getSource()).getText(); //можно также e.getActionCommand();
            try {
                if (operation != EXPO_COMMAND && operation != RESET_COMMAND) {
                    fieldA = Double.parseDouble(inputText_1.getText());
                    fieldB = Double.parseDouble(inputText_2.getText());
                    switch (operation) {
                        case PLUS_COMMAND:
                            fieldC = fieldA + fieldB;
                            break;
                        case MINUS_COMMAND:
                            fieldC = fieldA - fieldB;
                            break;
                        case DIV_COMMAND:
                            fieldC = fieldA / fieldB;
                            break;
                        case MULTI_COMMAND:
                            fieldC = fieldA * fieldB;
                            break;
                    }
                    outputText.setText(fieldC.toString());
                } else if (operation == EXPO_COMMAND) {
                    fieldA = Double.parseDouble(inputText_1.getText()); // можно Double.valueOf()
                    fieldBInt = Integer.parseInt(inputText_2.getText());
                    fieldC = (fieldBInt >= 0) ? getExponential(fieldA, fieldBInt) : 1 / getExponential(fieldA, Math.abs(fieldBInt));
                    outputText.setText(fieldC.toString());

                } else {
                    outputText.setText("");
                }
            } catch (NumberFormatException er) {
                JOptionPane.showMessageDialog(getComponent(0),"Некорректно введены аргументы!","Ошибка",JOptionPane.ERROR_MESSAGE);
            } catch (StackOverflowError er) {
                JOptionPane.showMessageDialog(getComponent(0),"Слишком большая степень!","Ошибка",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
 //       JFrame.setDefaultLookAndFeelDecorated(true);
        new MainWindow();
    }
}