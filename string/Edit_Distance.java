/*

Edit Distance


Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

    Insert a character
    Delete a character
    Replace a character


 Example

Given word1 = "mart" and word2 = "karma", return 3.



解：
dynanmic programming

递推公式：
dp[i][j]表示word1的0~i-1字符需要多少此修改可以变成word2的0~j-1。
word1为y坐标，word2为x坐标。考虑把word2变化为word1。
若word1[i-1] == word2[j-1]，那么，可以理解为从word1的0~i-2与word2的0~j-2变化之后的结果不需变化，两个字符串分别增加一个字符而来。故dp[i][j] = dp[i-1][j-1]。
若word1[i-1] != word2[j-1]，那么有三种方式可以让两个字符串相等，即增加，替换，删除。
以下分析须先记住我们是对每个y坐标横向遍历x轴。
删除相当于对word1的0~i-1与word2的0~j-2的变化结果，增加word2[j-1]这个字符之后再删掉。这里可以理解为，处理word2[j-1]时，word1的0~i-1已经与word2的0~j-2完全一致.后面的分析中同理。
增加相当于对于word1的0~i-2与word2的0~j-1的变化结果，需要对word2的0~j-1增加word1[i-1]这个字符，才能保证两个字符串相等。
替换相当于对于word1的0~i-2与word2的0~j-2的变化结果，把word2[j-1]变化成word1[i-1]，才能保证两个字符串相等。

dp[i][j] = dp[i-1][j-1], word1[i-1] == word2[j-1]
dp[i][j] = min(dp[i-1][j], dp[i-1][j-1], dp[i][j-1]) + 1, word1[i-1] != word2[j-1]


初始条件：
在word1和word2的串首分别加入一个空字符，这样第一行变为把word2变成一个空字符，第一列变为把一个空字符变成word1。
由于把一个空字符变成空字符需要0次变化，把一个空字符加上i长度的字符变成一个空字符需要i次删除，把一个空字符变成一个空字符加上i长度的字符需要i次增加。
所以第一行和第一列的值都为0~n，n是行或者列的长度减一。
dp[0][i] = i
dp[i][0] = i

*/




public class Solution {
    /**
     * @param word1 & word2: Two string.
     * @return: The minimum number of steps.
     */
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        
        // init
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        
        for (int j = 0; j <= word2.length(); j++) {
            dp[0][j] = j;
        }
        
        // calc
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                dp[i][j] = Math.min(dp[i - 1][j],
                            Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
                dp[i][j] = (word1.charAt(i - 1) == word2.charAt(j - 1)) ? dp[i - 1][j - 1] : dp[i][j] + 1;
            }
        }
        
        return dp[word1.length()][word2.length()];
    }
}
