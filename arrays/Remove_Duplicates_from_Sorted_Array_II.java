/*
Follow up for "Remove Duplicates":
What if duplicates are allowed at most twice?

For example,
Given sorted array A = [1,1,1,2,2,3],

Your function should return length = 5, and A is now [1,1,2,2,3].
*/


/*
方法一： 判断当前数与上上一个数是否相等。
		注意上上一个数可能被刚保存的数覆盖掉，所以需要在保存之前将其存到别处备用。
*/

public class Solution {
    /**
     * @param A: a array of integers
     * @return : return an integer
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null) {
            return 0;
        } else if (nums.length < 3) {
            return nums.length;
        }
        
        int store = 2;
        int backup = nums[store];
        for (int i = 2; i < nums.length; i++) {
            int cmp = (i - 2 == store - 1) ? backup : nums[i - 2];
            if (nums[i] != cmp) {
                backup = nums[store];
                nums[store++] = nums[i];
            }
        }
        
        return store;
    }
}

/*
方法二： 判断当前数与刚存储的数，以及上一个存储的数是否相等。
		若与刚存储的数不等，则保存；
		若与当前数相等，但是与上一个存储的数不等，则保存；
		若与当前数相等，但是与上一个存储的数相等，则不保存。
*/
public class Solution {
    /**
     * @param A: a array of integers
     * @return : return an integer
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null) {
            return 0;
        } else if (nums.length < 3) {
            return nums.length;
        }
        
        int cnt = 1;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] == nums[cnt] && nums[i] != nums[cnt - 1]) {
                nums[++cnt] = nums[i];
            } else if (nums[i] != nums[cnt]) {
                nums[++cnt] = nums[i];
            }
        }
        
        return ++cnt;
    }
}