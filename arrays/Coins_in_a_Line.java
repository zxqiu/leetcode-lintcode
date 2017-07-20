/*

Coins in a Line

There are n coins in a line. Two players take turns to take one or two coins from right side until there are no more coins left. The player who take the last coin wins.

Could you please decide the first play will win or lose?

Example
n = 1, return true.

n = 2, return true.

n = 3, return false.

n = 4, return true.

n = 5, return true.

Challenge 
O(n) time and O(1) memory


解：
只要最后让后手面对3个硬币，先手就一定能赢。
也就是先手如果面对4个或者5个硬币，只要不是6个，就一定能赢。
依此类推，只要n不是三的倍数先手就一定能赢。
在此不用这种方法解题，但是思路类似。

方法一：
Dynanmic Programming

递推公式：
dp[i]表示有i个硬币时，先手是否必胜。
分析倒数第二回合，先手一共有两种可能性：
1.拿走一个硬币。
  1）后手拿走一个硬币，剩余i-2个硬币，则dp[i] = dp[i-2]；
  2）后手拿走两个硬币，剩余i-3个硬币，则dp[i] = dp[i-3]；
2.拿走两个硬币。
  1）后手拿走一个硬币，剩余i-3个硬币，则dp[i] = dp[i-3];
  2）后手拿走两个硬币，剩余i-4个硬币，则dp[i] = dp[i-4]。

综上，想要必胜，则先手拿走一个硬币时(dp[i-2] && dp[i-3])须为真，先手拿走两个硬币时(dp[i-3] && dp[i-4])须为真。
故：
dp[i] = (dp[i-2] && dp[i-3]) || (dp[i-3] && dp[i-4])

初始条件：
最多需要用到dp[i-4]，故需要初始化四个值。
dp[0]=false
dp[1]=true
dp[2]=true
dp[3]=false


时间复杂度O(n)，空间复杂度O(n)

*/

public class Solution {
    /**
     * @param n: an integer
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int n) {
        boolean[] dp = new boolean[n + 5];
        
        dp[0] = false;
        dp[1] = true;
        dp[2] = true;
        dp[3] = false;
        
        for (int i = 4; i <= n; i++) {
            dp[i] = (dp[i - 2] & dp[i - 3]) | (dp[i - 3] & dp[i - 4]);
        }
        
        return dp[n];
    }
}

/*

方法二：
分析上面计算过程，发现只需要保存过去的四个值就够了。
故可以使用一个ArrayList来代替dp数组。

时间复杂度O(n)，空间复杂度O(1)

*/

public class Solution {
    /**
     * @param n: an integer
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int n) {
        switch(n) {
            case 0 :
                return false;
            case 1 :
                return true;
            case 2 :
                return true;
            case 3 :
                return false;
            default :
                break;
        }
        
        ArrayList<Boolean> list = new ArrayList<Boolean>();

        list.add(false); // n == 0        
        list.add(true);  // n == 1
        list.add(true);  // n == 2
        list.add(false); // n == 3
        
        for (int i = 4; i <= n; i++) {
            boolean b = (list.get(2) && list.get(1)) || (list.get(1) & list.get(0));
            list.remove(0);
            list.add(b);
        }
        
        return list.get(3);
    }
}
