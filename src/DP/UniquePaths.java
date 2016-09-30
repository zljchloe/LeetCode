package DP;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
     * @param obstacleGrid: Input matrix with obstacles value 1
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

    /**
     * Followup: Find one path from top-left to bottom-right in the matrix with obstacles.
     * Recursively checking from bottom-right to top-left.
     * @param matrix: Input matrix with obstacles value false
     * @param x: current point's row num
     * @param y: current point's col num
     * @param path: adding point from top-left to bottom-right (bottom-up recursion).
     * @param mem: contains point with value true but the upper and left value false, which means no path passing this point
     * @return boolean value to flag if the point can be reached
     */
    public boolean getPath(boolean[][] matrix, int x, int y, List<Point> path, Set<Point> mem) {
        Point p = new Point(x,y);
        if (x < 0 || y < 0 || mem.contains(p) || !matrix[x][y]) {
//            System.out.println(p);
            return false;
        }
        boolean isOrigin = x == 0 && y == 0;
        if (isOrigin || getPath(matrix, x, y - 1, path, mem) || getPath(matrix, x - 1, y, path, mem)) {
            path.add(p);
            return true;
        }
        mem.add(p);
//        System.out.println(mem);
        return false;
    }
    static class Point {
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Point)) {
                return false;
            }
            Point p1 = (Point) obj;
            return (p1.x == this.x && p1.y == this.y);
        }
        @Override
        public String toString() {
            return "(" + this.x + "," + this.y + ")";
        }
    }
    // Test case
    public static void main(String[] args) {
        UniquePaths r = new UniquePaths();
        boolean[][] matrix = {{true, false}, {true, true}, {true, true}};
        List<Point> path = new ArrayList<>();
        Set<Point> mem = new HashSet<>();
        r.getPath(matrix, 2, 1, path, mem);
        System.out.println(path);
    }
}
