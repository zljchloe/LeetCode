package Array;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by lyujiazhang on 9/30/16.
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 *
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
 * find the area of largest rectangle in the histogram.
 *
 * 1. Maintain a stack, push from left to right (index), push 0 when reached to the end (to ensure popping).
 * 2. When cur value is smaller than or equal to stack.pop(),
 *    calculate using stack.pop()'s value as height,
 *    width is calculated by cur index - stack.pop().
 * 3. When cur value is bigger than stack.peek()'s value, push it to the stack.
 */
public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new LinkedList<>();
        int res = 0;
        for (int i = 0; i <= heights.length; i++) {
            int cur = i == heights.length ? 0 : heights[i];
            while (!stack.isEmpty() && heights[stack.peekFirst()] >= cur) {
                int height = heights[stack.pollFirst()];
                int left = stack.isEmpty() ? 0 : stack.peekFirst() + 1;
                res = Math.max(res, height * (i - left));
            }
            stack.offerFirst(i);
        }
        return res;
    }
}
