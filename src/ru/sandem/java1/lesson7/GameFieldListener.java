package ru.sandem.java1.lesson7;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFieldListener implements ActionListener {

    private TickTackGame game;
    private JButton button;
    private int index;

    public GameFieldListener (TickTackGame _game, JButton _button, int _index){
        this.game = _game;
        this.button = _button;
        this.index = _index;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Начать игру") {
            game.setGameStart(true); //игра началась, устанавливаем символы игроков согласно заданным на поле
            game.getPlayer().setHumanSymbol(game.getArea().getCombo1().getSelectedItem().toString());
            game.getPlayer().setAiSymbol(game.getArea().getCombo1().getItemAt(Math.abs(game.getArea().getCombo1().getSelectedIndex() - 1)).toString());
            game.clearField();//очищаем поле
            if (game.getArea().getCombo2().getSelectedIndex() == 1) {//если первый ходит ПК, запускаем ход ПК
                game.setCurrentTurn(true);
                game.changeTurn(game.getPlayer().playerTurn(game,game.isCurrentTurn(), index));
            } else {
                game.setCurrentTurn(false);
            }
        } else if (game.isGameStart()){
            if (game.changeTurn(game.getPlayer().playerTurn(game,game.isCurrentTurn(),index))) {//ход игрока
                if (game.isEndGame(game.getPlayer().getHumanSymbol())) { //проверка конца игры
                    game.setGameStart(false);
                } else {
                    game.changeTurn(game.getPlayer().playerTurn(game,game.isCurrentTurn(),index));
                    if (game.isEndGame(game.getPlayer().getAiSymbol())) {
                        game.setGameStart(false);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(game.getArea(),"Клетка уже заполнена!","Ошибка",JOptionPane.ERROR_MESSAGE);
            }
        } else {
            //если игра не началась, ничего не делаем при нажатии кнопок поля
        }
    }


}