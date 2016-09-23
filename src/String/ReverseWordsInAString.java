package String;

/**
 * Created by lyujiazhang on 9/22/16.
 *
 * Given an input string, reverse the string word by word.
 * For example,
 * Given s = "the sky is blue",
 * return "blue is sky the".
 * https://leetcode.com/problems/reverse-words-in-a-string/
 *
 * 1. Remove leading and trailing spaces.
 * 2. Remove spaces between words till only one space is left.
 * 3. Reverse the all array.
 * 4. Reverse word by word.
 */
public class ReverseWordsInAString {
    public String reverseWords(String s) {
        char[] array = s.trim().toCharArray();
        if (array.length == 0) {
            return "";
        }
        int j = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == ' ' && (i == 0 || array[i - 1] == ' ')) {
                continue;
            }
            array[j++] = array[i];
        }
        reverse(array, 0, j - 1);
        int start = 0;
        for (int i = 0; i < j; i++) {
            if (array[i] != ' ' && (i == 0 || array[i- 1] == ' ')) {
                start = i;
            }
            if (array[i] != ' ' && (i == j - 1 || array[i + 1] == ' ')) {
                reverse(array, start, i);
            }
        }
        String res = new String(array);
        return res.substring(0, j);
    }

    private void reverse(char[] array, int start, int end) {
        while(start < end) {
            char tmp = array[start];
            array[start] = array[end];
            array[end] = tmp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        ReverseWordsInAString e = new ReverseWordsInAString();
        System.out.println(e.reverseWords("   one.   +two three?   ~four   !five- "));
    }
}
