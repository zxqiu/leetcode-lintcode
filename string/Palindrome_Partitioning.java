/*

Palindrome partitioning


Given a string s, partition s such that every substring of the partition is a palindrome.
Return all possible palindrome partitioning of s.



Example

Given s = "aab", return:

[
  ["aa","b"],
  ["a","a","b"]
]



解：

方法一：
DFS

当输入字符串的前i个字符可以构成回文的时候，将从第i+1开始的字符串作为下一次递归的输入。
如果整个输入字符串为一回文串时，表示整个最初的输入字符串已经被遍历了一次，该次递归应当返回。
*/



public class Solution {
    /**
     * @param s: A string
     * @return: A list of lists of string
     */
    public List<List<String>> partition(String s) {
        List<List<String>> ret = new ArrayList<List<String>>();
        if (s == null || s.length() == 0) {
            return ret;
        }
        
        for (int i = 0; i < s.length(); i++) {
            String sub = s.substring(0, i + 1);
            
            if (isPalindrome(sub)) {
                if (sub.length() == s.length()) {
                    List<String> list = new ArrayList<String>();
                    list.add(sub);
                    ret.add(list);
                }
                
                List<List<String>> tmp = partition(s.substring(i + 1, s.length()));
                for (List<String> list : tmp) {
                    list.add(0, sub);
                    ret.add(list);
                }
            }
        }

        // ret.size() will never be 0 if s.length() is not 0
        return ret;
    }
    
    private boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        
        for (int i = 0; i <= (s.length() - 1) / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        
        return true;
    }
}

/*

方法二：
先用Dynanmic Programming求解任意子字符串是否为回文，然后再用DFS求解partition。

*/

public class Solution {
    /**
     * @param s: A string
     * @return: A list of lists of string
     */
    public List<List<String>> partition(String s) {
        boolean[][] isP = new boolean[s.length()][s.length()];
        List<List<String>> ret = new ArrayList<List<String>>();
        
        for (int i = s.length() - 1; i >= 0; i--) {
            isP[i][i] = true;
            for (int j = i + 1; j < s.length(); j++) {
                if ((j == i + 1 || isP[i + 1][j - 1]) && s.charAt(i) == s.charAt(j)) {
                    isP[i][j] = true;
                }
            }
        }
        
        helper(ret, new ArrayList<String>(), s, 0, isP);
        
        return ret;
    }
    
    private void helper(List<List<String>> ret, List<String> path, String s, int start, boolean[][] isP) {
        if (start == s.length()) {
            ret.add(new ArrayList<String>(path));
            return;
        }
        
        for (int i = start; i < s.length(); i++) {
            if (!isP[start][i]) {
                continue;
            }
            
            path.add(s.substring(start, i + 1));
            helper(ret, path, s, i + 1, isP);
            path.remove(path.size() - 1);
        }
    }
}
