/*

Two Sum II (leetcode version)

Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
You may assume that each input would have exactly one solution.

Example:

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2


解：
用两个指针分别指向数组的头和尾，算出其和。
由于数组是有序的，所以如果和大于target，就左移尾指针；如果和小于target，就右移头指针。
直到找到target。

*/


public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int[] ret = new int[2];
        ret[0] = -1;
        ret[1] = -1;
        
        if (numbers == null || numbers.length == 0) {
            return ret;
        }
        
        int left, right;
        left = 0;
        right = numbers.length - 1;
        
        while (left < right) {
            int tmp = numbers[left] + numbers[right];
            if (tmp == target) {
                ret[0] = ++left;
                ret[1] = ++right;
                break;
            } else if (tmp > target) {
                right--;
            } else {
                left++;
            }
        }
        
        return ret;
    }
}