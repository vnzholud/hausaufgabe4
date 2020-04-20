package hause;

import java.util.Random;
import java.util.Scanner;

public class OXGame {

    static final int SIZE = 3;
//    static final int DOTS_TO_WIN = 3;

    static final char DOT_X = 'X';
    static final char DOT_O = 'O';
    static final char DOT_EMPTY = '.';

    static char[][] map;

    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        initMap();
        printMap();

        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("Ты Супер победитель!");
                break;
            }

            if (isFull()) {
                System.out.println("Ничья...");
                break;
            }

            aiTurn();
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("ИИ нынче очень развито, компьютер победил!");
                break;
            }
            if (isFull()) {
                System.out.println("Ничья...");
                break;
            }
        }
    }

    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        System.out.print("  ");
        for (int i = 1; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.printf("%s ", map[i][j]);
            }
            System.out.println();
        }
    }

    public static void humanTurn() {
        int x;
        int y;

        do {
            System.out.println("Введите координаты X и Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(y, x));
        map[y][x] = DOT_X;

    }

    public static boolean isCellValid(int y, int x) {
        if (x < 0 || y < 0 || x >= SIZE || y >= SIZE) {
            return false;
        }
        return map[y][x] == DOT_EMPTY;
    }

    public static void aiTurn() {
        int x;
        int y;

        do {
            y = random.nextInt(SIZE);
            x = random.nextInt(SIZE);
        } while (!isCellValid(y, x));
        map[y][x] = DOT_O;

    }

    public static boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkWin(char c) {
        //Строки
        int a = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {

                if (map[i][j] == c) {
                    a++;
                }
                if (a == SIZE) {
                    return true;
                }

            }
            a = 0;
        }
// Столбцы
        int b = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {

                if (map[j][i] == c) {
                    b++;
                }
                if (b == SIZE) {
                    return true;
                }

            }
            b = 0;
        }
        // 1-ая диагональ
        int d = 0;
        for (int i = 0; i < SIZE ; i++) {
            if (map[i][i] == c ) {
                d++;
            }
            if(d == SIZE){
                return true;
            }
        }
        // 2-ая диагональ
        int e = 0;
        for (int i = 0; i < SIZE ; i++) {
            for (int j = SIZE - 1; j >= 0 ; j--) {
                if (map[i][j] == c) {
                    e++;
                }
                if(e == SIZE){
                    return true;
                }
            }
        }


        return false;

    }


}

