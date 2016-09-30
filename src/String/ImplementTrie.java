package String;

import java.util.HashMap;

/**
 * Created by lyujiazhang on 9/19/16.
 * https://leetcode.com/problems/implement-trie-prefix-tree/
 *
 * Implement a trie with insert, search, and startsWith methods.
 *
 * 1. Insert: From the first index of the string, check if the current character is current node's child.
 *            If yest, go to the next index, if no, insert a new node to the children of current node and go to next index.
 *
 * 2. Search: From the first index of the string, check if the current character is current node's child.
 *            If yes, go to the next index till the last one and check whether the last character's isLeaf field is true. If no, return false;
 *
 * 3. StartWith: Same with search, without the step of checking whether the last character's isLeaf field.
 */
public class ImplementTrie {
    static class TrieNode {
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

    static public class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        // Inserts a word into the trie.
        public void insert(String word) {
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

        // Returns if the word is in the trie.
        public boolean search(String word) {
            TrieNode cur = root;
            int layer = 0;
            while (layer < word.length()) {
                char curWord = word.charAt(layer);
                if (cur.children.containsKey(curWord)) {
                    cur = cur.children.get(curWord);
                    layer++;
                } else {
                    return false;
                }
            }
            if (!cur.isLeaf) {
                return false;
            }
            return true;
        }

        // Returns if there is any word in the trie
        // that starts with the given prefix.
        public boolean startsWith(String prefix) {
            TrieNode cur = root;
            int layer = 0;
            while (layer < prefix.length()) {
                char curWord = prefix.charAt(layer);
                if (cur.children.containsKey(curWord)) {
                    cur = cur.children.get(curWord);
                    layer++;
                } else {
                    return false;
                }
            }
            return true;
        }
    }

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("a");
        System.out.println(trie.search("ab"));
        trie.insert("ab");
        System.out.println(trie.search("ab"));
    }
}
