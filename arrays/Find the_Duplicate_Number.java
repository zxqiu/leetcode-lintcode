/*

Find the Duplicate Number


Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

 Notice

You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n^2).
There is only one duplicate number in the array, but it could be repeated more than once.
Have you met this question in a real interview? Yes
Example
Given nums = [5,5,4,3,2,1] return 5
Given nums = [5,4,4,3,2,1] return 4


解：
遍历数组，把每个数字i放进位置i-1。
如果遇到两个数字需要放进同一个位置，那么说明该数字重复。

具体来说，遍历到位置i时：
1.如果nums[i]不等于i+1，说明该数字位置不对，将其与nums[nums[i]]交换。并且下一次继续访问当前位置。
2.如果交换时发现对方与自己的值一样，即nums[nums[i]] == nums[i]，则该数字重复。

*/


public class Solution {
    /**
     * @param nums an array containing n + 1 integers which is between 1 and n
     * @return the duplicate one
     */
    public int findDuplicate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                int tmp = nums[nums[i]];
                
                if (tmp == nums[i]) {
                    return tmp;
                }
                
                nums[nums[i]] = nums[i];
                nums[i] = tmp;
                i--;
            }
        }
        
        return -1;
    }
}
