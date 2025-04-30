package csc250.brincefield.jacob;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int n = 17; //  input
        List<char[][]> solutions = NQueens.solveNQueens(n);

        // Print all solutions
        for (char[][] solution : solutions) {
            for (char[] row : solution) {
                System.out.println(new String(row));
            }
            System.out.println();
        }

        System.out.println("Total solutions: " + solutions.size());
    }


}