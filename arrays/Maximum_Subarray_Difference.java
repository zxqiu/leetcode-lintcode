/*

Maximum Subarray Difference

Given an array with integers.

Find two non-overlapping subarrays A and B, which |SUM(A) - SUM(B)| is the largest.

Return the largest difference.

 Notice

The subarray should contain at least one number

Example
For [1, 2, -3, 1], return 6.

Challenge 
O(n) time and O(n) space.


解：
这道题跟Maximum Subarray II的解法很类似。
先将数组分成左右两半，要求最大差，就是左边的最大和减去右边的最小和，或者左边的最小和减去右边的最大和，两者绝对值中大的那个。

左边的最大和和最小和可以用dynanmic programming从左向右计算。
maxLeft[i]表示前i个数中最大子数组和。
可以包含第i个数字，也可以不包含，两者取较大的那个。
不包含时有:
maxLeft[i] = maxLeft[i - 1]
包含时，一定是包含i-1的子数组再加上i位置的数字的和。
使用maxSum来计算当前连续子数组和：
maxSum += nums[i - 1]
maxLeft[i] = maxSum
故有：
maxLeft[i] = max(maxLeft[i - 1], maxSum)

计算后，若maxSum为负数则清零。在此条件下，只有连续正数累加才能获得更大的和，否则将会取之前的最大值。
比如输入数组为1，2，-3，1，则在此条件下maxSum的值为：1，3，0，1，maxLeft为：1，3，3，4
若输入为-2，-1，-4，-3，则maxSum为-2，-1，-4，-3，maxLeft为：-2，-1，-1，-1

minLeft[i]表示前i个数字中最小子数组和。
同样可以博阿含第i个数字，也可以不包含，两者取较小的那个。
不包含时有：
minLeft[i] = minLeft[i - 1]
包含时，使用minSum来计算当前连续子数组和：
minSum += nums[i - 1]
minLeft[i] = minSum
故有：
minLeft[i] = min(minLeft[i - 1], minSum)

计算后，若minSum为正数则清零。在此条件下，只有连续负数累加才能获得更小的和，否则将会取得之前的最小值。
比如输入1，2，-3，-1，则minSum为1，2，-3，-4，minLeft为1，1，-3，-4
输入2，1，3，4，则minSum为2，1，3，4，minLeft为2，1，1，1

右边的最大和和最小和与左边计算方法一致，只不过换成从右向左计算。

*/

public class Solution {
    /**
     * @param nums: A list of integers
     * @return: An integer indicate the value of maximum difference between two
     * 		Subarrays
     */
    public int maxDiffSubArrays(int[] nums) {
        int[] maxLeft = new int[nums.length + 1];
        int[] minLeft = new int[nums.length + 1];
        int[] maxRight = new int[nums.length + 2];
        int[] minRight = new int[nums.length + 2];
        int maxSum = 0, minSum = 0, diff = Integer.MIN_VALUE;
        
        /* left to right */
        maxLeft[0] = Integer.MIN_VALUE;
        minLeft[0] = Integer.MAX_VALUE;
        for (int i = 1; i <= nums.length; i++) {
            /* max left sum */
            maxSum += nums[i - 1];
            maxLeft[i] = Math.max(maxLeft[i - 1], maxSum);
            maxSum = Math.max(maxSum, 0);
            
            /* min left sum */
            minSum += nums[i - 1];
            minLeft[i] = Math.min(minLeft[i - 1], minSum);
            minSum = Math.min(minSum, 0);
        }
        
        /* right to left */
        maxSum = minSum = 0;
        maxRight[nums.length + 1] = Integer.MIN_VALUE;
        minRight[nums.length + 1] = Integer.MAX_VALUE;
        for (int i = nums.length; i > 0; i--) {
            /* max right sum */
            maxSum += nums[i - 1];
            maxRight[i] = Math.max(maxRight[i + 1], maxSum);
            maxSum = Math.max(maxSum, 0);
            
            /* min right sum */
            minSum += nums[i - 1];
            minRight[i] = Math.min(minRight[i + 1], minSum);
            minSum = Math.min(minSum, 0);
        }
        
        /* calculate difference */
        for (int i = 1; i < nums.length; i++) {
            diff = Math.max(diff,
                   Math.max(Math.abs(maxLeft[i] - minRight[i + 1]),
                            Math.abs(minLeft[i] - maxRight[i + 1])));
        }
        
        return diff;
    }
}


