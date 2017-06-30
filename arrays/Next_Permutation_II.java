/*

Next Permutation II

Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

Example
Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

1,2,3 → 1,3,2

3,2,1 → 1,2,3

1,1,5 → 1,5,1

Challenge 
The replacement must be in-place, do not allocate extra memory.


解：
跟Next Permutation思路一致。
注意在pivot右侧寻找的是大于nums[pivot]的数字。

*/


public class Solution {
    /**
     * @param nums: an array of integers
     * @return: return nothing (void), do not return anything, modify nums in-place instead
     */
    public void nextPermutation(int[] nums) {
        int pivot = nums.length - 2;
        int left, right;

        if (nums.length < 2) {
            return;
        }        
        
        for (; pivot >= 0; pivot--) {
            if (nums[pivot] < nums[pivot + 1]) {
                break;
            }
        }
        
        if (pivot >= 0) {
            int i = nums.length - 1;
            for (; i > pivot && nums[i] <= nums[pivot]; i--);
            swap(nums, i, pivot);
        }
        
        left = pivot + 1;
        right = nums.length - 1;
        while (left < right) {
            swap(nums, left++, right--);
        }
    }
    
    private void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}
