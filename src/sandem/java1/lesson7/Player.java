package sandem.java_1.lesson7;

import java.util.Random;

/**
 * Класс игрока
 */
public class Player {

    private String humanSymbol;
    private String aiSymbol;
    private Random random = new Random();
    private int[] bestCombination;
    private int emptyIndexX, emptyIndexY;

    public Player () {
    }
    // Принимает экземпляр игры, вызвавшей метод объекта игрока, признак ПК / человек и индекс нажатой кнопки
    public boolean playerTurn (TickTackGame game, boolean isAIturn, int ind) {

        int x = -1;
        int y = -1;
        int k = 0;
        boolean humanAlmostWin = false;
        int humanAlmostWinIndex = -1;
        boolean AIAlmostWin = false;
        int AIAlmostWinIndex = -1;
        int maxAISum = -1;
        int randomIndex;
        boolean result = true;

        if (isAIturn) { //ход ПК
            game.setTextArea(); //заполняем актуальный текстовый массив игрового поля
            game.setGameState();//заполняем в виде массива акутальный статус комбинаций
            //перебираем все комбинации, ищем "почти выигрыщ" ПК и человека, а также комбинацию
            //с максимальным числом символов ПК и без символов человека
            for (int i = 0; i < game.GAME_SIZE * 2 + 2; i++) {
                if (game.getGameState()[i][2] == game.GAME_SIZE - 1 && game.getGameState()[i][4] == 1) {
                    humanAlmostWin = true;
                    humanAlmostWinIndex = i;
                }
                if (game.getGameState()[i][3] == game.GAME_SIZE - 1 && game.getGameState()[i][4] == 1) {
                    AIAlmostWin = true;
                    AIAlmostWinIndex = i;
                }
                if (game.getGameState()[i][3] > maxAISum && game.getGameState()[i][2] == 0) {
                    maxAISum = game.getGameState()[i][3];
                }
            }
// Если раскомментировать, AI будет всегда первым ходом занимать центр поля, если он свободен
// Мне лично так меньше нравится, почти все игры выходят вничью:) Без этого ПК чаще выигрывает
/*            if (game.isTurnable((game.GAME_SIZE / 2) * game.GAME_SIZE + game.GAME_SIZE / 2 + 1)) {
                x = game.GAME_SIZE / 2;
                y = game.GAME_SIZE / 2;
            } else */ if (AIAlmostWin) { //если ПК "почти" выиграл, ходим на выигрыш
                setFirstEmptyIndex(AIAlmostWinIndex,game);
                x = emptyIndexX;
                y = emptyIndexY;
            } else if (humanAlmostWin) { //если человек "почти" выиграл, портим выигрыщ
                setFirstEmptyIndex(humanAlmostWinIndex,game);
                x = emptyIndexX;
                y = emptyIndexY;
            } else if (maxAISum != -1) { //если есть потенциально выигрышные комбинации
                bestCombination = new int[game.GAME_SIZE * 2 + 2]; //заполняем массив с индексами лучших комбинаций ПК
                for (int j = 0; j < bestCombination.length; j++) {
                    bestCombination[j] = -1;
                }
                for (int i = 0; i < game.GAME_SIZE*2+2; i++) {
                    if (game.getGameState()[i][3] == maxAISum && game.getGameState()[i][2] == 0) {
                        bestCombination[k] = i;
                        k++;
                    }
                }
                randomIndex = random.nextInt(k); //если одинаково выигрышных комбинаций несколько, берем случайную
                setFirstEmptyIndex(bestCombination[randomIndex],game); //и получаем координаты случайной пустой кнопки из этой комбинации
                x = emptyIndexX;
                y = emptyIndexY;
            } else { //если потенциально выигрышных комбинаций нет, берем случайную пустую кнопку
                do {
                    x = random.nextInt(game.GAME_SIZE);
                    y = random.nextInt(game.GAME_SIZE);
                } while (!game.isTurnable(x * game.GAME_SIZE + y + 1));
            }
            game.getArea().getButtons()[x * game.GAME_SIZE + y + 1].setText(aiSymbol);
        } else {
            if (game.isTurnable(ind)) {
                game.getArea().getButtons()[ind].setText(humanSymbol);
            } else {
                result = false;
            }
        }
        return result;
    }

    /**
     * метод, который возвращает для заданной комбинации координаты случайной пустой ячейки
     * @param index
     * @param game
     */
    private void setFirstEmptyIndex(int index, TickTackGame game) {

        int[][] XY = new int[game.GAME_SIZE][2];
        int tempRow;
        int k = 0;

        for (int i = 0; i < game.GAME_SIZE; i++) {
            if (game.getGameState()[index][1] == game.TYPE_ROW) {
                if (game.getTextArea()[game.getGameState()[index][0]][i] == game.DOT_EMPTY) {
                    XY[k][0] = game.getGameState()[index][0];
                    XY[k][1] = i;
                    k++;
                }
            } else if (game.getGameState()[index][1] == game.TYPE_COLUMN) {
                if (game.getTextArea()[i][game.getGameState()[index][0]] == game.DOT_EMPTY) {
                    XY[k][0] = i;
                    XY[k][1] = game.getGameState()[index][0];
                    k++;
                }
            } else if (game.getGameState()[index][1] == game.TYPE_DIAG1) {
                if (game.getTextArea()[i][i] == game.DOT_EMPTY) {
                    XY[k][0] = i;
                    XY[k][1] = i;
                    k++;
                }
            } else if (game.getGameState()[index][1] == game.TYPE_DIAG2) {
                if (game.getTextArea()[i][game.GAME_SIZE - i - 1] == game.DOT_EMPTY) {
                    XY[k][0] = i;
                    XY[k][1] = game.GAME_SIZE - i - 1;
                    k++;
                }
            }
        }
        tempRow = random.nextInt(k);
        emptyIndexX = XY[tempRow][0];
        emptyIndexY = XY[tempRow][1];
    }

    public void setAiSymbol(String aiSymbol) {
        this.aiSymbol = aiSymbol;
    }

    public void setHumanSymbol(String humanSymbol) {
        this.humanSymbol = humanSymbol;
    }

    public String getAiSymbol() {
        return aiSymbol;
    }

    public String getHumanSymbol() {
        return humanSymbol;
    }
}