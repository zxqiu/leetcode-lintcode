/*

Insert Delete GetRandom O(1)

Design a data structure that supports all following operations in average O(1) time.

insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.


Example
// Init an empty set.
RandomizedSet randomSet = new RandomizedSet();

// Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomSet.insert(1);

// Returns false as 2 does not exist in the set.
randomSet.remove(2);

// Inserts 2 to the set, returns true. Set now contains [1,2].
randomSet.insert(2);

// getRandom should return either 1 or 2 randomly.
randomSet.getRandom();

// Removes 1 from the set, returns true. Set now contains [2].
randomSet.remove(1);

// 2 was already in the set, so return false.
randomSet.insert(2);

// Since 2 is the only number in the set, getRandom always return 2.
randomSet.getRandom();


解：
HashMap List

首先需要明确，List的remove操作平均时间复杂度为O(n)，这是由于需要移动元素。
但是如果删除最后一个元素，不需要移动别的元素，故时间复杂度为O(1)。

使用List保存数字，然后用HashMap保存每个数字的序号。
1.插入时将数字插入list，然后把序号存入HashMap。
2.删除时先将数字与list末尾的数字交换并修改HashMap，然后把该数字从list和map中都删除。
3.取随机值时生成一个随机序号，从list中取出即可。

*/



public class RandomizedSet {
    Random ran;
    List<Integer> list;
    Map<Integer, Integer> map;
    
    public RandomizedSet() {
        ran = new Random();
        list = new ArrayList<Integer>();
        map = new HashMap<Integer, Integer>();
    }
    
    // Inserts a value to the set
    // Returns true if the set did not already contain the specified element or false
    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        
        list.add(val);
        map.put(val, list.size() - 1);
        
        return true;
    }
    
    // Removes a value from the set
    // Return true if the set contained the specified element or false
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        
        int idx = map.get(val);
        int tail = list.get(list.size() - 1);
        
        list.set(idx, tail);
        map.put(tail, idx);
        list.remove(list.size() - 1);
        map.remove(val);
        
        return true;
    }
    
    // Get a random element from the set
    public int getRandom() {
        if (list.size() == 0) {
            return 0;
        }
        
        int idx = ran.nextInt(list.size());
        return list.get(idx);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param = obj.insert(val);
 * boolean param = obj.remove(val);
 * int param = obj.getRandom();
 */
