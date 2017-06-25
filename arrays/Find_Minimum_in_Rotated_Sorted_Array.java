/*

Find Minimum in Rotated Sorted Array 

Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

 Notice

You may assume no duplicate exists in the array.

Have you met this question in a real interview? Yes
Example
Given [4, 5, 6, 7, 0, 1, 2] return 0


解：
二分法
任何时候，只要left的值小于right的值，说明left一定指向最小值。
否则：
  1.如果mid的值大与left和right的值，说明mid在最小值左侧。
  2.入股mid的值小于left和right的值，说明mid在最小值右侧。

*/

public class Solution {
    /**
     * @param nums: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] nums) {
        if (nums[0] <= nums[nums.length - 1]) {
            return nums[0];
        }
        
        int left, right;
        left = 0;
        right = nums.length - 1;
        
        while (left < right - 1) {
            int mid = (left + right) / 2;
            
            if (nums[left] < nums[right]) {
                return nums[left];
            } else if (nums[mid] > nums[left] && nums[mid] > nums[right]) {
                left = mid;
            } else if (nums[mid] < nums[left] && nums[mid] < nums[right]) {
                right = mid;
            }
        }
        
        return Math.min(nums[left], nums[right]);
    }
}
