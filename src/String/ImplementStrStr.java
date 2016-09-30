package String;

/**
 * Created by lyujiazhang on 9/20/16.
 * https://leetcode.com/problems/implement-strstr/
 *
 * Implement strStr().
 * Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 *
 * Robin Karp solution with time complexity of O(m+n).
 * 1. Calculate the hashValue of needle.
 * 2. Calculate the hashValue of haystack's sliding widow with size needle.length().
 * 3. HashValue is calculated as following:
 *    Imaging the case where there are only 10 characters,
 *    123 -> hashVal = ((0 * 10 + 1) * 10 + 2) * 10 + 3
 */
public class ImplementStrStr {
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null || haystack.length() < needle.length()) {
            return -1;
        }
        long val = 0;
        // calculate needle's hash value
        for (int i = 0; i < needle.length(); i++) {
            val = val * 26 + (long) needle.charAt(i) - 'a';
        }
        long hashVal = 0;
        // calculate the first sliding window's hash value of haystack
        for (int i = 0; i < needle.length(); i++) {
            hashVal = hashVal * 26 + (long) haystack.charAt(i) - 'a';
        }
        if (hashVal == val) {
            return 0;
        }
        // move the sliding window and calculate the hash value
        for (int i = needle.length(); i < haystack.length(); i++) {
            hashVal = hashVal % (long) Math.pow(26, needle.length() - 1);
            hashVal = hashVal * 26 + (long) haystack.charAt(i) - 'a';
            if (hashVal == val) {
                return i - needle.length() + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        ImplementStrStr iss = new ImplementStrStr();
        System.out.println(iss.strStr("mississippi", "issi"));
    }
}
