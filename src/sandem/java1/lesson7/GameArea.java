package sandem.java_1.lesson7;

import javax.swing.*;
import java.awt.*;

/**
 * Класс для создания игрового поля
 */
public class GameArea extends JFrame {

    private final int DEFAULT_SIZE_X = 450;
    private final int DEFAULT_SIZE_Y = 450;
    private JComboBox combo1, combo2;
    private JButton[] buttons;

    public GameArea(int size){
        super("Крестики-нолики v1.0");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(550, 250);
        setSize(DEFAULT_SIZE_X, DEFAULT_SIZE_Y);
        setPreferredSize(new Dimension(DEFAULT_SIZE_X, DEFAULT_SIZE_Y));
        setResizable(false);

        buttons = new JButton[size * size + 1];
        GamePanel panel = new GamePanel(size);
        setContentPane(panel);
        getContentPane().setBounds(600, 300, DEFAULT_SIZE_X, DEFAULT_SIZE_Y);

        pack();
        setVisible(true);
    }

    private class GamePanel extends JPanel {

        public GamePanel(int size) {
            JPanel panelButton = new JPanel();
            JPanel panelCommand = new JPanel();

            for (int i = 1; i < buttons.length; i++) {
                buttons[i] = new JButton();
                panelButton.add(buttons[i]);
            }
            panelButton.setLayout(new GridLayout(size, size, 0, 0));
            panelButton.setBounds(10, 10, 105, 105);

            String[] items1 = {"X", "O"};
            combo1 = new JComboBox(items1);
            String[] items2 = {"Человек", "ПК"};
            combo2 = new JComboBox(items2);
            buttons[0] = new JButton("Начать игру");
            panelCommand.add(buttons[0]);
            panelCommand.add(new JLabel("Ваш символ:"));
            panelCommand.add(combo1);
            panelCommand.add(new JLabel("Ходит первым:"));
            panelCommand.add(combo2);

            setLayout(new BorderLayout());
            add(panelButton, BorderLayout.CENTER);
            add(panelCommand, BorderLayout.NORTH);
        }

    }

    public JButton[] getButtons() {
        return buttons;
    }

    public JComboBox getCombo1() {
        return combo1;
    }

    public JComboBox getCombo2() {
        return combo2;
    }
}