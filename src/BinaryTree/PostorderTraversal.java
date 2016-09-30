package BinaryTree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by lyujiazhang on 9/21/16.
 * https://leetcode.com/problems/binary-tree-postorder-traversal/
 *
 * Given a binary tree, return the postorder traversal of its nodes' values.
 *
 * 1. Maintain a stack to keep track.
 * 2. Push root's right child to stack, and then push root, and iterate through the root's left child.
 * 3. Pop from stack and check if its right child is now at the top of the stack,
 *    If so, pop the top (right child) and push the parent back, root is now the popped element (right child).
 *    If not, add it to result and set root as null.
 * 4. Recurse above steps till the stack is empty.
 */
public class PostorderTraversal {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        do {
            if (root != null) {
                if (root.right != null) {
                    stack.offerFirst(root.right);
                }
                stack.offerFirst(root);
                root = root.left;
            } else {
                TreeNode tmp = stack.pollFirst();
                if (tmp.right != null && stack.size() > 0 && tmp.right == stack.peekFirst()) {
                    root = stack.pollFirst();
                    stack.offerFirst(tmp);
                } else {
                    res.add(tmp.val);
                    root = null;
                }
            }
        } while (!stack.isEmpty());
        return res;
    }

    public static void main(String[] args) {
        PostorderTraversal pt = new PostorderTraversal();
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        n1.right = n2;
        n2.left = n3;
        System.out.println(pt.postorderTraversal(n1));
    }
}
