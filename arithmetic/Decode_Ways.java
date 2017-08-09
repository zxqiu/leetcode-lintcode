/*

Decode Ways


A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26

Given an encoded message containing digits, determine the total number of ways to decode it.


Example

Given encoded message 12, it could be decoded as AB (1 2) or L (12).
The number of ways decoding 12 is 2.


解：
Dynanmic Programming

递推公式：
dp[i]表示从0开始长度为i的substing有多少种解码方法。
1.如果i不为0，那么i单独解码时有dp[i-1]种方法。
2.如果i-1和i组合成的数字大于0，小于等于26，且数字i-1不为0（与1中值不一样），那么这个组合可以解码，故有dp[i-2]种方法。
上面两种可能性相互独立，应当相加。

故：
if (oneDigit != 0) {
    dp[i] += dp[i - 1];
}
if (twoDigit <= 26 && twoDigit > 0 && twoDigit != oneDigit) {
    dp[i] += dp[i - 2];
}

初始条件：
由于需要用到dp[i-2]，故需要初始化前两个
当有0个字符时，有1种解码方法即空解。
当有1个字符时，如果不为‘0’，有1种解码方法。如果为‘0’，该字符串不能解码。
故：
dp[0] = dp[1] = 1


*/

public class Solution {
    /**
     * @param s a string,  encoded message
     * @return an integer, the number of ways decoding
     */
    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) - '0' == 0) {
            return 0;
        }
        
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = 1;
        
        for (int i = 2; i <= s.length(); i++) {
            int oneDigit = s.charAt(i - 1) - '0';
            int twoDigit = Integer.valueOf(s.substring(i - 2, i));
            
            if (oneDigit != 0) {
                dp[i] += dp[i - 1];
            }
            if (twoDigit <= 26 && twoDigit > 0 && twoDigit != oneDigit) {
                dp[i] += dp[i - 2];
            }
        }
        
        return dp[s.length()];
    }
}
