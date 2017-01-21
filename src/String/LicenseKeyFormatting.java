package String;

/**
 * Created by lyujiazhang on 1/21/17.
 * https://leetcode.com/problems/license-key-formatting/
 *
 * Now you are given a string S, which represents a software license key which we would like to format. The string S is composed of alphanumerical characters and dashes. The dashes split the alphanumerical characters within the string into groups. (i.e. if there are M dashes, the string is split into M+1 groups). The dashes in the given string are possibly misplaced.
 * We want each group of characters to be of length K (except for possibly the first group, which could be shorter, but still must contain at least one character). To satisfy this requirement, we will reinsert dashes. Additionally, all the lower case letters in the string must be converted to upper case.
 * So, you are given a non-empty string S, representing a license key to format, and an integer K. And you need to return the license key formatted according to the description above.
 * Example 1:
 * Input: S = "2-4A0r7-4k", K = 4
 * Output: "24A0-R74K"
 * Explanation: The string S has been split into two parts, each part has 4 characters.
 * Example 2:
 * Input: S = "2-4A0r7-4k", K = 3
 * Output: "24-A0R-74K"
 * Explanation: The string S has been split into three parts, each part has 3 characters except the first part as it could be shorter as said above.
 * Note:
 * The length of string S will not exceed 12,000, and K is a positive integer.
 * String S consists only of alphanumerical characters (a-z and/or A-Z and/or 0-9) and dashes(-).
 * String S is non-empty.
 *
 * O(n) solution iterating through the string twice.
 * 1. Iterate through the string the first time to count the number of dashes.
 * 2. Count the number of chars in the first group (first < K).
 * 3. Append the first group (# of chars < K) to the res.
 * 4. If first group is not empty, insert dash before appending what's remained.
 * 5. Append remaining groups, adding dashes every K steps.
 */
public class LicenseKeyFormatting {
    public String licenseKeyFormatting(String S, int K) {
        char[] input = S.toCharArray();
        int countDash = 0;
        for (int i = 0; i < input.length; i++) {
            if (input[i] == '-') {
                countDash++;
            }
        }
//        int groups = (input.length - countDash) / K;
        int first = (input.length - countDash) % K;
        StringBuilder sb = new StringBuilder();
        int i = 0;
        // First group
        while (i < first) {
            if (input[i] == '-') {
                first++;
            } else {
                sb.append(Character.toUpperCase((input[i])));
            }
            i++;
        }
        // If first group is not empty, insert dash before appending
        if (i != 0 && input.length - countDash > K) {
            sb.append('-');
        }
        // Remaining groups
        int count = 0;
        while (i < input.length) {
            if (input[i] != '-') {
                if (count != 0 && count % K == 0) {
                    sb.append('-');
                    count = 0;
                    continue;
                } else {
                    sb.append(Character.toUpperCase(input[i]));
                    count++;
                }
            }
            i++;
//            System.out.println("count " + count);
//            System.out.println("i " + i);
//            System.out.println("res " + sb.toString());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LicenseKeyFormatting l = new LicenseKeyFormatting();
        String res = l.licenseKeyFormatting("2", 2);
        System.out.println(res);
    }
}
