package DP;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyujiazhang on 10/3/16.
 * https://leetcode.com/problems/longest-increasing-subsequence/
 *
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * For example,
 * Given [10, 9, 2, 5, 3, 7, 101, 18],
 * The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4.
 *
 * 1. O(nlogn) time algorithm using binary search and extra space of O(n).
 * 2. Base case: M[i] = 1
 *    Induction rule: M[i] = Max(M[i], M[j] + 1) if nums[j] < nums[i], for 0 <= j < i
 * 3. Maintain an extra sorted array, while scanning the original array,
 *    find the index of the first number that is larger than current number,
 *    replace it if found, append to the end if not found.
 * 3. Update the dp array with the index of the newly inserted position, which is the length of LIS so far.
 */
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        int max = 1;
        Integer[] mem = new Integer[nums.length];
        mem[0] = nums[0];
        for (int i = 1; i < mem.length; i++) {
            mem[i] = null;
        }
        // For each num find dp[i] which is the LIS's length so far at position i
        for (int i = 1; i < nums.length; i++) {
            int index = findFirstLarger(mem, nums[i]);
            mem[index] = nums[i];
            dp[i] = index + 1;
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    private int findFirstLarger(Integer[] mem, int target) {
        int left = 0;
        int right = mem.length - 1;

        // Find first non null and set it as right bound
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (mem[mid] == null) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        right = mem[right] == null ? left : right;
        left = 0;

        // Find first number larger than target's index
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (mem[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        // [left, right]
        if (mem[right] < target) {
            // insert target at the end
            return right + 1;
        } else if (mem[left] >= target) {
            // replace left
            return left;
        } else {
            // replace right
            return right;
        }
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
        System.out.println(lis.lengthOfLIS(new int[] {10, 9, 2, 5, 3, 7, 101, 18}));
    }
}
