package String;

import java.util.HashMap;

/**
 * Created by lyujiazhang on 9/19/16.
 *
 * Design a data structure to add and search word.
 * https://leetcode.com/problems/add-and-search-word-data-structure-design/
 *
 * 1. AddWord: From the first index of the string, check if the current character is current node's child.
 *             If yest, go to the next index, if no, insert a new node to the children of current node and go to next index.
 *
 * 2. Search: Recursively check from the first index of the string,
 *            if the current character is '.', recurse n times where n is the number of current node's children,
 *            if is not '.', check current character and recurse to next character.
 *
 */
public class AddAndSearchWord {

    public static class WordDictionary {

        class TrieNode {
            char c;
            HashMap<Character, TrieNode> children;
            boolean isLeaf;

            public TrieNode() {
                children = new HashMap<Character, TrieNode>();
            }
            public TrieNode(char c) {
                this.c = c;
                children = new HashMap<Character, TrieNode>();
            }
        }

        private TrieNode root;

        public WordDictionary() {
            root = new TrieNode();
        }
        // Adds a word into the data structure.
        public void addWord(String word) {
            int layer = 0;
            TrieNode cur = root;
            while (layer < word.length()) {
                char curWord = word.charAt(layer);
                if (cur.children.containsKey(curWord)) {
                    cur = cur.children.get(curWord);
                } else {
                    TrieNode newNode = new TrieNode(curWord);
                    cur.children.put(curWord, newNode);
                    cur = newNode;
                }
                if (layer == word.length() - 1) {
                    cur.isLeaf = true;
                }
                layer++;
            }
        }

        // Returns if the word is in the data structure. A word could
        // contain the dot character '.' to represent any one letter.
        public boolean search(String word) {
            return helper(word, root, 0);
        }

        boolean helper(String word, TrieNode cur, int index) {
            // base case
            if (index == word.length()) {
                return cur.isLeaf;
            }

            //recursive call
            if (word.charAt(index) == '.') {
                for (TrieNode curNode : cur.children.values()) {
                     if(helper(word, curNode, index + 1)) {
                         return true;
                     }
                }
                return false;
            } else {
                if (cur.children.containsKey(word.charAt(index))) {
                    return helper(word, cur.children.get(word.charAt(index)), index + 1);
                } else {
                    return false;
                }
            }
        }
    }

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.search("pad"));
        System.out.println(wordDictionary.search("bad"));
        System.out.println(wordDictionary.search(".ad"));
        System.out.println(wordDictionary.search("b.."));
    }
}
