/*

Next Permutation

Given a list of integers, which denote a permutation.

Find the next permutation in ascending order.

 Notice

The list may contains duplicate integers.

Example
For [1,3,2,3], the next permutation is [1,3,3,2]

For [4,3,2,1], the next permutation is [1,2,3,4]


解：
与Previous Permutation思路一致。
设一个pivot，从后向前搜索找到第一个小于前一个数的数，与后面第一个大于pivot的数交换。
然后将pivot后面的所有数递增排列。


*/

public class Solution {
    /**
     * @param nums: an array of integers
     * @return: An array of integers that's next permuation
     */
    public int[] nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        
        int pivot = -1;
        
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                pivot = i;
                break;
            }
        }
        
        if (pivot > -1) {
            int i;
            for (i = nums.length - 1; nums[i] <= nums[pivot]; i--);
            swap(nums, pivot, i);
        }
        
        for (int i = pivot + 1; i <= (nums.length + pivot) / 2; i++) {
            swap(nums, i, nums.length - i + pivot);
        }
        
        return nums;
    }
    
    private void swap(int[] nums, int idx0, int idx1) {
        int tmp = nums[idx0];
        nums[idx0] = nums[idx1];
        nums[idx1] = tmp;
    }
}
