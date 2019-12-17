package tictactoe;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int dimension = 3;
    static String[][] field = new String[dimension][dimension];
    static String whoMove = "X";

    public static void main(String[] args) {
        // write your code here

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = " "; //Character.toString(cells.charAt(i*dimension+j));
            }
        }
        printField();
        do {

            nextMove(whoMove);
            if (whoMove.equals("X")) {
                whoMove = "O";
            } else {
                whoMove = "X";
            }
            printField();
        }
        while (!analyseField());
    }

    static void nextMove(String symbol) {
        do {
            System.out.print("Enter the coordinates: ");
            String line = scanner.nextLine();
            String[] nums = line.split(" ");

            if (nums.length != 2) {
                System.out.println("You should enter two numbers!");
            } else if (!isNumeric(nums[0]) || !isNumeric(nums[1])) {
                System.out.println("You should enter numbers!");
            } else if (Integer.parseInt(nums[0]) < 1 || Integer.parseInt(nums[0]) > 3 ||
                    Integer.parseInt(nums[1]) < 1 || Integer.parseInt(nums[1]) > 3 ) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else if (!field[3-Integer.parseInt(nums[1])][Integer.parseInt(nums[0])-1].equals(" ")) {

                System.out.println("This cell is occupied! Choose another one!");
            } else {
                field[3-Integer.parseInt(nums[1])][Integer.parseInt(nums[0])-1]= symbol;
                break;
            }
        } while (true);

    }

    public static boolean isNumeric(String str) {
        if (str == null)
            return false;
        try {
            /* int i = */ Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    static void printField() {
        System.out.println("---------");
        for (int i = 0; i < field.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(field[i][j]);
                System.out.print(" ");
            }
            System.out.println("| ");
        }
        System.out.println("---------");
    }

    static boolean analyseField() {
        int countX = countItems("X");
        int countO = countItems("O");
        int count_ = countItems(" ");

        boolean isWinX = checkWin("X");
        boolean isWinO = checkWin("O");
        boolean isGameEnd = false;

        if (Math.abs(countX-countO) >= 2 || (isWinX && isWinO)) {
            System.out.println("Impossible");
            isGameEnd = true;
        } else if (!isWinX && !isWinO && count_ > 0) {
            // System.out.println("Game not finished");

        } else if (!isWinX && !isWinO && count_ == 0) {
            System.out.println("Draw");
            isGameEnd = true;
        } else if (isWinX) {
            System.out.println("X wins");
            isGameEnd = true;
        } else if (isWinO) {
            System.out.println("O wins");
            isGameEnd = true;
        }
        return isGameEnd;
    }
    static boolean checkWin(String item) {
        if (
                (field[0][0].equals(item) && field[0][1].equals(item) && field[0][2].equals(item)) ||
                (field[1][0].equals(item) && field[1][1].equals(item) && field[1][2].equals(item)) ||
                (field[2][0].equals(item) && field[2][1].equals(item) && field[2][2].equals(item)) ||

                (field[0][0].equals(item) && field[1][0].equals(item) && field[2][0].equals(item)) ||
                (field[0][1].equals(item) && field[1][1].equals(item) && field[2][1].equals(item)) ||
                (field[0][2].equals(item) && field[1][2].equals(item) && field[2][2].equals(item)) ||

                (field[0][0].equals(item) && field[1][1].equals(item) && field[2][2].equals(item)) ||
                (field[0][2].equals(item) && field[1][1].equals(item) && field[2][0].equals(item))
        ) {
            return true;
        } else {return false;}
    }

    static int countItems (String item) {
        int count = 0;
        for (String[] i : field) {
            for (String j : i) {
                if (j.equals(item)) {
                    count++;
                }
            }
        }
        return count;
    }
}

