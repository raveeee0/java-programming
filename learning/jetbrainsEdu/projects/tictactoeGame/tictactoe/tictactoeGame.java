package tictactoe;

import java.util.Scanner;
import java.util.Arrays;


public class tictactoeGame {
    final static Scanner scanner = new Scanner(System.in);
    final static int fieldSize = 3;
    char[][] field = new char[fieldSize][fieldSize];
    String fieldState;

    public static void main(String[] args) {
        new tictactoeGame();
        scanner.close();
    }


    public tictactoeGame() {
        for (int i = 0; i < fieldSize; i++) {
            Arrays.fill(field[i], '_');
        }

        printField();
        updateFieldState();
        simulateGame();
    }

    private void simulateGame() {
        char shift = 'X';
        while (this.fieldState.equals("Game not finished")) {
            move(shift);
            if (shift == 'X') {
                shift = 'O';
            } else {
                shift = 'X';
            }
        }

        System.out.println("\n" + this.fieldState);
    }


    private boolean isMoveValid(int xPos, int yPos) {
        boolean isInRange = xPos > 0 && xPos <= fieldSize && yPos > 0 && yPos <= fieldSize;
        if (!isInRange) {
            System.out.println("Coordinates should be from 1 to 3!");
            return isInRange;
        }

        boolean isOccupied = field[xPos - 1][yPos - 1] != '_';
        if (isOccupied) {
            System.out.println("This cell is occupied! Choose another one!");
            return !isOccupied;
        }

        return true;
    }


    private void move(char shift) {
        if (this.fieldState.equals("Impossible")) {
            System.out.println("Configuration is impossible.\nAborting...");
            System.exit(1);
        }


        String xPosString;
        String yPosString;


        int xPos = 0;
        int yPos = 0;

        boolean occupied;

        do {
            System.out.print(shift + " > ");
            xPosString = scanner.next();
            yPosString = scanner.next();

            if (xPosString.matches("\\d+") && yPosString.matches("\\d+")) {
                xPos = Integer.parseInt(xPosString);
                yPos = Integer.parseInt(yPosString);
            } else {
                System.out.println("You should enter numbers!");
                continue;
            }

        }  while (!isMoveValid(xPos, yPos));

        this.field[xPos - 1][yPos - 1] = shift;

        updateFieldState();
        printField();
    }



    private void updateFieldState() {
        boolean xWin = false;
        boolean oWin = false;

        int numberOfO = 0;
        int numberOfX = 0;

        boolean gameFinished = true;


        for (int i = 0; i < fieldSize; i++) {
            boolean isOWinnerCol = true;
            boolean isXWinnerCol = true;
            boolean isOWinnerRow = true;
            boolean isXWinnerRow = true;
            boolean isXWinnerDiag = true;
            boolean isOWinnerDiag = true;
            boolean isXWinnerDiag2 = true;
            boolean isOWinnerDiag2 = true;

            for (int j = 0; j < fieldSize; j++) {
                if (field[i][j] != 'X') {
                    isXWinnerRow = false;
                }
                if (field[i][j] != 'O') {
                    isOWinnerRow = false;
                }


                if (field[j][i] != 'X') {
                    isXWinnerCol = false;
                }
                if (field[j][i] != 'O') {
                    isOWinnerCol = false;
                }


                if (field[j][j] != 'O') {
                    isOWinnerDiag = false;
                }
                if (field[j][fieldSize - 1 - j] != 'O') {
                    isOWinnerDiag2 = false;
                }


                if (field[j][j] != 'X') {
                    isXWinnerDiag = false;
                }
                if (field[j][fieldSize - 1 - j] != 'X') {
                    isXWinnerDiag2 = false;
                }

                if (field[i][j] == 'X') {
                    numberOfX++;
                } else if (field[i][j] == 'O') {
                    numberOfO++;
                } else {
                    gameFinished = false;
                }

            }

            if (isOWinnerCol || isOWinnerRow || isOWinnerDiag || isOWinnerDiag2) {
                oWin = true;
            }

            if (isXWinnerCol || isXWinnerRow || isXWinnerDiag || isXWinnerDiag2) {
                xWin = true;
            }
        }


        if ((oWin && xWin) || numberOfO > numberOfX + 1 || numberOfO < numberOfX - 1) {
            this.fieldState = "Impossible";
        } else if (oWin) {
            this.fieldState = "O wins!";
        } else if (xWin) {
            this.fieldState = "X wins!";
        } else if (gameFinished){
            this.fieldState = "Draw";
        } else {
            this.fieldState = "Game not finished";
        }

    }



    private void printField() {
        System.out.println("---------");
        for (int i = 0; i < fieldSize; i++) {
            System.out.print("| ");
            for (int j = 0; j < fieldSize; j++) {
                System.out.print(String.format("%c ", field[i][j]));
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

}
