/*

LRU Cache

Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.


解：
使用HashMap来存储数据。
存储的内容为linked list节点。
用该linked list来存储使用先后信息。
每次使用某一个key的值(包括set和get)后，就把这个node移动到队头。
这样数据就会按照使用先后排序。当cache满了就把队尾删掉。

为了方便操作，使用双向队列，同时使用冗余的队头和队尾节点。

*/



public class Solution {
    private class ListNode {
        int key;
        int value;
        ListNode prev, next;
        ListNode(int key, int value) {
            this.key = key;
            this.value = value;
            prev = next = null;
        }
    }

    int capacity;
    ListNode head, tail;
    HashMap<Integer, ListNode> cache;

    // @param capacity, an integer
    public Solution(int capacity) {
        cache = new HashMap<Integer, ListNode>();
        this.capacity = capacity;
        head = new ListNode(0, 0);
        tail = new ListNode(0, 0);
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
        ListNode node;
        if (cache.containsKey(key)) {
            node = cache.get(key);
            node.value = value;
        } else {
            node = new ListNode(key, value);
        }
        
        moveToHead(node);
        cache.put(key, node);
        
        // remove LRU node
        if (cache.size() > capacity && capacity >= 0) {
            cache.remove(tail.prev.key);
            removeTail();
        }
    }
    
    private void moveToHead(ListNode node) {
        if (node.prev != null && node.next != null) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
    
    private void removeTail() {
        tail.prev.prev.next = tail;
        tail.prev = tail.prev.prev;
    }
}