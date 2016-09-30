package DP;

/**
 * Created by lyujiazhang on 9/30/16.
 * https://leetcode.com/problems/unique-paths/
 *
 * A robot is located at the top-left corner of a m x n grid.
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * How many possible unique paths are there?
 *
 * 1. M[i][j] represents the number of paths possible from original point to current position [i][j].
 * 2. Base case: M[0][0] = 1, M[0][i] = 1 (i = 1...n - 1), M[i][0] = 1 (i = 1...m - 1).
 * 3. Induction rule: M[i][j[ = M[i - 1][j] + M[i][j - 1].
 */
public class UniquePaths {
    public int uniquePaths(int m, int n) {
        int[][] matrix = new int[m][n];
        matrix[0][0] = 1;
        for (int i = 1; i < m; i++) {
            matrix[i][0] = 1;
        }
        for (int i = 1; i < n; i++) {
            matrix[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                matrix[i][j] = matrix[i - 1][j] + matrix[i][j - 1];
            }
        }
        return matrix[m - 1][n - 1];
    }

    /**
     * Followup uniquePathsII
     * https://leetcode.com/problems/unique-paths-ii/
     * Set obstacle position as 0 when filling out the matrix.
     * @param obstacleGrid
     * @return nums of paths
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        int[][] matrix = new int[m][n];
        matrix[0][0] = 1;
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] != 1)
                matrix[i][0] = matrix[i - 1][0];
        }
        for (int i = 1; i < n; i++) {
            if (obstacleGrid[0][i] != 1)
                matrix[0][i] = matrix[0][i - 1];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] != 1)
                    matrix[i][j] = matrix[i - 1][j] + matrix[i][j - 1];
                else
                    matrix[i][j] = 0;
            }
        }
        return matrix[m - 1][n - 1];
    }
}
