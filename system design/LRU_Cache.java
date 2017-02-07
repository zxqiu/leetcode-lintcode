/*

LRU Cache

Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.


解：
Linked list

首先思考要实现LRU，需要哪些信息。
一是Key-Value对，使用一个HashMap来保存即可。
二是使用顺序信息，即按照最近使用的顺序来排序。升序降序都可以，这里把最近使用的节点放在最前面。
有很多方案可以保存这个信息，比如最容易想到的是用一个ArrayList。每次一个节点被访问时，把它从ArrayList中删除，然后重新添加到队首。这里删除操作的时间复杂度为O(n)。也就是说set和get操作复杂度都为O(n)。
还可以使用一个Linked list来保存顺序信息。为了操作快速直接使用一个双向链表，并且让HashMap的value直接为链表的节点以快速查找。这样，每次一个节点被访问时，只需要把链表节点移动到队首即可，时间复杂度为O(1)。

*/


public class Solution {
    private class Node {
        int key;
        int value;
        Node next, prev;
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            next = null;
            prev = null;
        }
    }
    
    Map<Integer, Node> cache;
    Integer capacity;
    Node head, tail;

    // @param capacity, an integer
    public Solution(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    // @return an integer
    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        
        moveToHead(cache.get(key));
        return cache.get(key).value;
    }

    // @param key, an integer
    // @param value, an integer
    // @return nothing
    public void set(int key, int value) {
        if (!cache.containsKey(key)) {
            cache.put(key, new Node(key, value));
        }
        
        cache.get(key).value = value;
        moveToHead(cache.get(key));
        
        if (cache.size() > capacity) {
            cache.remove(removeLRUNode().key);
        }
    }
    
    private void moveToHead(Node node) {
        if (node.next != node.prev) {
            // existing node. dequeue first.
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        
        // insert to head
        node.prev = head;
        node.next = head.next;
        head.next = node;
        node.next.prev = node;
    }
    
    private Node removeLRUNode() {
        Node removedNode = tail.prev;
        tail.prev = tail.prev.prev;
        tail.prev.next = tail;
        
        return removedNode;
    }
}
