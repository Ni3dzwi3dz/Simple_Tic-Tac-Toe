package tictactoe;

import java.util.Objects;
import java.util.Scanner;

public class Game {
    int boardSize;
    int xs,os;
    String[][] board;

    public Game(int size){
        boardSize = size;
        board = new String[size][size];
        xs = 0;
        os = 0;

    }

    // Fills the board using 9-characters string from standard input
    public void fillBoard(String inputString) {
        int counter =0;

        String[] boardAsString = inputString.split("");

        for (int i = 0; i < boardSize; i++) {
            for (int j=0; j < boardSize; j++) {
                board[i][j] = boardAsString[counter];
                if (Objects.equals(boardAsString[counter], "X")) {
                    xs++;
                } else if (Objects.equals(boardAsString[counter], "O")) {
                    os++;
                }
                counter++;
            }
        }
    }

    public void printBoard() {

        System.out.println("---------");

        for (int i = 0; i < boardSize; i++) {
            System.out.print("| ");
            for (int j = 0; j < boardSize; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.print("|\n");
        }

        System.out.println("---------");
    }

    public String checkBoard(short counter) {

        // String result = "Game not finished";
        short changesCounter = 0;

        for (int i = 0; i < boardSize; i++) {
            // check for horizontal line
            if (board[i][0].equals(board[i][1]) &&
                    board[i][0].equals(board[i][2]) && "XO".contains(board[i][0])) {
                System.out.println("Found horizontal");
                return board[i][0] + " wins";


                // check for vertical lines
            } else if (board[0][i].equals(board[1][i]) &&
                    board[0][i].equals(board[2][i]) && "XO".contains(board[0][i])) {
                System.out.println("Found vertical");
                return board[0][i] + " wins";


                // check the diagonal
            }
        }
            if (board[0][0].equals(board[1][1]) &&
                    board[0][0].equals(board[2][2]) && "XO".contains(board[0][0])) {
                return board[0][0] + " wins";

                //System.out.println("Found diagonal");

                // check the secondary diagonal
            } else if (board[0][2].equals(board[1][1]) &&
                    board[0][2].equals(board[2][0]) && "XO".contains(board[0][2])){
                return board[0][2] + " wins";

                // System.out.println("Found 2nd diagonal");

            }


        if ( counter >= 9 ) {
            return "Draw";

        } else if (java.lang.Math.abs(xs - os ) > 1 ) {
            return  "Impossible";

        }
        System.out.println(changesCounter);
        return "Game not finished";
            }

    public boolean makeMove(String moveInput, String symbol){
        short x;
        short y;
        String[] moveCoords = moveInput.split(" ");

        try {
            x = Short.parseShort(moveCoords[0]);
            y = Short.parseShort(moveCoords[1]);
        }
        catch (Exception e) {
            System.out.println("Give me straight numbers");
            return false;
        }

        if ((x>3) || (x<1) || (y>3) || (y<1)) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        } else if ("XO".contains(board[x-1][y-1])){
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }

        board[x-1][y-1] = symbol;

        return true;
    }

    public void playGame(){

        String temporaryResult;
        String symbol = "X";
        short counter=1;
        Scanner scn = new Scanner(System.in);

        fillBoard("         ");
        printBoard();

        // main move loop
        do {

            do {
                System.out.println("Enter coordinates:");
            } while (!(makeMove(scn.nextLine(), symbol)));

            printBoard();
            temporaryResult = checkBoard(counter);
            counter++;

            //switch symbol
            if (symbol.equals("X")) { symbol = "O";}
            else {symbol = "X";}
            System.out.println(temporaryResult);
        } while ((temporaryResult.contains("finished")));


    }
}
