/*

Jump Game

Given an array of non-negative integers, you are initially positioned at the first index of the array.
Each element in the array represents your maximum jump length at that position.
Determine if you are able to reach the last index.

 Notice

This problem have two method which is Greedy and Dynamic Programming.
The time complexity of Greedy method is O(n).
The time complexity of Dynamic Programming method is O(n^2).
We manually set the small data set to allow you pass the test in both ways. This is just to let you learn how to use this problem in dynamic programming ways. If you finish it in dynamic programming ways, you can try greedy method to make it accept again.


解：
方法一：
dynamic programming

递推公式：
dp[i]表示i点可否到达。
dp[i] = dp[j] && A[j] + j > i, j为小于i的任意一点。
也就是说，只要i之前的任意一点可达，而且从该点可以到达i，那么dp[i]为真。

初始条件：
第一个点可达，也就是dp[0]为真。

*/

public class Solution {
    /**
     * @param A: A list of integers
     * @return: The boolean answer
     */
    public boolean canJump(int[] A) {
        boolean[] dp = new boolean[A.length];
        dp[0] = true;
        
        for (int i = 1; i < A.length; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && A[j] + j >= i) {
                    dp[i] = true;
                    break;
                }
            }
        }
        
        return dp[A.length - 1];
    }
}


/*

方法二：
greedy

用reach保存能跳到的最远距离。
如果reach大于当前位置，那么比较reach和从当前位置出发能跳到的最远距离，更新reach。
直到遍历整个数组。

*/


public class Solution {
    /**
     * @param A: A list of integers
     * @return: The boolean answer
     */
    public boolean canJump(int[] A) {
        int reach = 0;
        
        for (int i = 0; i < A.length; i++) {
            if (reach < i) {
                return false;
            }
            
            reach = Math.max(reach, A[i] + i);
        }
        
        return true;
    }
}
