/*

Restore IP Address


Given a string containing only digits, restore it by returning all possible valid IP address combinations.

Example
Given "25525511135", return

[
  "255.255.11.135",
  "255.255.111.35"
]
Order does not matter.


解：
DFS
按照每段长度进行DFS。
长度可以是1，2，3，故每次递归中遍历这三种情况。
递归深度最大为4，因为IP地址有4段。

有两种情况一个子字符串不能作为有效地质：
1.这个子字符串代表的数字大于0，但是以0开头。
2.这个子字符串代表的数字大于255.

*/


public class Solution {
    /**
     * @param s the IP string
     * @return All possible valid IP addresses
     */
    public ArrayList<String> restoreIpAddresses(String s) {
        ArrayList<String> ret = new ArrayList<String>();
        
        if (s == null || s.length() == 0) {
            return ret;
        }
        
        helper(s, 0, "", 0, ret);
        
        return ret;
    }
    
    private void helper(String s, int idx, String path, int depth, ArrayList<String> ret) {
        if (depth == 4) {
            if (idx == s.length()) {
                ret.add(path.substring(1, path.length()));
            }
            return;
        } else if (s.length() - idx > (4 - depth) * 3
                || s.length() - idx < 4 - depth) {
            return;
        }
        
        for (int i = 1; i <= 3; i++) {
            if (s.length() - idx < i
                || (i > 1 && s.charAt(idx) == '0')) {
                continue;
            }
            
            String sub = s.substring(idx, idx + i);
            
            if (Integer.valueOf(sub) > 255) {
                break;
            }
            
            helper(s, idx + i, path + "." + sub, depth + 1, ret);
        }
    }
}
