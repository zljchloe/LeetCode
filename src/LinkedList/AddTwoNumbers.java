package LinkedList;

/**
 * Created by lyujiazhang on 9/19/16.
 * https://leetcode.com/problems/add-two-numbers/
 *
 * You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 *
 * 1. Add two numbers accordingly, maintain a carry number to determine whether need to add 1 on the next bit.
 * 2. Don't forget to add the extra node (1) if the carry number is 1 when reached the end of list.
 */
public class AddTwoNumbers {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        } else if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int sum = 0;
        while (l1 != null && l2 != null) {
            sum /= 10;
            sum = sum + l1.val + l2.val;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        ListNode remain = l1 != null ? l1 : l2;
        while (remain != null) {
            sum /= 10;
            if (sum == 1) {
                sum = sum + remain.val;
                cur.next = new ListNode(sum % 10);
                cur = cur.next;
                remain = remain.next;
            } else {
                cur.next = remain;
                break;
            }
        }

        if (sum / 10 == 1) {
            cur.next = new ListNode(1);
        }
        return dummy.next;
    }

    /**
     * Add two numbers with orders maintained.
     * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
     * Output: 8 -> 0 -> 7
     *
     * Here's the solution without changing the data structure of given input. (without reversing the original lists)
     * Assumption: Two lists are of the same length. (If not, make the shorter list filled with Node(0) in the front till reaching the same length)
     *
     * 1. Maintain a new Node class with carry number also stored.
     * 2. Helper function to recurse from the last list node till the first one.
     * 3. Check if need to add Node(1) when done with the helper recursive function.
     *
     * @param l1
     * @param l2
     * @return Head of new list
     */
    public Node addTwoNumbersWithOrder(ListNode l1, ListNode l2) {
        Node head = addTwoNumbersWithOrderHelper(l1, l2);
        // If need to add 1 at the beginning
        if (head.carry == 1) {
            Node newHead = new Node(1, 0);
            newHead.next = head;
            return newHead;
        }
        return head;
    }

    public Node addTwoNumbersWithOrderHelper(ListNode l1, ListNode l2) {
        // base case
        if (l1.next == null) {
            return new Node((l1.val + l2.val) % 10, (l1.val + l2.val) / 10);
        }
        // recursive call
        Node head = addTwoNumbersWithOrderHelper(l1.next, l2.next);
        int value = head.carry + l1.val + l2.val;
        Node newHead = new Node(value % 10, value / 10);
        newHead.next = head;
        return newHead;
    }

    static class Node {
        int value;
        int carry;
        Node next;
        public Node(int value, int carry) {
            this.value = value;
            this.carry = carry;
        }
    }

    public static void main(String[] args) {
        AddTwoNumbers atn = new AddTwoNumbers();
        ListNode l1 = new ListNode(5);
        ListNode l11 = new ListNode(4);
        ListNode l12 = new ListNode(3);
        ListNode l13 = new ListNode(5);
        l1.next = l11;
        l11.next = l12;
        //l12.next = l13;
        ListNode l2 = new ListNode(5);
        ListNode l21 = new ListNode(6);
        ListNode l22 = new ListNode(4);
        l2.next = l21;
        l21.next = l22;
        ListNode res = atn.addTwoNumbers(l1, l2);
        //Node res = atn.addTwoNumbersWithOrder(l1, l2);
        while(res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }
}
