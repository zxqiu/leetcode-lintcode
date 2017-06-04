/*

4 Sum

Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target?

Find all unique quadruplets in the array which gives the sum of target.

 Notice

Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
The solution set must not contain duplicate quadruplets.

Example
Given array S = {1 0 -1 0 -2 2}, and target = 0. A solution set is:

(-1, 0, 0, 1)
(-2, -1, 1, 2)
(-2, 0, 0, 2)


解：
在3 Sum基础上再加入一层循环。


*/

public class Solution {
    /**
     * @param numbers : Give an array numbersbers of n integer
     * @param target : you need to find four elements that's sum of target
     * @return : Find all unique quadruplets in the array which gives the sum of
     *            zero.
     */
    public ArrayList<ArrayList<Integer>> fourSum(int[] numbers, int target) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        int a, b, c, d;
        
        if (numbers == null || numbers.length == 0) {
            return ret;
        }
        
        Arrays.sort(numbers);
        a = Integer.MAX_VALUE;
        
        for (int i = 0; i < numbers.length - 3; i++) {
            if (numbers[i] == a) {
                continue;
            }
            
            a = numbers[i];
            b = Integer.MAX_VALUE;
            
            for (int j = i + 1; j < numbers.length - 2; j++) {
                if (numbers[j] == b) {
                    continue;
                }
                b = numbers[j];
                
                int left, right;
                left = j + 1;
                right = numbers.length - 1;
                
                while (left < right) {
                    if (left > j + 1 && numbers[left] == numbers[left - 1]) {
                        left++;
                        continue;
                    } else if (right < numbers.length - 1 
                                && numbers[right] == numbers[right + 1]) {
                        right--;
                        continue;
                    }
                    
                    c = numbers[left];
                    d = numbers[right];
                    
                    if (a + b + c + d == target) {
                        ArrayList<Integer> list = new ArrayList<Integer>();
                        list.add(a);
                        list.add(b);
                        list.add(c);
                        list.add(d);
                        ret.add(list);
                        left++;
                        right--;
                    } else if (a + b + c + d < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        
        return ret;
    }
}
