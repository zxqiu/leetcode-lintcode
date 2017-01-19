/*

Length of last word

Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.
If the last word does not exist, return 0.

Notice
A word is defined as a character sequence consists of non-space characters only.


解：
先用trim()去掉字符串两端的空格，然后从后向前找，每遇到一个非空格的字符便给计数器加一，直到遇到一个空格或者遍历整个字符串。
*/




public class Solution {
    /**
     * @param s A string
     * @return the length of last word
     */
    public int lengthOfLastWord(String s) {
        int cnt = 0;
        String sTrim = s.trim();
        
        for (int i = sTrim.length() - 1; i >=0; i--) {
            if (sTrim.charAt(i) == ' ') {
                break;
            }
            cnt++;
        }
        
        return cnt;
    }
}
