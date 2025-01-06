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

        int[] retArray = new int[3]; // [row, col, score]

        if (player) {
            retArray[2] = Integer.MIN_VALUE;
        } else {
            retArray[2] = Integer.MAX_VALUE;
        }

        for (int row = 0; row < gameBoard.length; row++) {
            for (int col = 0; col < gameBoard[row].length; col++) {
                if (gameBoard[row][col] == ' ') { // Empty field
                    gameBoard[row][col] = player ? 'X' : 'O';

                    if (checkIfWinner(gameBoard, true)) {
                        retArray[0] = row;
                        retArray[1] = col;
                        retArray[2] = -1;
                    } else if (checkIfWinner(gameBoard, false)) {
                        retArray[0] = row;
                        retArray[1] = col;
                        retArray[2] = 1;
                    } else if (checkIfFull(gameBoard) || depth - 1 == 0) {
                        retArray[0] = row;
                        retArray[1] = col;
                        retArray[2] = 0;
                    } else {
                        int[] tempArray = minimax(gameBoard, !player, depth - 1);

                        if (player && tempArray[2] > retArray[2]) {
                            retArray[0] = row;
                            retArray[1] = col;
                            retArray[2] = tempArray[2];
                        } else if (!player && tempArray[2] < retArray[2]) {
                            retArray[0] = row;
                            retArray[1] = col;
                            retArray[2] = tempArray[2];
                        }
                    }

                    gameBoard[row][col] = ' '; // Undo move
                }
            }
        }

        return retArray;
    }

    private static boolean checkIfFull(char[][] gameBoard) {
        for (char[] array : gameBoard) {
            for (char element : array) {
                if (element == ' ') {
                    return true;
                }
            }
        }
        return false; //Zeile kann geändert oder entfernt werden.
    }

    private static boolean checkIfWinner(char[][] gameBoard, boolean player) {
        char winningSymbol = player ? 'X' : 'O';
        //row Check
        for (char[] array : gameBoard) {
            if (array[0] == winningSymbol && array[1] == winningSymbol && array[2] == winningSymbol) {
                return true;
            }
        }
        //col Check
        for (int col = 0; col < gameBoard[0].length; col++) {
            if (gameBoard[0][col] == winningSymbol && gameBoard[1][col] == winningSymbol && gameBoard[2][col] == winningSymbol) {
                return true;
            }
        }
        //dia Check
        return (gameBoard[0][0] == winningSymbol && gameBoard[1][1] == winningSymbol && gameBoard[2][2] == winningSymbol) ||
                (gameBoard[0][2] == winningSymbol && gameBoard[1][1] == winningSymbol && gameBoard[2][0] == winningSymbol);//Zeile kann geändert oder entfernt werden.
    }

    private static void drawGameBoard(CodeDraw myDrawObj, char[][] gameBoard) {
        //draw Gameboard-Lines
        double thirdX = myDrawObj.getWidth() / 3.0;
        double thirdY = myDrawObj.getHeight() / 3.0;
        myDrawObj.drawLine(thirdX, 0, thirdX, myDrawObj.getHeight());
        myDrawObj.drawLine(thirdX * 2, 0, thirdX * 2, myDrawObj.getHeight());

        myDrawObj.drawLine(0, thirdY, myDrawObj.getWidth(), thirdY);
        myDrawObj.drawLine(0, thirdY * 2, myDrawObj.getWidth(), thirdY * 2);
        //draw Symbols
        double posX = 0;
        double posY = 0;
        for (char[] array : gameBoard) {
            for (char element : array) {
                if (element == 'X') {
                    myDrawObj.drawLine(posX, posY, posX + thirdX, posY + thirdY);
                    myDrawObj.drawLine(posX, posY + thirdY, posX + thirdX, posY);
                } else if (element == 'O') {
                    myDrawObj.drawCircle(posX + thirdX / 2.0, posY + thirdY / 2.0, thirdX / 2.0);
                }
                posX += thirdX;
            }
            posY += thirdY;
            posX = 0;
        }
        myDrawObj.show();
    }

}
