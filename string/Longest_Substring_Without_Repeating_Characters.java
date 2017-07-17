/*

Longest Substring Without Repeating Characters


Given a string, find the length of the longest substring without repeating characters.

Example
For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3.

For "bbbbb" the longest substring is "b", with the length of 1.

Challenge 
O(n) time


解：
把遇到过的字符以及其序号放进一个HashMap。
每遇到一个重复的字符，就把上一次出现的这个字符以及之前的字符全部删掉。
每次把HashMap的大小与之前保存的最大值比较，保存较大的那个。

*/

public class Solution {
    /**
     * @param s: a string
     * @return: an integer 
     */
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int ret = 0;
        char[] c = s.toCharArray();
        
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        for (int i = 0; i < c.length; i++) {
            char key = c[i];
            
            if (map.containsKey(key)) {
                for (int j = map.get(key); j >= 0; j--) {
                    if (!map.containsKey(c[j]) || map.get(c[j]) != j) {
                        break;
                    }
                    map.remove(c[j]);
                }
            }
            
            map.put(key, i);
            ret = Math.max(ret, map.size());
        }
        
        return ret;
    }
}
