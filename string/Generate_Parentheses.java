/*

Generate Parentheses


Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

Example
Given n = 3, a solution set is:

"((()))", "(()())", "(())()", "()(())", "()()()"


解：
DFS
每一个位置可以放置"("或者")"。
每放置一个"("，后面就可以放置")"。

故设置一个")"计数器right，
1.每当放置一个"("，n减一，right加一。
2.每放置一个")",n不变，right减一。

计算直到n和right都为0，则得到了一个有效解。

*/


public class Solution {
    /**
     * @param n n pairs
     * @return All combinations of well-formed parentheses
     */
    public ArrayList<String> generateParenthesis(int n) {
        ArrayList<String> ret = new ArrayList<String>();
        
        helper(n, 0, "", ret);
        return ret;
    }
    
    private void helper(int n, int right, String path, ArrayList<String> ret) {
        if (n == 0 && right == 0) {
            if (path.length() > 0) {
                ret.add(path);
            }
            return;
        }
        
        if (n > 0) {
            helper(n - 1, right + 1, path + "(", ret);
        }
        
        if (right > 0) {
            helper(n, right - 1, path + ")", ret);
        }
    }
}
