/*

Palindrome Partitioning II


Given a string s, cut s into some substrings such that every substring is a palindrome.
Return the minimum cuts needed for a palindrome partitioning of s.

Example

Given s = "aab",
Return 1 since the palindrome partitioning ["aa", "b"] could be produced using 1 cut.


解：

方法一：
DFS
与Palindrome Partition类似，先判断输入字符串的前i个是否为回文，若为回文，则将从i+1开始的字符串作为下一次递归的输入再判断。
不同的是现在需要返回的是包含的回文串的数量-1。
若s能切成3个回文串，则需要切2刀。

这个方法不能accept，因为时间复杂度过高，为O(n * log(n)^2)

*/

public class Solution {
    /**
     * @param s a string
     * @return an integer
     */
    public int minCut(String s) {
        if (s == null) {
            return 0;
        }
        
        int min = s.length() - 1;
        
        for (int i = s.length() - 1; i >= 0; i--) {
            String sub = s.substring(0, i + 1);
            
            if (isPalindrome(sub)) {
                min = Math.min(min, minCut(s.substring(i + 1, s.length())) + 1);
                if (sub.length() == s.length()) {
                    break;
                }
            }
        }
        
        return min;
    }
    
    private boolean isPalindrome(String s) {
        int start, end;
        
        start = 0;
        end = s.length() - 1;
        
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        
        return true;
    }
}


/*

方法二：
Dynanmic Programing
时间O(n * log(n)^2)
空间O(n)

dp[i]表示从0到i-1的子串最少切多少刀。
dp[i+1] = dp[j] + 1, 若从j到i的子串为回文。j遍历0到i，取最小值即可。

这个方法需要每次判断从j到i的子串是否为回文，时间复杂度与方法一相差无几。
但是由于没有用到递归，但是可以accept。可能是因为没有栈的消耗？

*/

public class Solution {
    /**
     * @param s a string
     * @return an integer
     */
     
    // DP solution
    public int minCut(String s) {
        if (s == null) {
            return -1;
        }
        
        char[] source = s.toCharArray();
        int[] dp = new int[source.length + 1];
        dp[0] = -1;
        dp[1] = 0;
        
        for (int i = 2; i < dp.length; i++) {
            dp[i] = i - 1;
            for (int j = 0; j < i; j++) {
                if (isPalindrome(source, j, i - 1)) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        
        return dp[source.length];
    }
    
    private boolean isPalindrome(char[] s, int start, int end) {
        while (start < end) {
            if (s[start] != s[end]) {
                return false;
            }
            start++;
            end--;
        }

        return true;
    }

};


/*

方法三：
双重dynamic programing

在方法二的基础上，再使用一个DP用来判断子串是否为回文
dpPalindrome[i][j]表示从i到j的子串是否为回文。
则dpPalindrome[i][j] = (s[i] == s[j]) && dpPalindrome[i + 1][j - 1]

由于需要用到i + 1和j - 1，所以i应当递减，j应当递增。
因此，输入串需要从末尾向前遍历，dp[i]也变为表示从i到末尾的子串最少需要切几刀。
dp[i] = dp[j + 1] + 1, 若从i到j的子串为回文。

时间复杂度O(n^2)
空间复杂度O(n^2)

该算法对于方法二来说相当于用空间换时间。

*/

public class Solution {
    /**
     * @param s a string
     * @return an integer
     */
     
    // dual DP solution
    public int minCut(String s) {
        if (s == null) {
            return -1;
        }
        
        char[] source = s.toCharArray();
        int[] dp = new int[source.length + 1];
        dp[dp.length - 1] = -1;
        
        boolean[][] dpPalindrome = new boolean[source.length][source.length];
        
        for (int i = dp.length - 2; i >= 0; i--) {
            dp[i] = dp[i + 1] + 1;
            dpPalindrome[i][i] = true;
            for (int j = i + 1; j < source.length; j++) {
                if (source[i] == source[j] &&
                    ((i + 1 <= j - 1 && dpPalindrome[i + 1][j - 1]) ||
                    i + 1 > j - 1)) {
                    dp[i] = Math.min(dp[i], dp[j + 1] + 1);
                    dpPalindrome[i][j] = true;
                }
            }
        }
        
        return dp[0];
    }
};
