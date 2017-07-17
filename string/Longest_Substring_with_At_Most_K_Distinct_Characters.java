/*

Longest Substring with At Most K Distinct Characters


Given a string s, find the length of the longest substring T that contains at most k distinct characters.

Example
For example, Given s = "eceba", k = 3,

T is "eceb" which its length is 4.

Challenge 
O(n), n is the size of the string s.


解：
1.用left和right两个指针确定一个子字符串。
2.用HashMap统计当前子字符串中每个字符的数量。
3.每次循环右移right，把新字符放进HashMap中。
4.如果HashMap的大小超过k，说明字符的种类已经超过k，此时应当把left指向的字符去掉，也就是left右移，并对HashMap中的对应项减一。如果HashMap中某一项值为0，则删掉。
5.重复4直到HashMap的大小满足条件。

*/

public class Solution {
    /**
     * @param s : A string
     * @return : The length of the longest substring 
     *           that contains at most k distinct characters.
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k == 0) {
            return 0;
        }
        
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        char[] c = s.toCharArray();
        int left = 0;
        int right = 0;
        int ret = 0;
        
        while (right < s.length()) {
            char key = c[right];
            
            /* put the new char in */
            if (!map.containsKey(key)) {
                map.put(key, 0);
            }
            map.put(key, map.get(key) + 1);
            
            /* remove the excessive ones */
            while (map.size() > k) {
                map.put(c[left], map.get(c[left]) - 1);
                if (map.get(c[left]) < 1) {
                    map.remove(c[left]);
                }
                left++;
            }
            
            ret = Math.max(ret, right - left + 1);
            right++;
        }
        
        return ret;
    }
}
