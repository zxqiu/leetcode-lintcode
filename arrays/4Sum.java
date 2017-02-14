/*

4Sum

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
先取一个数字，然后变成3Sum。


*/


public class Solution {
    /**
     * @param numbers : Give an array numbersbers of n integer
     * @param target : you need to find four elements that's sum of target
     * @return : Find all unique quadruplets in the array which gives the sum of
     *           zero.
     */
    public ArrayList<ArrayList<Integer>> fourSum(int[] numbers, int target) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        Arrays.sort(numbers);
        
        for (int i = 0; i < numbers.length; i++) {
            if (i > 0 && numbers[i] == numbers[i - 1]) {
                continue;
            }
            int targetTmp0 = target - numbers[i];
            
            for (int j = i + 1; j < numbers.length; j++) {
                if (j > i + 1 && numbers[j] == numbers[j - 1]) {
                    continue;
                }
                int left = j + 1;
                int right = numbers.length - 1;
                int targetTmp1 = targetTmp0 - numbers[j];
                
                while (left < right) {
                    if (left > j + 1 && numbers[left] == numbers[left - 1]) {
                        left++;
                        continue;
                    }
                    if (right < numbers.length - 1 && numbers[right] == numbers[right + 1]) {
                        right--;
                        continue;
                    }
                    if (numbers[left] + numbers[right] == targetTmp1) {
                        ArrayList<Integer> path = new ArrayList<>();
                        path.add(numbers[i]);
                        path.add(numbers[j]);
                        path.add(numbers[left++]);
                        path.add(numbers[right--]);
                        ret.add(path);
                    } else if (numbers[left] + numbers[right] < targetTmp1) {
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