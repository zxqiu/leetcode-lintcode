/*

Find the Missing Number




Given an array contains N numbers of 0 .. N, find which number doesn't exist in the array.


Example

Given N = 3 and the array [0, 1, 3], return 2.
Challenge

Do it in-place with O(1) extra memory and O(n) time.


解：
与First Missing Positive几乎一致。
如果数字i满足0<=i<nums.length，则将i填如nums[i]。
之后遍历整个数组，找到第一个满足nums[i]!=i的i并返回。
如果没有找到，说明缺少的数字是最后一个，也就是nums.length。

*/

public class Solution {
    /**    
     * @param nums: an array of integers
     * @return: an integer
     */
    public int findMissing(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int tmp = nums[i];
            nums[i] = -1;
            while (tmp >= 0 && tmp < nums.length && tmp != nums[tmp]) {
                int tmp0 = nums[tmp];
                nums[tmp] = tmp;
                tmp = tmp0;
            }
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        
        return nums.length;
    }
}
