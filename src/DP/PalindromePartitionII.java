package DP;

import java.util.*;

/**
 * Created by lyujiazhang on 10/3/16.
 * https://leetcode.com/problems/palindrome-partitioning-ii/
 *
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * For example, given s = "aab",
 * Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
 */
public class PalindromePartitionII {
    /**
     * Dp optimized solution with run time O(n^2)
     * 1. Base case: M[1] = 1
     * 2. Induction rule: M[i] = min(M[i], M[j - 1] + 1) for j = i - 1...1 if s.substring(j,i) is palindrome.
     * 3. Maintain a isPa matrix, isPa[j][i] represents if s.substring(j,i) is a palindrome.
     * 4. Update isPa matrix while updating M[i].
     * @param s: input string
     * @return minimum cuts
     */
    public int minCutsOp(String s) {
        char[] arr = s.toCharArray();
        if (arr.length == 0) {
            return 0;
        }
        int[] cut = new int[arr.length];
        boolean[][] isPa = new boolean[cut.length][cut.length];
        for (int i = 0; i < arr.length; i++) {
            // i: end index
            cut[i] = i;
            for (int j = 0; j <= i; j++) {
                // j : start index
                if (arr[j] == arr[i]) {
                    // Start and end equal, keep matching chars between start and end
                    isPa[j][i] = i - j < 2 || isPa[j + 1][i - 1];
                }
                if (isPa[j][i]) {
                    // Update solution cut at index i
                    cut[i] = Math.min(cut[i], j == 0 ? 0 : Math.min(cut[i], cut[j - 1] + 1));
                }
            }
        }
        return cut[arr.length - 1];
    }

    /**
     * DP solution with run time O(n^3)
     * 1. Base case: M[1] = 1
     * 2. Induction rule: M[i] = min(M[i], M[j] + 1) for j = i - 1...1
     * @param s: input string
     * @return minimum cuts
     */
    public int minCuts(String s) {
        // Write your solution here.
        char[] arr = s.toCharArray();
        if (arr.length == 0) {
            return 0;
        }
        int[] cut = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            cut[i] = i;
            // if string is already a palindrome, no cut is needed
            if (isPa(arr, 0, i)) {
                cut[i] = 0;
            }
            for (int j = i - 1; j >= 0; j--) {
                // if current cut can form palindromes
                if (isPa(arr, j + 1, i)) {
                    cut[i] = Math.min(cut[i], 1 + cut[j]);
                }
            }
        }
        return cut[arr.length - 1];
    }
    private boolean isPa(char[] arr, int start, int end) {
        while (start < end) {
            if (arr[start++] != arr[end--]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Memoization solution
     * Maintain a map to store calculated min cuts of a string.
     * @param s: input string
     * @return minimum cuts
     */
    public int minCutsMemo(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] arr = s.toCharArray();
        Map<String, Integer> map = new HashMap<>();
        return helper(arr, 0, s.length(), map);
    }
    private int helper(char[] arr, int start, int end, Map<String, Integer> map) {
        String str = new String(Arrays.copyOfRange(arr, start, end));
        if (map.containsKey(str)) {
            return map.get(str);
        }
        if (isPa(arr, start, end - 1)) {
            map.put(str, 0);
        } else {
            int cuts = Integer.MAX_VALUE;
            for (int i = start + 1; i < end; i++) {
                cuts = Math.min(cuts, helper(arr, 0, i, map) + helper(arr, i, end, map) + 1);
            }
            map.put(str, cuts);
        }
        return map.get(str);
    }

    public static void main(String[] args) {
        PalindromePartitionII pp = new PalindromePartitionII();
        System.out.println(pp.minCutsOp("aab"));
    }
}
