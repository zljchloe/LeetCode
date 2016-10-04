package DP;

/**
 * Created by lyujiazhang on 10/3/16.
 * https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
 *
 * Given an integer matrix, find the length of the longest increasing path.
 * From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
 * Example 1:
 * nums = [
 * [9,9,4],
 * [6,6,8],
 * [2,1,1]
 * ]
 * Return 4
 * The longest increasing path is [1, 2, 6, 9].
 *
 * 1. DFS & Memoization -> DP
 * 2. Calculate lIP for all starting positions in the matrix and take the max.
 * 3. Maintain a mem matrix to store calculated results.
 * 4. Return 0 when it goes out of matrix's bound or goes to a bigger number.
 * 5. Go to 4 directions to get the max LIS for one position and store the result to mem matrix.
 */
public class LongestIncreasingPathInAMatrix {
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int max = 0;
        // Memoization matrix storing calculated results to avoid re-calculating
        int[][] mem = new int[matrix.length][matrix[0].length];
        // Start from any position in the matrix and take the max
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                max = Math.max(max, helper(matrix, i, j, Integer.MIN_VALUE, mem));
            }
        }
        return max;
    }

    private int helper(int[][] matrix, int x, int y, int min, int[][] mem) {
        // Base case
        if (x < 0 || y < 0 || x >= matrix.length || y >= matrix[0].length) {
            return 0;
        }
        if (matrix[x][y] <= min) {
            return 0;
        }
        // If longest increasing path starting from matrix[x][y] is already calculated and is in mem[x][y]
        if (mem[x][y] != 0) {
            return mem[x][y];
        }
        // Recursive call
        int left = helper(matrix, x, y - 1, matrix[x][y], mem);
        int right = helper(matrix, x, y + 1, matrix[x][y], mem);
        int up = helper(matrix, x - 1, y, matrix[x][y], mem);
        int down = helper(matrix, x + 1, y, matrix[x][y], mem);
        mem[x][y] = Math.max(Math.max(left, right), Math.max(up, down)) + 1;
        return mem[x][y];
    }

    public static void main(String[] args) {
        LongestIncreasingPathInAMatrix lip = new LongestIncreasingPathInAMatrix();
        System.out.println(lip.longestIncreasingPath(new int[][]{{9,9,4}, {6,6,8}, {2,1,1}}));
    }
}
