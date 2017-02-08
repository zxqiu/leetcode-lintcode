/*

LFU Cache

LFU (Least Frequently Used) is a famous cache eviction algorithm.

For a cache with capacity k, if the cache is full and need to evict a key in it, the key with the lease frequently used will be kicked out.

Implement set and get method for LFU cache.

 Example

Given capacity=3

set(2,2)
set(1,1)
get(2)
>> 2
get(1)
>> 1
get(2)
>> 2
set(3,3)
set(4,4)
get(3)
>> -1
get(2)
>> 2
get(1)
>> 1
get(4)
>> 4


解：
这道题实现相对复杂，但是思路不难理解。
需要保存的信息有两个，一是key-value对，二是使用频率信息(在这里简化为使用次数信息)。
key-value对使用HashMap来保存即可。
使用频率信息与LRU Cache类似，使用双向链表保存(请参阅题目LRU Cache)，不同的是，这里需要两种频率信息，一是不同的频率，二是同一频率下节点的先后顺序。
保存同一频率下节点先后顺序是为了在最小使用频率有多个节点可以选择删除时，删除调最早被访问的节点。
多以对于频率信息，首先用一个链表保存不同的频率，然后每个频率的节点上都保存一个子链表，用来保存所有该频率的节点。如下

频率1->队首->节点->节点->...->节点->队尾
 ^
 |
频率2->队首->节点->节点->...->节点->队尾
 ^
 |
频率3->队首->节点->节点->...->节点->队尾
 .
 .
 .
频率n->队首->节点->节点->...->节点->队尾


如此，在需要删除LFU节点时，需要先找到最低非空频率链表，然后删除该链表的最后一个节点。
实现时为了快速查找频率表和实现简单，使用LinkedHashMap来保存频率链表，然后写一个双向链表用作每个频率的子链表。这样除了删除LFU节点时间复杂度为O(n)，n为可能频率的数量，其他操作均为O(1)。

每次插入一个新的K-V对时，如果对应的频率不在频率map中，就在频率map中插入一个新的频率(一定是更高频率)。当删除或者移动一个K-V对时，就算当前频率的节点列表为空也不能删除，否则重新插入该频率时会插入频率map的最后，导致低频率排在高频率后面。这是LinkedHashMap只能在最后插入的特性所致。这也是导致删除LFU节点时间复杂度为O(n)的原因。

如果想要解决这个问题，可以用一个自定义的链表来保存频率链表，这样删除LFU可以变成O(1)。


*/


public class LFUCache {
    private class Node {
        int key, value, freq;
        Node prev, next;
        Node(int key, int value, int freq) {
            this.key = key;
            this.value = value;
            this.freq = freq;
            prev = next = null;
        }
    }
    
    Map<Integer, Node[]> freqMap;
    Map<Integer, Node> cache;
    int capacity;

    // @param capacity, an integer
    public LFUCache(int capacity) {
        this.capacity = capacity;
        freqMap = new LinkedHashMap<>();
        cache = new HashMap<>();
    }

    // @param key, an integer
    // @param value, an integer
    // @return nothing
    public void set(int key, int value) {
        if (!cache.containsKey(key)) {
            if (cache.size() == capacity) {
                int deletedKey = removeLFUNodeFromFreq();
                if (deletedKey >= 0) {
                    cache.remove(deletedKey);
                }
            }
            cache.put(key, new Node(key, value, 0));
        }
        cache.get(key).value = value;
        
        refreshNodeFreq(cache.get(key));
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        
        refreshNodeFreq(cache.get(key));
        return cache.get(key).value;
    }
    
    // return head and tail of the linked list of specific freq
    private Node[] getFreqList(int freq) {
        if (freqMap.containsKey(freq)) {
            return freqMap.get(freq);
        }
        
        Node head = new Node(0, 0, 0);
        Node tail = new Node(0, 0, 0);
        head.next = tail;
        tail.prev = head;
        
        Node[] edges = {head, tail};
        freqMap.put(freq, edges);
        
        return edges;
    }
    
    // insert the node to the head of the linked list of it's freq
    private void insertNodeToFreq(Node node) {
        Node[] list = getFreqList(node.freq);
        
        Node head = list[0];
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }
    
    private void removeNodeFromFreq(Node node) {
        if (!freqMap.containsKey(node.freq) ||
            node.prev == null) {
            return;
        }
        
        // only remove old node
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    private void refreshNodeFreq(Node node) {
        removeNodeFromFreq(node);
        node.freq++;
        insertNodeToFreq(node);
    }
    
    // return the key of the deleted Node
    private int removeLFUNodeFromFreq() {
        Node deletedNode = null;
        for (Map.Entry entry : freqMap.entrySet()) {
            Node[] edges = (Node[])entry.getValue();
            Node head = edges[0];
            Node tail = edges[1];
            if (head.next == tail) {
                continue;
            }
            
            deletedNode = tail.prev;
            tail.prev = tail.prev.prev;
            tail.prev.next = tail;
            break;
        }
        
        return deletedNode == null ? -1 : deletedNode.key;
    }
}
