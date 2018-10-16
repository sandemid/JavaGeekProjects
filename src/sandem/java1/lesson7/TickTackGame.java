package sandem.java_1.lesson7;

import javax.swing.*;

public class TickTackGame {

    public final int GAME_SIZE = 3;
    final public int TYPE_ROW = 0;
    final public int TYPE_COLUMN = 1;
    final public int TYPE_DIAG1 = 2;
    final public int TYPE_DIAG2 = 3;
    final public String DOT_EMPTY = "\u0000";
    private boolean gameStart = false;
    private GameArea area;
    private Player player;
    private boolean currentTurn;
    private int[][] gameState;
    private String[][] textArea;

    public TickTackGame() {
        this.area = new GameArea(GAME_SIZE);
        this.player = new Player();
        this.textArea = new String[GAME_SIZE][GAME_SIZE];
        this.gameState = new int[GAME_SIZE * 2 + 2][5];

        for (int i = 0; i < GAME_SIZE; i++) {
            gameState[i][1] = TYPE_ROW;
            gameState[i + GAME_SIZE][1] = TYPE_COLUMN;
        }
        gameState[GAME_SIZE*2][1] = TYPE_DIAG1;
        gameState[GAME_SIZE*2+1][1] = TYPE_DIAG2;
    }

    public void initGame() {
        for (int i = 0; i < this.area.getButtons().length; i++) {
            this.area.getButtons()[i].addActionListener(new GameFieldListener(this, this.area.getButtons()[i],i));
            if (i > 0) this.area.getButtons()[i].setText(DOT_EMPTY);
        }
    }

    public void clearField(){
        for (int i = 1; i < this.area.getButtons().length; i++) {
            this.area.getButtons()[i].setText(DOT_EMPTY);
        }
    }

    public boolean isTurnable (int i){
        boolean result = false;
        if (this.getArea().getButtons()[i].getText() != this.getPlayer().getHumanSymbol() && this.getArea().getButtons()[i].getText() != this.getPlayer().getAiSymbol()){
            result = true;
        }
        return result;
    }

    public boolean changeTurn(boolean bol) {

        currentTurn = bol ? !currentTurn : currentTurn;
        return bol;

    }

    public String[][] getTextArea() {
        return textArea;
    }

    public void setTextArea() {
        for (int i = 1; i <= GAME_SIZE * GAME_SIZE; i++) {
            this.textArea[(i - 1) / (GAME_SIZE)][(i - 1) % (GAME_SIZE)] = getArea().getButtons()[i].getText();
        }
    }
    //заполняет массив, в котором перебраны все комбинации на поле, и указано сколько каких символов в каждой
    public void setGameState() {

        int rowSumUser, rowSumAI, rowSumEmpty, colSumUser, colSumAi, colSumEmpty;
        int diagSumUser1 = 0;
        int diagSumAI1 = 0;
        int diagSumEmpty1 = 0;
        int diagSumUser2 = 0;
        int diagSumAI2 = 0;
        int diagSumEmpty2 = 0;

        for (int i = 0; i < GAME_SIZE; i++) {
            rowSumUser = 0;
            rowSumAI = 0;
            rowSumEmpty = 0;
            colSumUser = 0;
            colSumAi = 0;
            colSumEmpty = 0;
            for (int j = 0; j < GAME_SIZE; j++) {
                if (this.textArea[i][j] == getPlayer().getHumanSymbol()) {
                    rowSumUser++;
                } else if (this.textArea[i][j] == getPlayer().getAiSymbol()) {
                    rowSumAI++;
                } else  {
                    rowSumEmpty++;
                }
                if (this.textArea[j][i] == getPlayer().getHumanSymbol()) {
                    colSumUser++;
                } else if (this.textArea[j][i] == getPlayer().getAiSymbol()){
                    colSumAi++;
                } else {
                    colSumEmpty++;
                }
                if (i == j && this.textArea[i][j] == getPlayer().getHumanSymbol()) {
                    diagSumUser1++;
                } else if (i == j && this.textArea[i][j] == getPlayer().getAiSymbol()) {
                    diagSumAI1++;
                } else if (i == j){
                    diagSumEmpty1++;
                }
                if (this.textArea[i][j] == getPlayer().getHumanSymbol() && (i == GAME_SIZE - j - 1)) {
                    diagSumUser2++;
                } else if (this.textArea[i][j] == getPlayer().getAiSymbol() && (i == GAME_SIZE - j - 1)) {
                    diagSumAI2++;
                } else if (i == GAME_SIZE - j - 1){
                    diagSumEmpty2++;
                }
            }
            this.gameState[i][0] = i;
            this.gameState[i][2] = rowSumUser;
            this.gameState[i][3] = rowSumAI;
            this.gameState[i][4] = rowSumEmpty;
            this.gameState[i+GAME_SIZE][0] = i;
            this.gameState[i+GAME_SIZE][2] = colSumUser;
            this.gameState[i+GAME_SIZE][3] = colSumAi;
            this.gameState[i+GAME_SIZE][4] = colSumEmpty;
        }

        this.gameState[GAME_SIZE*2][0] = 1;
        this.gameState[GAME_SIZE*2][2] = diagSumUser1;
        this.gameState[GAME_SIZE*2][3] = diagSumAI1;
        this.gameState[GAME_SIZE*2][4] = diagSumEmpty1;
        this.gameState[GAME_SIZE*2 + 1][0] = 2;
        this.gameState[GAME_SIZE*2 + 1][2] = diagSumUser2;
        this.gameState[GAME_SIZE*2 + 1][3] = diagSumAI2;
        this.gameState[GAME_SIZE*2 + 1][4] = diagSumEmpty2;
    }

    public int[][] getGameState() {
        return gameState;
    }

    public void setCurrentTurn(boolean currentTurn) {
        this.currentTurn = currentTurn;
    }

    public boolean isCurrentTurn() {
        return currentTurn;
    }

    public boolean isEndGame(String symbol) {
        boolean result = false;

        if (isWinGame()) {
            JOptionPane.showMessageDialog(this.getArea(),"Победил символ " + symbol,"Конец игры!",JOptionPane.WARNING_MESSAGE);
            result = true;
        } else if (isAreaFull()) {
            JOptionPane.showMessageDialog(this.getArea(),"Ничья","Конец игры!",JOptionPane.WARNING_MESSAGE);
            result = true;
        }

        return result;
    }

    public boolean isWinGame(){
        boolean result = false;

        setTextArea();
        setGameState();

        for (int i = 0; i < GAME_SIZE * 2 + 2; i++) {
            if (this.gameState[i][2] == GAME_SIZE || this.gameState[i][3] == GAME_SIZE) {
                result = true;
                break;
            }
        }

        return result;
    }

    public boolean isAreaFull(){
        boolean result = true;

        for (int i = 1; i <= GAME_SIZE * GAME_SIZE ; i++) {
            if (this.getArea().getButtons()[i].getText() != this.getPlayer().getHumanSymbol() && this.getArea().getButtons()[i].getText() != this.getPlayer().getAiSymbol()) {
                result = false;
                break;
            }
        }

        return result;
    }

    public GameArea getArea() {
        return area;
    }

    public boolean isGameStart() {
        return gameStart;
    }

    public void setGameStart(boolean gameStart) {
        this.gameStart = gameStart;
    }

    public Player getPlayer() {
        return player;
    }
}