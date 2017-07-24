/*

Minimum Size Subarray Sum


Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return -1 instead.

Example
Given the array [2,3,1,2,4,3] and s = 7, the subarray [4,3] has the minimal length under the problem constraint.

Challenge 
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).


解：
根Continuous Subarray Sum思路完全一致。
参照： https://github.com/zxqiu/leetcode-lintcode/blob/master/arrays/Continuous_Subarray_Sum.java

start从0开始，并从start开始累加，如果sum小于0，则sum清零，并且start改成i+1。
如果sum大于等于s，将start右移，并用sum减去每一个nums[start]，直到sum小于s为止。在这过程中记录sum大于等于s的最小子串长度。

*/

public class Solution {
    /**
     * @param nums: an array of integers
     * @param s: an integer
     * @return: an integer representing the minimum size of subarray
     */
    public int minimumSize(int[] nums, int s) {
        int start = 0;
        int sum = 0;
        int ret = nums.length + 1;
        
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            
            while (start <= i && sum >= s) {
                ret = Math.min(ret, i - start + 1);
                sum -= nums[start++];
            }
            
            if (sum < 0) {
                sum = 0;
                start = i + 1;
            }
        }
        
        return (ret > nums.length) ? -1 : ret;
    }
}
