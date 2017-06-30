/*

Maximum Product Subarray

Find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example
For example, given the array [2,3,-2,4], the contiguous subarray [2,3] has the largest product = 6.


解：
对于每个数来说，由于可能为正也可能为负，所以应当分别与之前的最大正乘积和最小负乘积相乘，取其中大的与全局最大值比较。
由此，需要同时保存当前的最大正乘积和最小负乘积。

如果遇到一个正数，应当与之前的正乘积相乘获得新的最大正乘积。同时该数与之前的最小负乘积相乘可以获得新的最小负乘积。
如果遇到一个负数，应当与之前的最小负乘积相乘获得当前的最大正乘积。同时该数与之前的最大正乘积相乘获得新的最小负乘积。

如果遇到的第一个数为正，那么最小负乘积为正数，应当忽略并令其为1。
如果遇到的第一个数为负，那么最大正乘积为负数，应当忽略并令其为1。

*/


public class Solution {
    /**
     * @param nums: an array of integers
     * @return: an integer
     */
    public int maxProduct(int[] nums) {
        int max, pos, neg;
        
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        max = nums[0];
        pos = 1;
        neg = 1;
        
        for (int i : nums) {
            int tmpPos = Math.max(pos * i, neg * i);
            int tmpNeg = Math.min(pos * i, neg * i);

            if (tmpPos <= 0) {
                pos = 1;
            } else {
                pos = tmpPos;
                max = Math.max(max, pos);
            }
            
            if (tmpNeg >= 0) {
                neg = 1;
            } else {
                neg = tmpNeg;
            }
        }
        
        return max;
    }
}
