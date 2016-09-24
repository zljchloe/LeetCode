package BinaryTree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by lyujiazhang on 9/24/16.
 *
 * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
 *  For example:
 *  Given the following binary tree,
 *     1            <---
 *   /   \
 *  2    3         <---
 *   \    \
 *   5    4       <---
 *  You should return [1, 3, 4].
 * https://leetcode.com/problems/binary-tree-right-side-view/
 *
 * 1. DFS solution: Recurse to the right child then the left child.
 *                  Print when the res.size()==layer. (This will enable printing only the right side view's children)
 *
 * 2. BFS solution: Add each layer to a list.
 *                  Print the last indexed element in each layer.
 */
public class RightSideView {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * DFS solution
     * Time: O(n)
     * Space: O(n) worst case - call stack
     * @param root
     * @return result list
     */
    public List<Integer> rightSideViewDFS(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }
        helper(root, res, 0);
        return res;
    }
    private void helper(TreeNode root, List<Integer> res, int layer) {
        // Base case
        if (root == null) {
            return;
        }
        if (layer == res.size()) {
            res.add(root.val);
        }
        // Recursive call
        helper(root.right, res, layer + 1);
        helper(root.left, res, layer + 1);
    }

    /**
     * BFS solution
     * Time: O(n)
     * Space: O(n)
     * @param root
     * @return result list
     */
    public List<Integer> rightSideViewBFS(TreeNode root) {
        // Write your solution here.
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offerFirst(root);
        while(!queue.isEmpty()) {
            List<Integer> curLayer = new ArrayList<Integer>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.pollLast();
                if (cur.left != null) {
                    queue.offerFirst(cur.left);
                }
                if (cur.right != null) {
                    queue.offerFirst(cur.right);
                }
                curLayer.add(cur.val);
            }
            res.add(curLayer.get(curLayer.size() - 1));
        }
        return res;
    }
}
