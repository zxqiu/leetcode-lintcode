/*

Partition Array

Given an array nums of integers and an int k, partition the array (i.e move the elements in "nums") such that:

All elements < k are moved to the left
All elements >= k are moved to the right
Return the partitioning index, i.e the first index i nums[i] >= k.

 Notice

You should do really partition in array nums instead of just counting the numbers of integers smaller than k.

If all elements in nums are smaller than k, then return nums.length


Example
If nums = [3,2,2,1] and k=2, a valid answer is 1.

Challenge 
Can you partition the array in-place and in O(n)?

解：
这道题考察的实际是quick sort或者quick select。
两个指针分别指向数组的头和尾。
1.如果头指针指向的值小于k，头指针向后移动一次。
2.如果尾指针指向的值大于等于k，尾指针向前移动一次。
3.否则，说明头指针的值大于或者等于k，而尾指针的值小于k，此时应当交换两个值。
4.重复1～4直到首尾指针相遇。

返回时由于不知道最后一次移动的是头指针还是尾指针，所以需要判断两个指针相遇时的值。
若小于k则返回下一个位置下标，否则返回相遇时的下标。


*/


public class Solution {
    /** 
     * param nums: The integer array you should partition
     * param k: As description
     * return: The index after partition
     */
    public int partitionArray(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            if (nums[left] < k) {
                left++;
            } else if (nums[right] >= k) {
                right--;
            } else {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
            }
        }
        
        return (nums[left] < k) ? left + 1 : left;
    }
}
