package LinkedList;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lyujiazhang on 9/20/16.
 *
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 * https://leetcode.com/problems/lru-cache/
 *
 * 1. Maintain a doubly linked for Node<K, V>, in order to easily update the location of the node.
 * 2. Maintain a HashMap<K, Node<K, V>>, in order to check whether the node already exists in the list.
 * 3. Always keep track of the head and tail of the doubly linked list.
 * 4. Get(key): move the Node<K, V> to the head of the list.
 * 5. Set(key, value): if Node<K, V> already exists, move it to the head;
 *    Otherwise check the size of the map, if less than limit, create new node and append it to the head, if not, delete the tail before that.
 */
public class LRUCache {
    static class Node {
        int key;
        int value;
        Node prev;
        Node next;
        // constructor
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
        void update(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private Node head;
    private Node tail;
    private Map<Integer, Node> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<Integer, Node>();
    }

    public void set(int key, int value) {
        Node node = null;
        if (map.containsKey(key)) {
            // update the value and move the node to the head
            node = map.get(key);
            node.value = value;
            remove(node);
        } else if (map.size() < capacity) {
            node = new Node(key, value);
            // append the new node to the head
        } else {
            // remove the tail and append the new node to the head
            node = tail;
            if (node == null) {
                return;
            }
            remove(node);
            node.update(key, value);
        }
        append(node);
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        // move the node to the head
        remove(node);
        append(node);
        return node.value;
    }

    private Node remove(Node node) {
        map.remove(node.key);
        // if only one node
        if (head == tail) {
            head = tail = null;
        }
        // if remove head
        else if (node == head) {
            head = head.next;
        }
        // if remove tail
        else if (node == tail) {
            tail = tail.prev;
        }
        else {
            // if remove regular node
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        node.prev = node.next = null;
        return node;
    }

    private Node append(Node node) {
        map.put(node.key, node);
        if (head == null) {
            head = tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
            node.prev = null;
        }
        return node;
    }
}
