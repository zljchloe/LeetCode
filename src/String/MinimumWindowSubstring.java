package String;

import java.util.HashMap;

/**
 * Created by lyujiazhang on 9/14/16.
 * https://leetcode.com/problems/minimum-window-substring/
 *
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T.
 *
 * 1. Maintain a window starting from the first index of String s, find the first end till all letters in t are included in s.
 * 2. Try to move the start index as right as possible to minimize the window.
 * 3. Move the window from left to right.
 */
public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();

        // Initialize HashMap
        for (int i = 0; i < t.length(); i++) {
            if (map.containsKey(t.charAt(i))) {
                map.put(t.charAt(i),  map.get(t.charAt(i)) + 1);
            } else {
                map.put(t.charAt(i), 1);
            }
        }
        int start = 0;
        int end = -1;

        // Set end
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), map.get(s.charAt(i)) - 1);
            }
            if (allInclude(map)) {
                end = i;
                break;
            }
        }
        if (end == -1) {
            return "";
        }
        int sol_start = start;
        int sol_end = end;
        end++;

        // Move window
        while (end <= s.length()) {
            // Move start to as right as possible
            if (allInclude(map)) {
                for (int i = start; i <= end; i++) {
                    if (map.containsKey(s.charAt(i))) {
                        map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
                        if (!allInclude(map)) {
                            start = i;
                            map.put(s.charAt(i), map.get(s.charAt(i)) - 1);
                            break;
                        }
                    }
                }
                sol_start = start;
                sol_end = end;
            }
            // Try to remove start
            if (map.containsKey(s.charAt(start))) {
                map.put(s.charAt(start), map.get(s.charAt(start)) + 1);
            }
            start++;
            // Try to add end
            if (end < s.length()) {
                if (map.containsKey(s.charAt(end))) {
                    map.put(s.charAt(end), map.get(s.charAt(end)) - 1);
                }
            }
            end++;
        }
        return s.substring(sol_start, sol_end);
    }

    private boolean allInclude(HashMap<Character, Integer> map) {
        for (char letter : map.keySet()) {
            if (map.get(letter) > 0) {
                // if not all letters in t has been matched
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        MinimumWindowSubstring mws = new MinimumWindowSubstring();
        System.out.println(mws.minWindow("acbbaca", "aba"));
    }
}

