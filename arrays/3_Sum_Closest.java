/*

3 Sum Closest

Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers.

 Notice

You may assume that each input would have exactly one solution.

Example
For example, given array S = [-1 2 1 -4], and target = 1. The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

Challenge 
O(n^2) time, O(1) extra space


解：
这道题思路与3 Sum完全一致。
稍微不同的是需要记录并返回与target最接近的一个和就行了。

*/

public class Solution {
    /**
     * @param numbers: Give an array numbers of n integer
     * @param target : An integer
     * @return : return the sum of the three integers, the sum closest target.
     */
    public int threeSumClosest(int[] numbers, int target) {
        if (numbers == null || numbers.length == 0) {
            return 0;
        }
        
        int ret;
        
        Arrays.sort(numbers);
        ret = numbers[0] + numbers[1] + numbers[2];
        
        for (int i = 0; i < numbers.length - 2; i++) {
            if (i > 0 && numbers[i] == numbers[i - 1]) {
                continue;
            }
            
            int left, right, sum;
            
            left = i + 1;
            right = numbers.length - 1;
            
            while (left < right) {
                if (left > i + 1 && numbers[left] == numbers[left - 1]) {
                    left++;
                    continue;
                } else if (right < numbers.length - 1 
                            && numbers[right] == numbers[right + 1]) {
                    right--;
                    continue;
                }
                
                sum = numbers[i] + numbers[left] + numbers[right];
                
                if (Math.abs(target - sum) < Math.abs(target - ret)) {
                    ret = sum;
                }
                
                if (sum == target) {
                    return ret;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        
        return ret;
    }
}

