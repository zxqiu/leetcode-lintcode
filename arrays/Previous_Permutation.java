/*

Previous Permutation

Given a list of integers, which denote a permutation.

Find the previous permutation in ascending order.

 Notice

The list may contains duplicate integers.

Example
For [1,3,2,3], the previous permutation is [1,2,3,3]
For [1,2,3,4], the previous permutation is [4,3,2,1]


解：
先考虑一般情况，如果一个数组不是第一个permutation，
那么首先，其末尾一定存在一个或多个数字按照递增顺序排列，
其次，在这个递增序列之前一定存在一个数大于其后方的数字。假设这个数字为pivot。
比如输入1，5，2，3，4，pivot为5，其后数字为递增序列。

pivot一定为第一个大于其后数字的数字，计算上一个permutation时不需要考虑pivot左边的数字。

对于这样的数组，计算其上一个permutation时，
首先从后向前找到第一个小于pivot指向数字的数，交换该数字与pivot指向数字。由于是第一个小于pivot指向数的数，交换后pivot位置后面的数组依然递增。
比如1，5，2，3，4变成1，4，2，3，5。
然后将pivot（交换后pivot位置并不改变）后面的数字由递增改成递减。
上面数组变成1，4，5，3，2
这样就得到了previous permutation。

如果考虑第一个permutation，比如1，2，3，4，5，只需要直接将数组倒序排列。


*/

public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A list of integers that's previous permuation
     */
    public ArrayList<Integer> previousPermuation(ArrayList<Integer> nums) {
		int pivot = -1;
		
		/* find pivot */
		for (int i = nums.size() - 2; i >= 0; i--) {
		    if (nums.get(i) > nums.get(i + 1)) {
		        pivot = i;
		        break;
		    }
		}
		
		/* correct pivot value */
		if (pivot > -1) {
		    int i = nums.size() - 1;
		    for (; i > pivot && nums.get(i) >= nums.get(pivot); i--);
            swap(nums, pivot, i);
		}
		
		/* sort nums after pivot */
		for (int i = pivot + 1; i <= (nums.size() + pivot) / 2; i++) {
		    swap(nums, i, nums.size() - i + pivot);
		}
		
		return nums;
    }
    
    private void swap(ArrayList<Integer> nums, int idx0, int idx1) {
        int tmp = nums.get(idx0);
        nums.set(idx0, nums.get(idx1));
        nums.set(idx1, tmp);
    }
}

