/*

Distinct Subsequences

Given a string S and a string T, count the number of distinct subsequences of T in S.
A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).


Example
Given S = "rabbbit", T = "rabbit", return 3.


解：

方法一：
dynanmic programming

递推公式：
dp[i][j]表示T中的前i个字符（即从0到i-1）在S的前j个字符（即从0到j-1）中有几种子序列。
S为行坐标，T为列坐标。
对于dp[i][j]来说存在两种可能，即字符S[j-1]与T[i-1]相等或这不等。
当不等时，T的前i个字符至少可以匹配S的前j-1个字符（即从0到j-2），也就是与dp[i][j-1]相等。
当相等时，我们可以用这两个相等的字符与S前j-1和T前i-1个字符组合，也可以用S[j-1]与S前j-2和T前i-1个字符组合。即dp[i-1][j-1] + dp[i][j-1]。
换个方式分析相等的情况。
一：可以想象一下，若S的前j-1和T前i-1个字符完全相等，那么当S[j-1]和T[i-1]相等时，若与其进行组合，不会增加匹配的数量，直接取dp[i-1][j-1]。
二：若S的前j-1和T前i个字符完全相等，那么当增加一个新的字符即S[j-1]时，由于S[j-1]和T[i-1]相等这种情况已经计算过一次，S[j-1]会取代之前已经匹配过的S串中的最后一个字符，故总匹配数量与之前持平。
两种情况都独立可行，故相加。

dp[i][j] = dp[i][j-1], S[j-1] != T[i-1]
dp[i][j] = dp[i-1][j-1] + dp[i][j-1], S[j-1] == T[i-1]

初始条件：
由于需要用到矩阵的左方和左上方的数据，所以需要初始化第一行和第一列。
第一行和第一列分别表示S和T分别与一个空字符匹配。
那么第一行为一个空字符在S中寻找匹配数量，全为1。
第一列为T在一个空字符串中匹配，全为0。

*/


public class Solution {
    /**
     * @param S, T: Two string.
     * @return: Count the number of distinct subsequences
     */
    public int numDistinct(String S, String T) {
        int[][] dp = new int[T.length() + 1][S.length() + 1];
        Arrays.fill(dp[0], 1);
        
        for (int i = 1; i <= T.length(); i++) {
            for (int j = 1; j <= S.length(); j++) {
                dp[i][j] = dp[i][j - 1];
                if (S.charAt(j - 1) == T.charAt(i - 1)) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }
        
        return dp[T.length()][S.length()];
    }
}


/*

方法二：
与方法一一致，优化了空间结构。
由于每次计算只需要左上方和左边的值，所以干脆使用两个变量来保存这两个值。

*/

public class Solution {
    /**
     * @param S, T: Two string.
     * @return: Count the number of distinct subsequences
     */
     
    public int numDistinct(String S, String T) {
        int[] dp = new int[S.length() + 1];
        Arrays.fill(dp, 1);
        
        for (int i = 1; i <= T.length(); i++) {
            int upLeft = i == 1 ? 1 : 0;
            int left = 0;
            for (int j = 1; j <= S.length(); j++) {
                int tmp = dp[j];
                dp[j] = left;
                if (S.charAt(j - 1) == T.charAt(i - 1)) {
                    dp[j] += upLeft;
                }
                upLeft = tmp;
                left = dp[j];
            }
        }
        
        return dp[S.length()];
    }
}