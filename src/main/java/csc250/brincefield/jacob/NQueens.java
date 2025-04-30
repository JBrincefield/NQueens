package csc250.brincefield.jacob;

import java.util.ArrayList;
import java.util.List;

public class NQueens {

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
        List<char[][]> solutions = new ArrayList<>(); // List to store all solutions
        char[][] board = new char[n][n]; // 2D array to represent the chessboard

        // Initialize the board with empty cells
        for (int i = 0; i < n; i++) { // O(n)
            for (int j = 0; j < n; j++) { // O(n)
                board[i][j] = '.';
            }
        }

        solve(board, 0, solutions, n); // Start solving from the first row
        return solutions; // Return all found solutions
    }

    private static void solve(char[][] board, int row, List<char[][]> solutions, int n) {
        if (row == n) { // All queens are placed successfully
            // Add a deep copy of the board to solutions
            char[][] solution = new char[n][n]; // O(n)
            for (int i = 0; i < n; i++) { // O(n)
                solution[i] = board[i].clone(); // O(n) - cloning each row
            }
            solutions.add(solution); // O(1) - adding to the list
            return; // Found a valid solution
        }

        for (int col = 0; col < n; col++) { // O(n) - iterate through columns
            if (isValidPlacement(board, row, col, n)) { // O(n) - check if the placement is valid
                placeQueen(board, row, col); // Place the queen
                solve(board, row + 1, solutions, n); // Recurse to the next row
                removeQueen(board, row, col); // Backtrack
            }
        }
    }

    private static boolean isValidPlacement(char[][] board, int row, int col, int n) {
        // Check vertical column
        for (int r = 0; r < row; r++) { // O(n)
            if (board[r][col] == 'Q') { // Check if there's a queen in the same column
                return false;
            }
        }

        // Check top-left diagonal
        int r = row - 1, c = col - 1; // row - 1, col - 1
        while (r >= 0 && c >= 0) { // O(n)
            if (board[r][c] == 'Q') { // Check if there's a queen in the left diagonal
                return false; // Invalid placement
            }
            r--; // Move up
            c--; // Move left
        }

        // Check top-right diagonal
        r = row - 1; // row - 1 - move up
        c = col + 1; // col + 1 - move right
        while (r >= 0 && c < n) { // O(n) - check within bounds
            if (board[r][c] == 'Q') { // Check if there's a queen in the right diagonal
                return false;
            }
            r--; // Move up
            c++; // Move right
        }

        return true; // Valid placement
    }

    private static void placeQueen(char[][] board, int row, int col) {
        board[row][col] = 'Q'; // Place the queen on the board
    }

    private static void removeQueen(char[][] board, int row, int col) {
        board[row][col] = '.'; // Remove the queen from the board
    }
}