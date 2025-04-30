package csc250.brincefield.jacob;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int n = 16;
        List<char[][]> solutions = solveNQueens(n);

        // Print all solutions
        for (char[][] solution : solutions) {
            for (char[] row : solution) {
                System.out.println(new String(row));
            }
            System.out.println();
        }
        System.out.println("n = " + n);
        System.out.println("Total solutions: " + solutions.size());
    }

    /*
    Pseudocode
    nQueens - Backtracking algorithm

    Find all possible solutions when given n
    n will determine the board size: (n*n)

    INPUT: integer = n
    OUTPUT: the amount of queens that should fit on the board that = n


        board is the current state of the N×N board (e.g., 2D array or list of strings).
            char[][]
        row is the current row we’re trying to place a queen in.
            int
        solutions is a list to store all valid board configurations.
            List<char[][]> //dont use a hashset as the psuedo code should prevent duplicate solutions


    function solveNQueens(board, row, solutions):
        if row == N: //we have reached beyond the last row and can now go backwards
            //add deep copy of board to solutions
            solutions.add(board)
            return;

        for col from 0 to N - 1: //Loop through all columns in the current row.
            if isValidPlacement(board, row, col):  //No other queen is in the same column and No queen is on the same diagonal
                placeQueen(board, row, col)
                solveNQueens(board, row + 1, solutions)  // Recurse to next row
                removeQueen(board, row, col) // Backtrack

    function isValidPlacement(board, row, col):
        // Check vertical column
        // you must check each row at the same index vertically going down
        for r from 0 to row - 1:
            if board[r][col] == 'Q':
                return false

        // Check top-left diagonal
        // you must check the same row going down, and the column going down each loop
        // start from the top left
        r = row - 1; //row identifier
        c = col - 1; //column identifier
        while r >= 0 and c >= 0: //loop through until row and column = 0
            if board[r][c] == 'Q':
                return false
            //iterate each row and column down 1
            r -= 1
            c -= 1

        // Check top-right diagonal
        // you must check the same row going down, but the column going up each loop
        // start from the top right
        r = row - 1 // row identifier
        c = col + 1 // column identifier
        while r >= 0 and c < N: //loop through until row = 0 but column = the n input
            if board[r][c] == 'Q':
                return false
            //iterate each row down 1 but each column up 1
            r -= 1
            c += 1
        return true //if no queen has been found, return true;

    function placeQueen(board, row, col): //change the index to a queen
        board[row][col] = 'Q'

    function removeQueen(board, row, col): //change the index to an empty grid.
        board[row][col] = '.'
    */

    public static List<char[][]> solveNQueens(int n) {
        List<char[][]> solutions = new ArrayList<>();
        char[][] board = new char[n][n];

        // Initialize the board with empty cells
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }

        solveNQueensHelper(board, 0, solutions);
        return solutions;
    }

    private static void solveNQueensHelper(char[][] board, int row, List<char[][]> solutions) {
        int n = board.length;

        // Base case: If all rows are processed, add the solution
        if (row == n) {
            solutions.add(deepCopyBoard(board));
            return;
        }

        // Try placing a queen in each column of the current row
        for (int col = 0; col < n; col++) {
            if (isValidPlacement(board, row, col)) {
                placeQueen(board, row, col);
                solveNQueensHelper(board, row + 1, solutions); // Recurse to the next row
                removeQueen(board, row, col); // Backtrack
            }
        }
    }

    private static boolean isValidPlacement(char[][] board, int row, int col) {
        int n = board.length;

        // Check vertical column
        for (int r = 0; r < row; r++) {
            if (board[r][col] == 'Q') {
                return false;
            }
        }

        // Check top-left diagonal
        for (int r = row - 1, c = col - 1; r >= 0 && c >= 0; r--, c--) {
            if (board[r][c] == 'Q') {
                return false;
            }
        }

        // Check top-right diagonal
        for (int r = row - 1, c = col + 1; r >= 0 && c < n; r--, c++) {
            if (board[r][c] == 'Q') {
                return false;
            }
        }

        return true;
    }

    private static void placeQueen(char[][] board, int row, int col) {
        board[row][col] = 'Q';
    }

    private static void removeQueen(char[][] board, int row, int col) {
        board[row][col] = '.';
    }

    private static char[][] deepCopyBoard(char[][] board) {
        int n = board.length;
        char[][] copy = new char[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(board[i], 0, copy[i], 0, n);
        }
        return copy;
    }
}