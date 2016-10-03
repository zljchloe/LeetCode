package Recursion;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by lyujiazhang on 10/2/16.
 *
 * Parentheses Problem
 */
public class GenerateParentheses {
    /**
     * https://leetcode.com/problems/generate-parentheses/
     * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
     * @param n: number of parentheses pairs
     * @return List of all possible valid combinations
     */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        generateParenthesisHelper(0, 0, n, sb, res);
        return res;
    }
    public void generateParenthesisHelper(int left, int right, int n, StringBuilder sb, List<String> res) {
        // Base case
        if (left == n && right == n) {
            res.add(sb.toString());
            return;
        }
        // Recursive call
        if (left < n) {
            // Add left parenthesis
            sb.append("(");
            generateParenthesisHelper(left + 1, right, n, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (right < left) {
            // Add right parenthesis
            sb.append(")");
            generateParenthesisHelper(left, right + 1, n, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    /**
     * Generate all valid permutations of l pairs of "()", m pairs of "[]", n pairs of "{}"
     * @param l: "()"
     * @param m: "[]"
     * @param n: "{}"
     * @return List of result
     */
    public List<String> generateParenthesisII(int l, int m, int n) {
        List<String> res = new ArrayList<>();
        // [l, l, m, m, n, n]
        int[] arr = new int[6];
        // [l, m, n]
        int[] remain = new int[] {l, m, n};
        int targetLen = 2 * (l + m + n);
        StringBuilder sb = new StringBuilder();
        char[] parentheses = new char[] {'(', ')', '[', ']', '{', '}'};
        Deque<Character> stack = new LinkedList<>();
        generateParenthesisIIHelper(arr, remain, targetLen, sb, res, parentheses, stack);
        return res;
    }
    public void generateParenthesisIIHelper(int[] arr, int[] remain, int targetLen, StringBuilder sb, List<String> res, char[] parentheses, Deque<Character> stack) {
        // Base case
        if (sb.length() == targetLen) {
            res.add(sb.toString());
            return;
        }
        // Recursive call
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) {
                // Left
                if (arr[i] < remain[i / 2]) {
                    sb.append(parentheses[i]);
                    stack.offerFirst(parentheses[i]);
                    arr[i]++;
                    generateParenthesisIIHelper(arr, remain, targetLen, sb, res, parentheses, stack);
                    sb.deleteCharAt(sb.length() - 1);
                    stack.pollFirst();
                    arr[i]--;
                }
            } else {
                // Right
                if (arr[i] < arr[i - 1] && !stack.isEmpty() && stack.peekFirst() == parentheses[i - 1]) {
                    sb.append(parentheses[i]);
                    stack.pollFirst();
                    arr[i]++;
                    generateParenthesisIIHelper(arr, remain, targetLen, sb, res, parentheses, stack);
                    sb.deleteCharAt(sb.length() - 1);
                    stack.offerFirst(parentheses[i - 1]);
                    arr[i]--;
                }
            }
        }
    }

    /**
     * https://leetcode.com/problems/longest-valid-parentheses/
     * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
     * 1. Maintain a stack to store index of left parenthesis.
     * 2. Maintain a start pointer to store the starting index of longest substring.
     * 3. If stack is empty, update start index (right parenthesis not matching up).
     * 4. If stack is not empty, pop from stack and update max length of longest substring (still valid).
     * @param s: input string
     * @return longest length of valid substring
     */
    public int longestValidParentheses(String s) {
        Deque<Integer> stack = new LinkedList<>();
        int max = 0;
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.offerFirst(i);
            } else {
                if (stack.isEmpty()) {
                    start = i +  1;
                } else {
                    stack.pollFirst();
                    max = Math.max(max, stack.isEmpty() ? i - start + 1 : i - stack.peekFirst());
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        GenerateParentheses gp = new GenerateParentheses();
//        System.out.println(gp.generateParenthesis(3));
        System.out.println(gp.generateParenthesisII(1, 1, 0));
    }
}
