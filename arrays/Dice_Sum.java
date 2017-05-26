/*

Dice Sum

Throw n dices, the sum of the dices' faces is S. Given n, find the all possible value of S along with its probability.

 Notice

You do not care about the accuracy of the result, we will help you to output results.

Have you met this question in a real interview? Yes

Example
Given n = 1, return [ [1, 0.17], [2, 0.17], [3, 0.17], [4, 0.17], [5, 0.17], [6, 0.17]].


解：
这道题放进arrays是因为其实其本质是排列组合。

解法一：
DFS 递归
利用求解permutation的思路，把所有可能的色子组合全部找出来，每个组合的概率都相等，然后求和统计。
这种方法会超时。


*/

public class Solution {
    /**
 *      * @param n an integer
 *           * @return a list of Map.Entry<sum, probability>
 *                */
    public List<Map.Entry<Integer, Double>> dicesSum(int n) {
        
        List<Map.Entry<Integer, Double>> ret = new ArrayList<Map.Entry<Integer, Double>>();
        HashMap<Integer, Double> keys = new HashMap<Integer, Double>();
        double probility = 1 / (double)Math.pow(6, n);
        
        helper(n, probility, 0, keys);
        
        for (Map.Entry<Integer, Double> entry : keys.entrySet()) {
            ret.add(new AbstractMap.SimpleEntry<Integer, Double>(entry.getKey(), entry.getValue()));
        }
        
        return ret;
    }
    
    private void helper(int n, double prob, int path, HashMap<Integer, Double> keys) {
        if (n == 0) {
            if (!keys.containsKey(path)) {
                keys.put(path, 0.0);
            }
            keys.put(path, keys.get(path) + prob);
            return;
        }
        
        for (int i = 1; i <= 6; i++) {
            helper(n - 1, prob, path + i, keys);
        }
    }
}

/*

解法二：
跟方法一思路一致，不过把递归变成循环，同时直接保存每组色子的和以及对应的概率。
概率的计算思路为：
1. 如果该组合的和值不存在，则其概率为每个色子的概率乘积。
2. 如果该组合的和值已存在，则其概率为已经存在的概率，加上每个色子概率乘积。

最外层循环表示掷色子的次数，第二层为每个色子可能的值。
每次投掷时，对于上一轮投掷的每一个结果，都有可能产生1到6一共六种可能，故第三层循环遍历上一次的结果。

不会超时但不是最优解。


*/

public class Solution {
    /**
     * @param n an integer
     * @return a list of Map.Entry<sum, probability>
     */
    public List<Map.Entry<Integer, Double>> dicesSum(int n) {
        //Write your code here
        //Ps. new AbstractMap.SimpleEntry<Integer, Double>(sum, pro)
        //to create the pair
        
        List<Map.Entry<Integer, Double>> ret = new ArrayList<Map.Entry<Integer, Double>>();
        TreeMap<Integer, Double> keys = new TreeMap<Integer, Double>();
        double probility = 1.0 / 6.0;
        
        keys.put(0, 1.0);
        
        for (int i = 0; i < n; i++) {
            TreeMap<Integer, Double> tmp = new TreeMap<Integer, Double>();
            for (int j = 1; j <= 6; j++) {
                for (Map.Entry<Integer, Double> entry : keys.entrySet()) {
                    int newKey = entry.getKey() + j;
                    if (!tmp.containsKey(newKey)) {
                        tmp.put(newKey, 0.0);
                    }
                    tmp.put(newKey, tmp.get(newKey) + entry.getValue() * probility);
                }
            }
            keys = tmp;
        }        
        
        for (Map.Entry<Integer, Double> entry : keys.entrySet()) {
            ret.add(new AbstractMap.SimpleEntry<Integer, Double>(entry.getKey(), entry.getValue()));
        }
        
        return ret;
    }
}

/*

解法三：
Dynanmic Programming

递推公式：
dp[i][j]表示投掷i次时，和为j的概率。
假设本次投掷的值为k(1~6)，有
dp[i][j] = dp[i - 1][j - 1] + ... + dp[i - 1][j - k], k < j && k <= 6 && k >= 1

初始条件：
当第一次投掷时，j只可能为1到6，并且概率都为1/6，所以：
dp[1][j] = 1/6, j >= 1 && j <= 6

得到计算结果后，生成结果队列返回即可。

*/

public class Solution {
    /**
     * @param n an integer
     * @return a list of Map.Entry<sum, probability>
     */
    public List<Map.Entry<Integer, Double>> dicesSum(int n) {
        //Write your code here
        //Ps. new AbstractMap.SimpleEntry<Integer, Double>(sum, pro)
        //to create the pair
        
        List<Map.Entry<Integer, Double>> ret = new ArrayList<Map.Entry<Integer, Double>>();
        double[][] pair = new double[n + 1][n * 6 + 1];
        for (int i = 1; i <= 6; i++) {
            pair[1][i] = 1.0 / 6.0;
        }
        
        for (int i = 2; i <= n; i++) {
            for (int j = i; j <= n * 6; j++) {
                for (int k = 1; k <= 6; k++) {
                    if (k < j)
                        pair[i][j] += pair[i - 1][j - k] / 6.0;
                }
            }
        }
        
        for (int i = n; i <= n * 6; i++) {
            if (pair[n][i] == 0) {
                continue;
            }
            ret.add(new AbstractMap.SimpleEntry<Integer, Double>(i, pair[n][i]));
        }
        
        return ret;
    }
}
