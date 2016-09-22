package LinkedList;

/**
 * Created by lyujiazhang on 9/21/16.
 *
 * Given a singly linked list, determine if it is a palindrome.
 * https://leetcode.com/problems/palindrome-linked-list/
 *
 * 1. Use fast and slow pointer to find the middle of the linked list.
 * 2. Reverse the second half of the list.
 * 3. Use two pointers to compare first half and second half of the list.
 */
public class PalindromeLinkedList {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode newHead = reverseList(slow);
        while (newHead != null && head != null) {
            if (head.val != newHead.val) {
                return false;
            }
            head = head.next;
            newHead = newHead.next;
        }
        // If first half and second half is not of the same length
        if (newHead != null) {
            return false;
        }
        return true;
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode next;
        while (head != null) {
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(5);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(1);
        ListNode n4 = new ListNode(3);
        ListNode n5 = new ListNode(3);
        ListNode n6 = new ListNode(1);
        ListNode n7 = new ListNode(2);
        ListNode n8 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;
        PalindromeLinkedList pl = new PalindromeLinkedList();
        System.out.println(pl.isPalindrome(n1));
    }
}
