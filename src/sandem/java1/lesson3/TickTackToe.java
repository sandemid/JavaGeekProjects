import java.util.Random;
import java.util.Scanner;

/**
 * Игра крестики-нолики в процедурном стиле
 */

public class TickTackToe {

    final private static int SIZE = 3;
    final private static int TYPE_ROW = 0;
    final private static int TYPE_COLUMN = 1;
    final private static int TYPE_DIAG1 = 2;
    final private static int TYPE_DIAG2 = 3;
    private static char[][] map;
    private static int[][] mapState;
    private static int[] maxDOTCombi;
    final private static char DOT_X = 'X';
    final private static char DOT_O = 'O';
    final private static char DOT_EMPTY = '•';
    private static int USER_SYMBOL = -1;
    private static int GAME_LEVEL = -1;
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();
    private static int emptyIndexX, emptyIndexY;

    public static void main(String[] args) {
        initMap();
        printMap();
        chooseSymbol();
        chooseLevel();
        while (true) {
            humanTurn();
            if (isEndGame(returnSymbol(USER_SYMBOL))) {
                break;
            }
            computerTurn();
            if (isEndGame(returnSymbol(Math.abs(USER_SYMBOL - 1)))) {
                break;
            }
        }
    }

    /**
     * Метод проверки окончания игры - или выигрыш, или поле заполнено
     * @param userSymbol крестик или нолик
     * @return логическое
     */
    private static boolean isEndGame(char userSymbol){
        boolean result = false;
        printMap();

        if (isWinGame()) {
            System.out.println("Победил символ " + userSymbol);
            result = true;
        } else if (isMapFull()) {
            System.out.println("Ничья!");
            result = true;
        }

        return result;
    }

    /**
     * Проверка выигрша на заданный символ
     * @return логическое
     */
    private static boolean isWinGame() {
        boolean result = false;

        getMapState();

        for (int i = 0; i < SIZE * 2 + 2; i++) {
           if (mapState[i][2] == SIZE || mapState[i][3] == SIZE) {
               result = true;
               break;
           }
        }

        return result;
    }

    /**
     * Проверка заполненности поля
     * @return логическое
     */
    private static boolean isMapFull() {
        boolean result = true;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    result = false;
                    break;
                }
            }
            if (result == false) {
                break;
            }
        }
        return result;
    }

    /**
     * Ход игрока
     */
    private static void humanTurn(){
        int x,y;
        do {
            System.out.println("Введите координаты ячейки Х и Y (через пробел):");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x,y));

        map[x][y] = returnSymbol(USER_SYMBOL);
    }

    /**
     * Ход компьютера. Две стратегии
     * Глупый компьютер - клетка выбирается случайным образом
     * Умный компьютер - придумал свой алгоритм, имхо, более близкий к реальности
     * Вводим состояние игрового поля, для каждой строки / столбца / диагонали в массиве получаем параметры:
     * индекс, тип набора, число заполненных клеток человеком, ПК и пустых
     * Набор - это строка, столбец или диагональ игрового поля
     * Находим наборы, в которых для ПК не хватает 1 хода до выигрыша. Если есть, ходим в последнюю клетку набора и выигрываем
     * Иначе находим набор, в котором человеку не хватает 1 хода до выигрыша
     * Если такой есть, "портим" выигрыш игроку:)
     * Иначе ищем наборы с максимальным числом элементов ПК и без элементов игрока, выбираем из них случайный и ходим в него
     * Иначе - потенциально выигрышных наборов больше нет, ходим дальше случайным образом
     */
    private static void computerTurn(){
        int x = -1;
        int y = -1;
        int k = 0;
        boolean humanAlmostWin = false;
        int humanAlmostWinIndex = -1;
        boolean AIAlmostWin = false;
        int AIAlmostWinIndex = -1;
        int maxAISum = -1;
        int randomIndex;
        if (GAME_LEVEL == 0) {
            do {
                x = random.nextInt(SIZE);
                y = random.nextInt(SIZE);
            } while (!isCellValid(x,y));
        } else {
            getMapState();
            for (int i = 0; i < SIZE * 2 + 2; i++) {
                if (mapState[i][2] == SIZE - 1 && mapState[i][4] == 1) {
                    humanAlmostWin = true;
                    humanAlmostWinIndex = i;
                }
                if (mapState[i][3] == SIZE - 1 && mapState[i][4] == 1) {
                    AIAlmostWin = true;
                    AIAlmostWinIndex = i;
                }
                if (mapState[i][3] > maxAISum && mapState[i][2] == 0) {
                    maxAISum = mapState[i][3];
                }
            }
            if (AIAlmostWin) {
                setFirstEmptyIndex(AIAlmostWinIndex,mapState[AIAlmostWinIndex][1]);
                x = emptyIndexX;
                y = emptyIndexY;
            } else if (humanAlmostWin) {
                setFirstEmptyIndex(humanAlmostWinIndex,mapState[humanAlmostWinIndex][1]);
                x = emptyIndexX;
                y = emptyIndexY;
            } else if (maxAISum != -1) {
                for (int i = 0; i < SIZE*2+2; i++) {
                    if (mapState[i][3] == maxAISum && mapState[i][2] == 0) {
                        maxDOTCombi[k] = i;
                        k++;
                    }
                }
                randomIndex = random.nextInt(k);
                setFirstEmptyIndex(maxDOTCombi[randomIndex],mapState[maxDOTCombi[randomIndex]][1]);
                x = emptyIndexX;
                y = emptyIndexY;
            } else {
                do {
                    x = random.nextInt(SIZE);
                    y = random.nextInt(SIZE);
                } while (!isCellValid(x,y));
            }

        }

        map[x][y] = returnSymbol(Math.abs(USER_SYMBOL - 1));
        System.out.println("Компьютер выбрал ячейку с координатами: " + (x + 1) + ", " + (y + 1));
    }

    private static void setFirstEmptyIndex(int index, int type) {
        for (int i = 0; i < SIZE; i++) {
            if (mapState[index][1] == TYPE_ROW) {
                if (map[mapState[index][0]][i] == DOT_EMPTY) {
                    emptyIndexX = mapState[index][0];
                    emptyIndexY = i;
                }
            } else if (mapState[index][1] == TYPE_COLUMN) {
                if (map[i][mapState[index][0]] == DOT_EMPTY) {
                    emptyIndexX = i;
                    emptyIndexY = mapState[index][0];
                }
            } else if (mapState[index][1] == TYPE_DIAG1) {
                if (map[i][i] == DOT_EMPTY) {
                    emptyIndexX = i;
                    emptyIndexY = i;
                }
            } else if (mapState[index][1] == TYPE_DIAG2) {
                if (map[i][SIZE - i - 1] == DOT_EMPTY) {
                    emptyIndexX = i;
                    emptyIndexY = SIZE - i - 1;
                }
            }
        }
    }

    private static void getMapState(){

        int rowSumUser, rowSumAI, rowSumEmpty, colSumUser, colSumAi, colSumEmpty;
        int diagSumUser1 = 0;
        int diagSumAI1 = 0;
        int diagSumEmpty1 = 0;
        int diagSumUser2 = 0;
        int diagSumAI2 = 0;
        int diagSumEmpty2 = 0;

        for (int i = 0; i < SIZE; i++) {
            rowSumUser = 0;
            rowSumAI = 0;
            rowSumEmpty = 0;
            colSumUser = 0;
            colSumAi = 0;
            colSumEmpty = 0;
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == returnSymbol(USER_SYMBOL)) {
                    rowSumUser++;
                } else if (map[i][j] == returnSymbol(Math.abs(USER_SYMBOL - 1))) {
                    rowSumAI++;
                } else  {
                    rowSumEmpty++;
                }
                if (map[j][i] == returnSymbol(USER_SYMBOL)) {
                    colSumUser++;
                } else if (map[j][i] == returnSymbol(Math.abs(USER_SYMBOL - 1))){
                    colSumAi++;
                } else {
                    colSumEmpty++;
                }
                if (i == j && map[i][j] == returnSymbol(USER_SYMBOL)) {
                    diagSumUser1++;
                } else if (i == j && map[i][j] == returnSymbol(Math.abs(USER_SYMBOL - 1))) {
                    diagSumAI1++;
                } else if (i == j){
                    diagSumEmpty1++;
                }
                if (map[i][j] == returnSymbol(USER_SYMBOL) && (i == SIZE - j - 1)) {
                    diagSumUser2++;
                } else if (map[i][j] == returnSymbol(Math.abs(USER_SYMBOL - 1)) && (i == SIZE - j - 1)) {
                    diagSumAI2++;
                } else if (i == SIZE - j - 1){
                    diagSumEmpty2++;
                }
            }
            mapState[i][0] = i;
            mapState[i][2] = rowSumUser;
            mapState[i][3] = rowSumAI;
            mapState[i][4] = rowSumEmpty;
            mapState[i+SIZE][0] = i;
            mapState[i+SIZE][2] = colSumUser;
            mapState[i+SIZE][3] = colSumAi;
            mapState[i+SIZE][4] = colSumEmpty;
        }

        mapState[SIZE*2][0] = 1;
        mapState[SIZE*2][2] = diagSumUser1;
        mapState[SIZE*2][3] = diagSumAI1;
        mapState[SIZE*2][4] = diagSumEmpty1;
        mapState[SIZE*2 + 1][0] = 2;
        mapState[SIZE*2 + 1][2] = diagSumUser2;
        mapState[SIZE*2 + 1][3] = diagSumAI2;
        mapState[SIZE*2 + 1][4] = diagSumEmpty2;

    }

    /**
     * Возврат символа в зависимости от выбранного игроком
     * @param x - код символа
     * @return символ крестик или нолик
     */
    private static char returnSymbol(int x) {
        char symbol;
        if (x == 0) {
            symbol = DOT_O;
        } else {
            symbol = DOT_X;
        }
        return symbol;
    }

    /**
     * Проверка корректности ввода координат игроком или компьютером
     * @param x строка
     * @param y столбец
     * @return логическое
     */
    private static boolean isCellValid(int x, int y){
        boolean result = true;
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            result = false;
        } else if (map[x][y] != DOT_EMPTY) {
            result = false;
        }
        return result;
    }

    /**
     * Выбор пользователем символа, за который он будет играть
     */
    private static void chooseSymbol(){
        do {
            System.out.println("Выберите символ, за который будете играть (0 - нолик, 1 - крестик):");
            USER_SYMBOL = scanner.nextInt();
        } while (!isSymbolValid(USER_SYMBOL));
    }

    /**
     * Проверка корректности ввода кодов символа и уровня сложности
     * @param x
     * @return
     */
    private static boolean isSymbolValid(int x){
        boolean result = true;
        if (!(x == 0 || x == 1)) {
            result = false;
            System.out.println("Введен некорректный код!");
        }
        return result;
    }

    /**
     * Выбор уровная сложности компьютера
     */
    private static void chooseLevel(){
        do {
            System.out.println("Выберите уровень сложности (0 - глупый компьютер, 1 - умный компьютер):");
            GAME_LEVEL = scanner.nextInt();
        } while (!isSymbolValid(GAME_LEVEL));
    }

    /**
     * Инициализация игрового поля
     */
    private static void initMap(){
        maxDOTCombi = new int [SIZE * 2 + 2];
        for (int i = 0; i < SIZE * 2 + 2; i++) {
            maxDOTCombi [i] = -1;
        }

        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
        mapState = new int[SIZE * 2 + 2][5];

        for (int i = 0; i < SIZE; i++) {
            mapState[i][1] = TYPE_ROW;
            mapState[i + SIZE][1] = TYPE_COLUMN;
        }
        mapState[SIZE*2][1] = TYPE_DIAG1;
        mapState[SIZE*2+1][1] = TYPE_DIAG2;
    }

    /**
     * Печать игрового поля
     */
    private static void printMap(){
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < SIZE; j++) {
                if (i == 0) {
                    System.out.print(j + 1 + " ");
                } else {
                    System.out.print(map[i-1][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
