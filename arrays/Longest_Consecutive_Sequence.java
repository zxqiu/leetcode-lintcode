/*

Longest Consecutive Sequence

Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

 Clarification
Your algorithm should run in O(n) complexity.

 Example
 
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.


解：
把所有数全部放进一个HashSet，分别判断每个数的前后所有可以连续的数字是否存在于这个Set中，若存在则将其从Set中删去。
这样便可找出最长序列。

这道题提供了一个思路，即判断的条件可以是单纯的某个数字，而非一定要是输入条件中的一个数。

*/



public class Solution {
    /**
     * @param nums: A list of integers
     * @return an integer
     */
    public int longestConsecutive(int[] num) {
        Set<Integer> set = new HashSet<Integer>();
        int max = 0;
        
        for (int i : num) {
            set.add(i);
        }
        
        for (int i : num) {
            int up = i + 1;
            int down = i - 1;
            
            while (set.contains(up)) {
                set.remove(up);
                up++;
            }
            
            while (set.contains(down)) {
                set.remove(down);
                down--;
            }
            max = Math.max(max, up - down - 1);
        }
        
        return max;
    }
}