/*

Majority Number

Given an array of integers, the majority number is the number that occurs more than half of the size of the array. Find it.

 Notice

You may assume that the array is non-empty and the majority number always exist in the array.

Example
Given [1, 1, 1, 1, 2, 2, 2], return 1

Challenge 
O(n) time and O(1) extra space


解：
用candidate来保存数字，并用cnt来统计该数字的数量。
当遇到一个跟candidate相同的数字时，cnt加一，否则减一。
当cnt等于0时，更新candidate为当前的数字，同时cnt不变。
这样做相当于如果某一个数出现了3次，另外一个数必须出现4次才可以变更candidate。

输出最后一个candidate

比如输入为：	1，1，1，2，2，2，2，1，1
cnt：		1，2，3，2，1，0，0，0，1
candidate：	1，1，1，1，1，1，2，1，1

输入：		1，1，1，1，2，2，2
cnt：		1，2，3，4，3，2，1
candidate：	1，1，1，1，1，1，1

*/

public class Solution {
    /**
     * @param nums: a list of integers
     * @return: find a  majority number
     */
    public int majorityNumber(ArrayList<Integer> nums) {
        int candidate, cnt = 0;
        candidate = nums.get(0);
        
        for (int i = 0; i < nums.size(); i++) {
            if (candidate == nums.get(i)) {
                cnt++;
            } else if (cnt == 0) {
                candidate = nums.get(i);
                cnt++;
            } else {
                cnt--;
            }
        }
        
        return candidate;
    }
}
