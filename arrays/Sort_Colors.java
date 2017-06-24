/*

Sort Colors



Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
Notice

You are not suppose to use the library's sort function for this problem.
You should do it in-place (sort numbers in the original array).



 Example

Given [1, 0, 1, 2], sort it in-place to [0, 1, 1, 2].


 Challenge

A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.

Could you come up with an one-pass algorithm using only constant space?

解：
two pointers

使用一个left指针指向0保存的位置，right指针指向2保存的位置。
mid指针从左向右遍历数组，遇到0就存到left位置，并右移left，遇到2就存到right位置，并左移right。
若mid遇到1或者小于left，则自己向右移动。

*/


class Solution {
    /**
     * @param nums: A list of integer which is 0, 1 or 2 
     * @return: nothing
     */
    public void sortColors(int[] nums) {
        int left, right, mid;
        
        left = 0;
        mid = 0;
        right = nums.length - 1;
        
        while (mid <= right) {
            if (nums[mid] == 0) {
                swap(nums, left++, mid);
            } else if (nums[mid] == 2) {
                swap(nums, right--, mid);
            }
            
            if (nums[mid] == 1 || mid < left) {
                mid++;
            }
        }
    }
    
    private void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}
