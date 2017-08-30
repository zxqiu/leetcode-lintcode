/*

Russian Doll Envelopes



You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.

What is the maximum number of envelopes can you Russian doll? (put one inside other)
Have you met this question in a real interview?
Example

Given envelopes = [[5,4],[6,4],[6,7],[2,3]],
the maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).


解：
这道题的两种解法跟Longest Increasing Subsequence思路几乎一致。
唯一不同的是需要在计算LIS之前对输入数据进行排序。


方法一：
Dynanmic Programming

递推公式：
dp[i]表示第i-1个信封包含自己在内最多可以装几个。

首先考虑以下两个前提：
1.如果信封a可以把信封b装进去，那么a一定可以把b里面的所有信封装下。
2.按照长增序排序之后，信封i能装的信封一定在i的左边。

那么可以想到：
1.首先按照长对所有信封排序。
2.每次去当前信封i左边找长和宽都小于i的信封，并把该信封里面的所有信封都装进当前信封，然后统计。

故：
dp[i] = max(dp[j0] + 1, dp[j1] + 1, ...), j0~jn信封满足长和宽都小于i信封


初始条件:
任意信封不装其他任何信封时，统计值都为1。


最后应当返回dp数组中的最大值。
该方法时间复杂度n^2，不能AC。

*/

public class Solution {
    /**
     * @param envelopes a number of envelopes with widths and heights
     * @return the maximum number of envelopes
     */
    public int maxEnvelopes(int[][] envelopes) {
        int[] dp = new int[envelopes.length];
        int max = 0;
        
        Arrays.sort(envelopes, comparator);
        
        for (int i = 0; i < envelopes.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (envelopes[i][0] > envelopes[j][0] && 
                    envelopes[i][1] > envelopes[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(dp[i], max);
        }
        
        return max;
    }
    
    Comparator<int[]> comparator = new Comparator<int[]>() {
        public int compare(int[] a, int[] b) {
               return a[0] - b[0];
        }
    };
}


/*

方法二：
对上面方法进行优化。
如果先对长进行增序排序，如果长相同，则宽度进行降序排序，那么这个问题就转化成了寻找LIS。
可以用LIS的二分法加速，得到时间复杂度nlog(n)。

参照LIS方法二:
https://github.com/zxqiu/leetcode-lintcode/blob/master/arrays/Longest_Increasing_Subsequence.java

*/


public class Solution {
    /**
     * @param envelopes a number of envelopes with widths and heights
     * @return the maximum number of envelopes
     */
    public int maxEnvelopes(int[][] envelopes) {
        int max = 0;
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        Arrays.sort(envelopes, comparator);
        
        for (int i = 0; i < envelopes.length; i++) {
            int left = 0;
            int right = list.size();
            int target = envelopes[i][1];
            
            while (left < right) {
                int mid = (left + right) / 2;
                
                if (list.get(mid) < target) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            
            if (right == list.size()) {
                list.add(target);
            } else {
                list.set(right, target);
            }
        }
        
        return list.size();
    }
    
    Comparator<int[]> comparator = new Comparator<int[]>() {
        public int compare(int[] a, int[] b) {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            } else {
                return a[0] - b[0];
            }
        }
    };
}
