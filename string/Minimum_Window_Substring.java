/*

Minimum Window Substring

Given a string source and a string target, find the minimum window in source which will contain all the characters in target.

 Notice

If there is no such window in source that covers all characters in target, return the emtpy string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in source.

Clarification
Should the characters in minimum window has the same order in target?

Not necessary.

Example
For source = "ADOBECODEBANC", target = "ABC", the minimum window is "BANC"

Challenge 
Can you do it in time complexity O(n) ?


解：
使用两个指针，一个快一个慢。
1.快指针先在souce中向后查找，找到target中所有字符后停止。
2.慢指针接着在source中向后查找，找到一个target中的字符c后，移动到该字符后面一个位置。并记录两指针间距+1，也就是一个包含target的子串长度。
3.快指针继续查找，直到找到一个新的字符c。比较新的子串长度和旧的子串长度，记录小的那个。
4.重复1到3。

*/

public class Solution {
    /**
     * @param source: A string
     * @param target: A string
     * @return: A string denote the minimum window
     * Return "" if there is no such a string
     */
    public String minWindow(String source, String target) {
        if (source == null || target == null) {
            return null;
        }
        
        char[] s = source.toCharArray();
        char[] t = target.toCharArray();
        int slow, fast, minStart, minLen, remain;
        int[] cnt = new int[256];
        
        slow = fast = minStart = 0;
        minLen = Integer.MAX_VALUE;
        remain = t.length;
        
        for (char c : t) {
            cnt[c]++;
        }
        
        while (fast < s.length) {
            if (cnt[s[fast]] > 0) {
                remain--;
            }
            cnt[s[fast++]]--;
            
            while (remain == 0) {
                if (fast - slow < minLen) {
                    minLen = fast - slow;
                    minStart = slow;
                }
                if (cnt[s[slow]] >= 0) {
                    remain++;
                }
                cnt[s[slow++]]++;
            }
        }
        
        minLen = (minLen == Integer.MAX_VALUE) ? 0 : minLen;
        return source.substring(minStart, minStart + minLen);
    }
}
