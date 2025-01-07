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
        int maxDepth = 3;
        boolean gameRunning = true;

        drawGameBoard(myDrawObj, gameBoard);

        while (!myDrawObj.isClosed() && gameRunning) {
            if (myEventSC.hasMouseClickEvent()) {
                MouseClickEvent currentClick = myEventSC.nextMouseClickEvent();
                int mouseX = currentClick.getX();
                int mouseY = currentClick.getY();
                int third = size / 3;
                int row = Integer.MIN_VALUE;
                int col = Integer.MIN_VALUE;
                char symbol = 'X';
                if (twoPlayer && !player){
                    symbol = 'O';
                }
                if (mouseX > 0 && mouseX < third && mouseY > 0 && mouseY < third) {
                    System.out.println("row: 1, col: 1");
                    row = 0;
                    col = 0;
                } else if (mouseX > third && mouseX < third * 2 && mouseY > 0 && mouseY < third){
                    System.out.println("row: 1, col: 2");
                    row = 0;
                    col = 1;
                } else if (mouseX > third * 2 && mouseX < size && mouseY > 0 && mouseY < third){
                    System.out.println("row: 1, col: 3");
                    row = 0;
                    col = 2;
                } else if (mouseX > 0 && mouseX < third && mouseY > third && mouseY < third * 2) {
                    System.out.println("row: 2, col: 1");
                    row = 1;
                    col = 0;
                } else if (mouseX > third && mouseX < third * 2 && mouseY > third && mouseY < third * 2){
                    System.out.println("row: 2, col: 2");
                    row = 1;
                    col = 1;
                } else if (mouseX > third * 2 && mouseX < size && mouseY > third && mouseY < third * 2){
                    System.out.println("row: 2, col: 3");
                    row = 1;
                    col = 2;
                } else if (mouseX > 0 && mouseX < third && mouseY > third * 2 && mouseY < size) {
                    System.out.println("row: 3, col: 1");
                    row = 2;
                    col = 0;
                } else if (mouseX > third && mouseX < third * 2 && mouseY > third * 2 && mouseY < size){
                    System.out.println("row: 3, col: 2");
                    row = 2;
                    col = 1;
                } else if (mouseX > third * 2 && mouseX < size && mouseY > third * 2 && mouseY < size){
                    System.out.println("row: 3, col: 3");
                    row = 2;
                    col = 2;
                }
                if (row != Integer.MIN_VALUE && col != Integer.MIN_VALUE && gameBoard[row][col] == ' ') {
                    gameBoard[row][col] = symbol;
                    if (checkIfWinner(gameBoard, player)){
                        gameRunning = false;
                        if (player){
                            System.out.println("Player 1 wins!");
                        } else {
                            System.out.println("Player 2 wins!");
                        }
                    };
                    player = !player;
                } else{
                    System.out.println("Sie müssen in ein leeres Feld klicken!");
                }

            } else if (!twoPlayer && !player) {
                int[] resultCom = minimax(gameBoard, player, maxDepth);
                int row = resultCom[0];
                int col = resultCom[1];
                if (gameBoard[row][col] == ' ') {
                    gameBoard[row][col] = 'O';
                    if (checkIfWinner(gameBoard, player)) {
                        gameRunning = false;
                        System.out.println("Computer Wins");
                    }
                    player = !player;
                }
            } else {
                myEventSC.nextEvent();
            }

            if (checkIfFull(gameBoard) && gameRunning) { //Check draw
                gameRunning = false;
                System.out.println("Unentschieden");
            }
            drawGameBoard(myDrawObj, gameBoard);
        }
        myDrawObj.close();
    }

    public static int[] minimax(char[][] gameBoard, boolean player, int depth) {
        int[] retArray = new int[3];

        if (player) {
            retArray[2] = Integer.MAX_VALUE;
        } else {
            retArray[2] = Integer.MIN_VALUE;
        }

        for (int row = 0; row < gameBoard.length; row++) {
            for (int col = 0; col < gameBoard[row].length; col++) {

                if (gameBoard[row][col] == ' ') {
                    if (player) {
                        gameBoard[row][col] = 'X';
                    } else {
                        gameBoard[row][col] = 'O';
                    }

                    if (checkIfWinner(gameBoard, true)) {
                        retArray[0] = row;
                        retArray[1] = col;
                        retArray[2] = -1;
                    } else if (checkIfWinner(gameBoard, false)) {
                        retArray[0] = row;
                        retArray[1] = col;
                        retArray[2] = 1;
                    }
                    else if (checkIfFull(gameBoard) || depth - 1 == 0) {
                        retArray[0] = row;
                        retArray[1] = col;
                        retArray[2] = 0;
                    }
                    else {
                        int[] tempArray = minimax(gameBoard, !player, depth - 1);

                        if (player) {
                            if (tempArray[2] < retArray[2]) {
                                retArray[0] = row;
                                retArray[1] = col;
                                retArray[2] = tempArray[2];
                            }
                        } else {
                            if (tempArray[2] > retArray[2]) {
                                retArray[0] = row;
                                retArray[1] = col;
                                retArray[2] = tempArray[2];
                            }
                        }
                    }

                    gameBoard[row][col] = ' ';
                }
            }
        }

        return retArray;
    }

    private static boolean checkIfFull(char[][] gameBoard) {
        for (char[] array : gameBoard) {
            for (char element : array) {
                if (element == ' ') {
                    return false;
                }
            }
        }
        return true; //Zeile kann geändert oder entfernt werden.
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
