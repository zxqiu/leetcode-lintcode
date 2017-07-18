/*

House Robber




You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

Example

Given [3, 8, 4], return 8.
Challenge

O(n) time and O(1) memory.


解：
Dynanmic programming

方法一：
递推公式：
dp[i]表示，抢A[i]的情况下，到i为止最大能抢多少。
由于i一定要抢，所以i-1一定不能抢。
i-2可以抢也可以不抢，取其中大的那个。
故：
dp[i] = max(dp[i-2], dp[i-3])

初始条件：
需要用到i-3，故必须初始化dp数组的前三个值。
dp[0] = A[0]，只抢0
dp[1] = A[1]，只抢1
dp[2] = A[0] + A[2]，抢0和2

时间复杂度O(n)，空间复杂度O(n)

*/


public class Solution {
    /**
     * @param A: An array of non-negative integers.
     * return: The maximum amount of money you can rob tonight
     */
    public long houseRobber(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        } else if (A.length == 1) {
            return A[0];
        } else if (A.length == 2) {
            return (long)Math.max(A[0], A[1]);
        }
        
        long[] dp = new long[A.length];
        
        dp[0] = A[0];
        dp[1] = A[1];
        dp[2] = A[0] + A[2];
        
        for (int i= 3; i < A.length; i++) {
            dp[i] = Math.max(dp[i - 2], dp[i - 3]) + A[i];
        }
        
        return Math.max(dp[A.length - 1], dp[A.length - 2]);
    }
}

/*

方法二：
由于只需要用到i前面三个dp值，故可以只保存3个。
利用Queue先进先出的特点，每次取出第一个dp值，然后再末尾加入新的dp值，保持queue中一直只有三个数字。

*/

public class Solution {
    /**
     * @param A: An array of non-negative integers.
     * return: The maximum amount of money you can rob tonight
     */
    public long houseRobber(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        } else if (A.length == 1) {
            return A[0];
        } else if (A.length == 2) {
            return (long)Math.max(A[0], A[1]);
        }
        
        Queue<Long> q = new LinkedList<Long>();
        
        q.offer((long)A[0]);
        q.offer((long)A[1]);
        q.offer((long)A[0] + A[2]);
                
        for (int i= 3; i < A.length; i++) {
            long tmp = Math.max(q.poll(), q.peek()) + A[i];
            q.offer(tmp);
        }
        
        q.poll();
        return Math.max(q.poll(), q.peek());
    }
}
