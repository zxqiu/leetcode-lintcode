/*

Sliding Window Median


Given an array of n integer, and a moving window(size k), move the window at each iteration from the start of the array, find the median of the element inside the window at each moving. (If there are even numbers in the array, return the N/2-th number after sorting the element in the window. )

Example
For array [1,2,7,8,5], moving window size k = 3. return [2,7,7]

At first the window is at the start of the array like this

[ | 1,2,7 | ,8,5] , return the median 2;

then the window move one step forward.

[1, | 2,7,8 | ,5], return the median 7;

then the window move one step forward again.

[1,2, | 7,8,5 | ], return the median 7;

Challenge 
O(nlog(n)) time


解：

方法一：
要求median，可以使用max，min heap的方法。
把大于median的所有数放进一个min heap，所有小于等于median的数放进一个max heap。
然后平衡两个heap中数字的数量，保证max heap的第一个值为median。
在做平衡时:
1，考虑两个heap中数字的总量是否大于k。如果大于则删掉nums[i-k]。
2，当两个heap中数字总量为奇数时，max heap应当比min heap多一个。为偶数时，两者数量应当相等。
3，当max heap中数字较多时，应当转移第一个元素到min heap；当min heap中数字较多时，应当转移第一个元素到max heap。

把所有数字按照上面规则依次加入两个heap，当两个heap中数字的总数量等于k时，将max heap的第一个值放进返回数组即可。

这个算法不能满足O(nlog(n))的要求。
因为heap插入时间复杂度为O(log(n))，删除时间复杂度为O(n)。总时间复杂度为O(n^2)

*/

public class Solution {
    /**
     * @param nums: A list of integers.
     * @return: The median of the element inside the window at each moving.
     */
    public ArrayList<Integer> medianSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) {
            return new ArrayList<Integer>();
        }
        
        ArrayList<Integer> ret = new ArrayList<Integer>();
        Queue<Integer> min = new PriorityQueue<Integer>(1, new Comparator<Integer>() {
                public int compare(Integer a, Integer b) {
                    return a - b;
                }
            });
        Queue<Integer> max = new PriorityQueue<Integer>(1, new Comparator<Integer>() {
                public int compare(Integer a, Integer b) {
                    return b - a;
                }
            });
        
        for (int i = 0; i < nums.length; i++) {
            if (max.size() == 0 || nums[i] <= max.peek()) {
                max.offer(nums[i]);
            } else {
                min.offer(nums[i]);
            }
            
            balance(min, max, k, nums, i - k);
            if (i >= k - 1) {
                ret.add(max.peek());
            }
        }
        
        return ret;
    }
    
    private void balance(Queue<Integer> min, Queue<Integer> max, int k, int[] nums, int i) {
        int isOdd;
        
        if (min.size() + max.size() > k) {
            if (nums[i] > max.peek()) {
                min.remove(nums[i]);
            } else {
                max.remove(nums[i]);
            }
        }
        
        isOdd = (min.size() + max.size()) % 2;
        
        if (max.size() == min.size() + isOdd) {
            return;
        }
        
        while (max.size() > min.size() + isOdd) {
            min.offer(max.poll());
        }
        
        while (max.size() < min.size() + isOdd) {
            max.offer(min.poll());
        }
    }
}


/*

为了解决删除时间复杂度高的问题，可以使用TreeMap代替。
TreeMap remove时间复杂读为O(log(n))，故总时间复杂度为O(nlog(n))。
每个Entry的key为nums中的数字，value为有几个这样的数。
还需要两个计数器分别保存两个map中元素的数量。
其他思路方法都不变。

*/



public class Solution {
    /**
     * @param nums: A list of integers.
     * @return: The median of the element inside the window at each moving.
     */
    
    int minCnt = 0;
    int maxCnt = 0;
    public ArrayList<Integer> medianSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) {
            return new ArrayList<Integer>();
        }
        
        ArrayList<Integer> ret = new ArrayList<Integer>();
        TreeMap<Integer, Integer> min = new TreeMap<Integer, Integer>(new Comparator<Integer>() {
                public int compare(Integer a, Integer b) {
                    return a - b;
                }
            });
        TreeMap<Integer, Integer> max = new TreeMap<Integer, Integer>(new Comparator<Integer>() {
                public int compare(Integer a, Integer b) {
                    return b - a;
                }
            });
        
        for (int i = 0; i < nums.length; i++) {
            if (max.size() == 0 || nums[i] <= max.firstKey()) {
                add(max, nums[i]);
                maxCnt++;
            } else {
                add(min, nums[i]);
                minCnt++;
            }
            
            balance(min, max, k, nums, i);
            if (i >= k - 1) {
                ret.add(max.firstKey());
            }
        }
        
        return ret;
    }
    
    private void balance(TreeMap<Integer, Integer> min, TreeMap<Integer, Integer> max, int k, int[] nums, int i) {
        int isOdd;
        
        if (minCnt + maxCnt > k) {
            if (nums[i - k] > max.firstKey()) {
                remove(min, nums[i - k]);
                minCnt--;
            } else {
                remove(max, nums[i - k]);
                maxCnt--;
            }
        }
        
        isOdd = (minCnt + maxCnt) % 2;
        
        if (maxCnt == minCnt + isOdd) {
            return;
        }
        
        while (maxCnt > minCnt + isOdd) {
            add(min, max.firstKey());
            minCnt++;
            remove(max, max.firstKey());
            maxCnt--;
        }
        
        while (maxCnt < minCnt + isOdd) {
            add(max, min.firstKey());
            maxCnt++;
            remove(min, min.firstKey());
            minCnt--;
        }
    }
    
    private void add(TreeMap<Integer, Integer> map, int key) {
        if (!map.containsKey(key)) {
            map.put(key, 0);
        }
        map.put(key, map.get(key) + 1);
    }
    
    private void remove(TreeMap<Integer, Integer> map, int key) {
        map.put(key, map.get(key) - 1);
        
        if (map.get(key) == 0) {
            map.remove(key);
        }
    }
}

