/*

3 Sum

Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

 Notice

Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)

The solution set must not contain duplicate triplets.

Example
For example, given array S = {-1 0 1 2 -1 -4}, A solution set is:

(-1, 0, 1)
(-1, -1, 2)


解：
time O(n^2)
这道题需要利用2 Sum的方法。
将3 Sum拆解成2 Sum。
1.将数组排序
2.遍历排序后的数组
3.在当前数字num[i]后方查找和为-num[i]的两个数。

如此便将3 Sum变成了2 Sum。

接下来需要注意的是重复问题。
首先遍历数组时，没必要对同样的数字查找其后方。否则一定会得到重复解。
其次在计算2 Sum时，left和right也不应当重复计算相同的值。

*/

public class Solution {
    /**
     * @param numbers : Give an array numbers of n integer
     * @return : Find all unique triplets in the array which gives the sum of zero.
     */
    public ArrayList<ArrayList<Integer>> threeSum(int[] numbers) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        if (numbers == null || numbers.length == 0) {
            return ret;
        }
        
        Arrays.sort(numbers);
        
        for (int i = 0; i < numbers.length - 2 && numbers[i] <= 0; i++) {
            int left, right;
            
            if (i > 0 && numbers[i] == numbers[i - 1]) {
                continue;
            }
            
            left = i + 1;
            right = numbers.length - 1;
            
            while (left < right) {
                int sum = numbers[left] + numbers[right] + numbers[i];
                
                if (left > i + 1 && numbers[left] == numbers[left - 1]) {
                    left++;
                    continue;
                } else if (right < numbers.length - 1 
                        && numbers[right] == numbers[right + 1]) {
                    right--;
                    continue;
                }
                
                if (sum == 0) {
                    ArrayList<Integer> list = new ArrayList<Integer>();
                    list.add(numbers[i]);
                    list.add(numbers[left++]);
                    list.add(numbers[right--]);
                    ret.add(list);
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        
        return ret;
    }
}
