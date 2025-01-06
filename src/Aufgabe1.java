/*
    Aufgabe 1) Zweidimensionale Arrays und Rekursion - Game "TicTacToe"
*/

import codedraw.*;

public class Aufgabe1 {
    public static void main(String[] args) {

        int size = 600;
        CodeDraw myDrawObj = new CodeDraw(size, size);
        myDrawObj.setTitle("Tic Tac Toe");
        EventScanner myEventSC = myDrawObj.getEventScanner();

        char[][] gameBoard = new char[][]{
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };

        boolean twoPlayer = false; //true ... human vs. human, false ... human vs. computer
        boolean player = true; //(1Player) human = true, computer = false, (2Player) human1 = true, human2 = false
        int maxDepth = 4;
        boolean gameRunning = true;

        drawGameBoard(myDrawObj, gameBoard);

        while (!myDrawObj.isClosed() && gameRunning) {
            if (myEventSC.hasMouseClickEvent()) {
                // TODO: Implementieren Sie hier Ihre Lösung für den if-Zweig
            } else if (!twoPlayer && !player) {
                // TODO: Implementieren Sie hier Ihre Lösung für den else-if-Zweig
            } else {
                myEventSC.nextEvent();
            }
        }
    }

    private static int[] minimax(char[][] gameBoard, boolean player, int depth) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        return null; //Zeile kann geändert oder entfernt werden.
    }

    private static boolean checkIfFull(char[][] gameBoard) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        return false; //Zeile kann geändert oder entfernt werden.
    }

    private static boolean checkIfWinner(char[][] gameBoard, boolean player) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        return false; //Zeile kann geändert oder entfernt werden.
    }

    private static void drawGameBoard(CodeDraw myDrawObj, char[][] gameBoard) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
    }

}
