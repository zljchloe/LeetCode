package Matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyujiazhang on 9/30/16.
 *
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 * For example,
 * Given the following matrix:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * You should return [1,2,3,6,9,8,7,4,5].
 * https://leetcode.com/problems/spiral-matrix/
 *
 * 1. Recursively print the upper row, right col, bottom row and left col.
 * 2. Helper function inputs include the nums of row/col to be printed, the offset.
 * 3. Base case is when left nothing, just return, when only one row or one col left, print it.
 */
public class SpiralMatrixPrint {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return res;
        }
        helper(matrix, matrix.length, matrix[0].length, 0, res);
        return res;
    }

    public void helper(int[][] matrix, int m, int n, int offset, List<Integer> res) {
        // Base case
        if (m == 0 || n == 0) return;
        if (m == 1) {
            // Print the only row left
            for (int i = 0; i < n; i++) {
                res.add(matrix[offset][offset + i]);
            }
            return;
        }
        if (n == 1) {
            // Print the only col left
            for (int i = 0; i < m; i++) {
                res.add(matrix[offset + i][offset]);
            }
            return;
        }
        // Recursive call
        for (int i = 0; i < n - 1; i++) {
            // Print upper row
            res.add(matrix[offset][offset + i]);
        }
        for (int i = 0; i < m - 1; i++) {
            // Print right col
            res.add(matrix[offset + i][matrix[0].length - 1 - offset]);
        }
        for (int i = 0; i < n - 1; i++) {
            // Print bottom row
            res.add(matrix[matrix.length - 1 - offset][matrix[0].length - 1 - offset - i]);
        }
        for (int i = 0; i < m - 1; i++) {
            // Print left col
            res.add(matrix[matrix.length - 1 - offset - i][offset]);
        }
        helper(matrix, m - 2, n - 2, offset + 1, res);
    }

    public static void main(String[] args) {
        SpiralMatrixPrint smp = new SpiralMatrixPrint();
        System.out.println(smp.spiralOrder(new int[][]{{1}}));
    }
}
