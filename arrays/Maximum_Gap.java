/*

Maximum Gap



Given an unsorted array, find the maximum difference between the successive elements in its sorted form.

Return 0 if the array contains less than 2 elements.

Notice
You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.

Example
Given [1, 9, 2, 5], the sorted form of it is [1, 2, 5, 9], the maximum gap is between 5 and 9 = 4. 



解：
使用线性排序算法(计数排序，桶排序，基数排序)
在此使用桶排序。

将大小从i到j的n个数放入若干个桶中，保证每个桶的大小小于最大gap。
然后从每个桶中找出最大和最小值，比较相邻的非空桶的最小值和最大值从而得出最大gap。

关键点在于桶的大小一定要小于最大gap，从而保证最大gap一定出在相邻的桶之间。
可以计算出，最大gap一定会大于等于：(j - i) / n
那么，每个桶的大小为： len = ceiling((j - i) / n)
桶的数量为： num = ceiling((j - i) / len)
由于最后的结果一定处在相邻的桶之间而非某一个桶内，所以只需要保存每个桶内的最大值和最小值即可。

算法流程为：
1. 找出数组中的最小值和最大值，即上面提到的i和j
2. 计算桶的小大和数量
3. 初始化桶
4. 根据数组内容填充桶
5. 计算任意相邻两个非空桶之间的最大gap，得出结果

*/


class Solution {
    /**
     * @param nums: an array of integers
     * @return: the maximum difference
     */
    
    // bucket sort
    public int maximumGap(int[] nums) {
        int max, min;
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        
        for (int i : nums) {
            max = Math.max(max, i);
            min = Math.min(min, i);
        }
        
        int bucket_len = (max - min) / nums.length + 1;
        int bucket_num = (max - min) / bucket_len + 1;
        int[][] bucket = new int[bucket_num][2];
        // init the buckets
        for (int i = 0; i < bucket_num; i++) {
            bucket[i][0] = Integer.MIN_VALUE; // store max value in the bucket
            bucket[i][1] = Integer.MAX_VALUE; // store min value in the bucket
        }
        
        // fill the buckets
        for (int i = 0; i < nums.length; i++) {
            int idx = (nums[i] - min) / bucket_len;
            
            bucket[idx][0] = Math.max(bucket[idx][0], nums[i]);
            bucket[idx][1] = Math.min(bucket[idx][1], nums[i]);
        }
        
        // find max gap in each consecutive none-empty buckets
        int gap = 0;
        int prev_bucket = -1;
        for (int i = 0; i < bucket_num; i++) {
            if (bucket[i][0] < 0) {
                continue;
            }
            if (prev_bucket >= 0) {
                gap = Math.max(gap, bucket[i][1] - bucket[prev_bucket][0]);
            }
            prev_bucket = i;
        }
        
        return gap;
    }
}
