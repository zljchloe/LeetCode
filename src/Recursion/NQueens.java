package Recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyujiazhang on 10/1/16.
 * https://leetcode.com/problems/n-queens/
 *
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * Each solution contains a distinct board configuration of the n-queens' placement,
 * where 'Q' and '.' both indicate a queen and an empty space respectively.
 *
 * 1. Backtrack recursion solution with runtime O(n!).
 * 2. For each recursion, check if current col can be placed Queen on current row, remove current col after each layer's recursion.
 * 3. isValid method is done by checking the same col and its diagonals.
 */
public class NQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        helper(res, cur, n);
        return res;
    }

    private void helper(List<List<String>> res, List<Integer> cur, int n) {
        // Base case
        if (cur.size() >= n) {
            addSolution(res, cur, n);
            return;
        }
        // Recursive call
        for (int i = 0; i < n; i++) {
            // Recurse n times to find a solution
            if (isValid(cur, i)) {
                cur.add(i);
                helper(res, cur, n);
                cur.remove(cur.size() - 1);
            }
        }
    }

    private boolean isValid(List<Integer> cur, int col) {
        for (int row = 0; row < cur.size(); row++) {
            // Check if current col is added on the same col or its diagonals
            if (cur.get(row) == col || Math.abs(cur.get(row) - col) == cur.size() - row) {
                return false;
            }
        }
        return true;
    }

    private void addSolution(List<List<String>> res, List<Integer> cur, int n) {
        // Build one possible solution list of Strings
        List<String> curStr = new ArrayList<>();
        for (int row = 0; row < cur.size(); row++) {
            StringBuilder sb = new StringBuilder();
            int col = cur.get(row);
            for (int i = 0; i < n; i++) {
                if (i == col) sb.append("Q");
                else sb.append(".");
            }
            curStr.add(sb.toString());
        }
        res.add(curStr);
    }

    public static void main(String[] args) {
        NQueens nq = new NQueens();
        System.out.println(nq.solveNQueens(4));
        /*
        [[.Q.., ...Q, Q..., ..Q.],
         [..Q., Q..., ...Q, .Q..]]
         */
    }
}
