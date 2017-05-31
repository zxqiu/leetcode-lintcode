/*

Majority Number II

Given an array of integers, the majority number is the number that occurs more than 1/3 of the size of the array.

Find it.

Notice

There is only one majority number in the array.

Example

Given [1, 2, 1, 2, 1, 3, 3], return 1.
Challenge

O(n) time and O(1) extra space.


解：
利用Majority Number的方法，略微改进。
使用两个candidate和cnt分别保存两个数字。
当遇到一个跟里那个个candidate都不同的数字时，两个cnt都减一。
这意味着每次增加时总体cnt只增加了1，而减少时减了2。以此达到三分的效果。

最后两个candidate需要重新统计，超过1/3的那个为最后结果。


*/

public class Solution {
    /**
     * @param nums: A list of integers
     * @return: The majority number that occurs more than 1/3
     */
    public int majorityNumber(ArrayList<Integer> nums) {
        int candidate0, candidate1, cnt0, cnt1;
        
        candidate0 = candidate1 = cnt0 = cnt1 = 0;
        for (Integer i : nums) {
            if (cnt0 == 0) {
                candidate0 = i;
                cnt0++;
            } else if (candidate0 == i) {
                cnt0++;
            } else if (cnt1 == 0) {
                candidate1 = i;
                cnt1++;
            } else if (candidate1 == i) {
                cnt1++;
            } else {
                cnt0--;
                cnt1--;
            }
        }
        
        cnt0 = cnt1 = 0;
        for (Integer i : nums) {
            if (candidate0 == i) {
                cnt0++;
            } else if (candidate1 ==i) {
                cnt1++;
            }
        }
        
        return cnt0 > cnt1 ? candidate0 : candidate1;
    }
}
