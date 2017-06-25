/*

Find Minimum in Rotated Sorted Array II

Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

 Notice

The array may contain duplicates.

Example
Given [4,4,5,6,7,0,1,2] return 0.


解：
二分法
与Find Minimum in Rotated Sorted Array思路一致。
需要额外注意left和right的值相等的情况：
1.只有当left的值小于right时，left的值才是最小值。
2.当left和right的值相等时，left右移，直到不相等为止。
3.当mid的值大于等于left和right的值时，说明mid在最小值左边，left移动到mid。实际上由于left和right的值不相等，mid的值不可能等于right。
4.当mid的值小于等于left和right的值时，说明mid在最小值右边，right移动到mid。同理由于left和right的值不相等，mid的值不可能等于left。

*/


public class Solution {
    /**
     * @param nums: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] nums) {
        int left, right;
        left = 0;
        right = nums.length - 1;
        
        while (left < right - 1) {
            int mid = (left + right) / 2;
            
            if (nums[left] == nums[right]) {
                left++;
            } else if (nums[left] < nums[right]) {
                return nums[left];
            } else if (nums[mid] >= nums[left] && nums[mid] > nums[right]) {
                left = mid;
            } else if (nums[mid] < nums[left] && nums[mid] <= nums[right]) {
                right = mid;
            }
        }
        
        return Math.min(nums[left], nums[right]);
    }
}
