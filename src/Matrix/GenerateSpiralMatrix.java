package Matrix;

/**
 * Created by lyujiazhang on 9/30/16.
 * https://leetcode.com/problems/spiral-matrix-ii/
 *
 * Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
 * For example,
 * Given n = 3,
 * You should return the following matrix:
 * [
 * [ 1, 2, 3 ],
 * [ 8, 9, 4 ],
 * [ 7, 6, 5 ]
 * ]
 *
 * 1. Recursively generate the upper row, right col, bottom row and left col.
 * 2. Helper function inputs include the num of row/col to be generated, the offset, the number to be added.
 * 3. Base case is when left nothing, just return, when only one position left, generate it.
 */
public class GenerateSpiralMatrix {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        helper(matrix, n, 0, 1);
        return matrix;
    }

    public void helper(int[][] matrix, int n, int offset, int count) {
        // Base case
        if (n == 0) return;
        if (n == 1) {
            matrix[offset][offset] = count;
            return;
        }
        // Recursive call
        for (int i = 0; i < n - 1; i++) {
            // Generate upper row
            matrix[offset][offset + i] = count++;
        }
        for (int i = 0; i < n - 1; i++) {
            // Generate right col
            matrix[offset + i][matrix[0].length - 1 - offset] = count++;
        }
        for (int i = 0; i < n - 1; i++) {
            // Generate bottom row
            matrix[matrix.length - 1 - offset][matrix[0].length - 1 - offset - i] = count++;
        }
        for (int i = 0; i < n - 1; i++) {
            // Generate left col
            matrix[matrix.length - 1 - offset - i][offset] = count++;
        }
        helper(matrix, n - 2, offset + 1, count);
    }

}
