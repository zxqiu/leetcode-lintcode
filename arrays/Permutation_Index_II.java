/*

Permutation Index II

Given a permutation which may contain repeated numbers, find its index in all the permutations of these numbers, which are ordered in lexicographical order. The index begins at 1.

Example
Given the permutation [1, 4, 2, 2], return 3.


解：
跟Permutation Index思路一致。
需要注意的是由于存在重复，所以每个数的结果应当除以包括该数在内每个重复数字数量的阶乘的乘积。
由于1！=1，所以可以除以每个数字数量阶乘的乘积。其实可以用一个数字每次乘以每次统计的当前数字的数量，就是想要的乘积。

比如对于4，1，1，2，2，
计算到4时的计算公式为：(cnt * 4!) / (1! * 2! * 2!), cnt = 4

用HashMap来统计有多少重复的数字。

*/

public class Solution {
    /**
     * @param A an integer array
     * @return a long integer
     */
    public long permutationIndexII(int[] A) {
        long ret = 1;
        long fact = 1;
        long mulFact = 1;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        if (A == null || A.length == 0) {
            return 0;
        }
        
        for (int i = A.length - 1; i >= 0; i--) {
            int cnt = 0;
            
            if (!map.containsKey(A[i])) {
                map.put(A[i], 0);
            }
            map.put(A[i], map.get(A[i]) + 1);
            
            mulFact *= map.get(A[i]);
            
            for (int j = i + 1; j < A.length; j++) {
                if (A[j] < A[i]) {
                    cnt++;
                }
            }
            
            ret += cnt * fact / mulFact;
            fact *= A.length - i;
        }
        
        return ret;
    }
}
