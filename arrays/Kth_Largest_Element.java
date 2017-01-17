/*
    QuickSelect
    time: O(n)
    space: O(1)

    类似于快速排序，找出某一个值的位置之后，判断第K大的值应当在其左边还是右边，然后对其左边或者右边的数组进行下一轮查找。
    pivotTh表示pivot在输入数组中是第几大。
*/

class Solution {
    /*
     * @param k : description of k
     * @param nums : array of nums
     * @return: description of return
     */

    public int kthLargestElement(int k, int[] nums) {
        return kthHelper(k, nums, 0, nums.length - 1);
    }

    private int kthHelper(int k, int[] nums, int start, int end) {
        int pivot = nums[end];
        int store = start;

        // find out the final position of pivot
        for (int i = start; i <= end; i++) {
            if (nums[i] <= pivot) {
                swap(nums, store++, i);
            }
        }

        // Note that store points to the index after pivot. Move it back.
        store--;

        int pivotTh = end - store + 1;
        if (pivotTh == k) {
            return nums[store];
        } else if (pivotTh > k) {
            return kthHelper(k, nums, store + 1, end);
        } else {
            return kthHelper(k - pivotTh, nums, start, store - 1);
        }
    }

    private void swap(int[] nums, int idxA, int idxB) {
        int tmp = nums[idxA];
        nums[idxA] = nums[idxB];
        nums[idxB] = tmp;
    }
};
