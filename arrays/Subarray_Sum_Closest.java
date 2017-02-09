/*

Subarray Sum Closest

Given an integer array, find a subarray with sum closest to zero. Return the indexes of the first number and last number.


 Example

Given [-3, 1, 1, -3, 5], return [0, 2], [1, 3], [1, 1], [2, 2] or [0, 4].

 Challenge

O(nlogn) time


解：

方法一：
最基本的思路，暴力破解。
从0开始遍历数组，到达i时，反向遍历i到0，依次算出nums[i]和其之间所有数的和，找到最接近零的那个存起来。
继续下一轮直到遍历完整个数组。

时间复杂度O(n^2)
超时所以不能Accept

*/

public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number 
     *          and the index of the last number
     */
    public int[] subarraySumClosest(int[] nums) {
        int[] min = new int[2];
        int minVal = Integer.MAX_VALUE;
        
        for (int i = 0; i < nums.length; i++) {
            int tmp = 0;
            for (int j = i; j >= 0; j--) {
                tmp += nums[j];
                if (Math.abs(tmp - 0) < minVal) {
                    minVal = Math.abs(tmp - 0);
                    min[0] = j;
                    min[1] = i;
                }
            }
        }
        
        return min;
    }
}


/*

方法二：
基本思路是：
先把从0到i的所有数加起来的和，以及对应的序号i组合起来作为一个Node存入一个数组，i为从0到nums.length - 1。
然后对该数组按照和的大小排序。那么当该数组中的一项减去另一项时，相当于获得了从一项的序号加一到另一个数组的序号为止的所有数的和。
比如用[0~6]的和减去[0~3]的和，得到的是[4~6]的和。
因为按照和的大小进行了排序，所以相邻Node和的差的最小值就是输入数组子序列的最小和，即我们要求的目标。然后只要按照上边计算序号的办法返回对应最小值的序号即可。

在实现时，利用TreeMap自排序的特性直接在计算和时进行排序。和作为TreeMap的key，序号作为value，即TreeMap将按照和来排序。
需要注意的是，由于TreeMap不接受重复的key，所以在求和阶段如果发现重复的值，那就说明找到了一个和为0的子序列，应当直接返回该子序列。

*/



public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number 
     *          and the index of the last number
     */
    public int[] subarraySumClosest(int[] nums) {
        TreeMap<Integer, Integer> sums = new TreeMap<>();
        int sum = 0;
        
        for (int i = 1; i <= nums.length; i++) {
            sum += nums[i - 1];
            if (sums.containsKey(sum)) {
                return new int[]{sums.get(sum) + 1, i - 1};
            }
            sums.put(sum, i - 1);
        }
        
        
        Map.Entry<Integer, Integer> lastEntry = null;
        int[] min = new int[2];
        int minVal = 0;
        for (Map.Entry<Integer, Integer> entry : sums.entrySet()) {
            if (lastEntry == null) {
                lastEntry = entry;
                minVal = Math.abs(entry.getKey());
                min[0] = 0;
                min[1] = entry.getValue();
                continue;
            }
            
            if (entry.getKey() - lastEntry.getKey() < minVal) {
                minVal = entry.getKey() - lastEntry.getKey();
                min[0] = lastEntry.getValue();
                min[1] = entry.getValue();
                Arrays.sort(min);
                min[0]++;
            }
            lastEntry = entry;
        }
        
        return min;
    }
}
