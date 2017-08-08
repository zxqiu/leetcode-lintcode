/*

Wiggle Sort

Given an unsorted array nums, reorder it in-place such that

nums[0] <= nums[1] >= nums[2] <= nums[3]....
 Notice

Please complete the problem in-place.

Example
Given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].


解：
方法一：
先排序，然后从nums[3]开始，每个数字与它前面的数字交换即可。

这样做的原理是：
对于递增序列中的任意三个连续数，例如：
...a,1,2,3,b,c...
将2和1交换之后就可以得到：
...a,2,1,3,b,c...
这三个数一定大于其前面的任何数字，故满足2大于a且大于1。
计算完成后i跳到b，进行下一轮，计算序列3,b,c。

时间O(nlog(n))

*/

public class Solution {
    /**
     * @param nums a list of integer
     * @return void
     */
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        
        int left, right, len;
        
        Arrays.sort(nums);
        
        for (int i = 2; i < nums.length; i += 2) {
            int tmp = nums[i];
            nums[i] = nums[i - 1];
            nums[i - 1] = tmp;
        }
    }
}


/*

方法二：
对于任意一个数字，其实只需要考虑其与左边数字的关系即可。
1.对于i为奇数，应满足nums[i]>=nums[i-1]。
  如果nums[i]<nums[i-1]，直接交换这两个数。
  由于nums[i]<nums[i-1]<=nums[i-2]，故交换后满足条件：nums[i-1]>nums[i]<=nums[i-2]
2.对于i为偶数，应满足nums[i]<=nums[i-1]
  如果nums[i]>nums[i-1]，直接交换。
  由于nums[i]>nums[i-1]>=nums[i-2]，故交换后满足条件：nums[i-1]<nums[i]>=nums[i-2]

时间O(n)

*/

public class Solution {
    /**
     * @param nums a list of integer
     * @return void
     */
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        
        for (int i = 1; i < nums.length; i++) {
            if ((i % 2 == 0 && nums[i] > nums[i - 1]) ||
                (i % 2 == 1 && nums[i] < nums[i - 1])) {
                int tmp = nums[i];
                nums[i] = nums[i - 1];
                nums[i - 1] = tmp;
            }
        }
    }
}
