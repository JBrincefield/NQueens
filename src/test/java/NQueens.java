import java.util.List;

import org.junit.Test;

import static csc250.brincefield.jacob.NQueens.solveNQueens;
public class NQueens {
    @Test
    public void testSolveNQueens5() {
        int n = 5;
        List<char[][]> solutions = solveNQueens(n);
        assert solutions.size() == 10;
    }
    @Test
    public void testSolveNQueens6() {
        int n = 6;
        List<char[][]> solutions = solveNQueens(n);
        assert solutions.size() == 4;
    }
    @Test
    public void testSolveNQueens9() {
        int n = 9;
        List<char[][]> solutions = solveNQueens(n);
        assert solutions.size() == 352;
    }
}   
