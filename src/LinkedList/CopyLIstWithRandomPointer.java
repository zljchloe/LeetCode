package LinkedList;

import java.util.HashMap;

/**
 * Created by lyujiazhang on 9/20/16.
 *
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
 * Return a deep copy of the list.
 *  __________
 * |          |
 * 1 -> 2 -> 3 -> 4
 *      |_________|
 *
 * https://leetcode.com/problems/copy-list-with-random-pointer/
 *
 * 1. Solution 1 uses HashMap with key = original list node and value = copied list node.
 *    Whenever generating a new node, check if the node has been copied before using the HashMap.
 *
 * 2. Solution 2 optimized the space without using HashMap.
 *    1 -> 1' -> 2 -> 2' -> 3 -> 3' -> 4 -> 4'
 *    Copy each node and insert it after its original node.
 *    The original's random node's next node is the copied node's random node.
 *    Connect even indexed nodes to return a new list.
 */
public class CopyListWithRandomPointer {
    static class RandomListNode {
        int label;
        RandomListNode next, random;
        RandomListNode(int x) { this.label = x; }
    };

    /**
     * Solution with the help of HashMap.
     * Time: O(n)
     * Space: O(n)
     * @param head
     * @return head of deep copied list
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode cur = head;
        while (cur != null) {
            if (!map.containsKey(head)) {
                RandomListNode copyCur = new RandomListNode(head.label);
                map.put(cur, copyCur);
            }
            if (cur.next != null && !map.containsKey(cur.next)) {
                RandomListNode copyNext = new RandomListNode(cur.next.label);
                map.put(cur.next, copyNext);
            }
            if (cur.random != null && !map.containsKey(cur.random)) {
                RandomListNode copyRan = new RandomListNode(cur.random.label);
                map.put(cur.random, copyRan);
            }
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    /**
     * Solution without using HashMap, but the original list's structure can be modified.
     * Time: O(3n)
     * Space: O(constant)
     * @param head
     * @return head of deep copied list
     */
    public RandomListNode copyRandomListWithoutHashMap(RandomListNode head) {
        if (head == null) {
            return null;
        }
        RandomListNode cur = head;
        while (cur != null) {
            RandomListNode copyCur = new RandomListNode(cur.label);
            copyCur.next = cur.next;
            cur.next = copyCur;
            cur = copyCur.next;
        }
        cur = head;
        while (cur != null) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }
        cur = head.next;
        while (cur.next != null) {
            cur.next = cur.next.next;
            cur = cur.next;
        }
        return head.next;
    }

    public static void main(String[] args) {
        CopyListWithRandomPointer cl = new CopyListWithRandomPointer();
        RandomListNode head = new RandomListNode(1);
        RandomListNode l2 = new RandomListNode(2);
        RandomListNode l3 = new RandomListNode(3);
        RandomListNode l4 = new RandomListNode(4);
        head.next = l2;
        l2.next = l3;
        l3.next = l4;
        head.random = l3;
        l2.random = l4;
        //RandomListNode newHead = cl.copyRandomList(head);
        RandomListNode newHead= cl.copyRandomListWithoutHashMap(head);
        while (newHead != null) {
            System.out.println(newHead.label);
            newHead = newHead.next;
        }
    }
}
