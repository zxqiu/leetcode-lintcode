/*

Letter Combinations of a Phone Number


Given a digit string excluded 01, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.

Cellphone

 Notice

Although the above answer is in lexicographical order, your answer could be in any order you want.

Have you met this question in a real interview? Yes
Example
Given "23"

Return ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]


解：
递归做所有组合即可。

*/

public class Solution {
    /**
     * @param digits A digital string
     * @return all posible letter combinations
     */
    
    static final String[] DICT = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public ArrayList<String> letterCombinations(String digits) {
        ArrayList<String> ret = new ArrayList<String>();
        
        if (digits == null || digits.length() == 0) {
            return ret;
        }
        
        helper(digits, 0, "", ret);
        
        return ret;
    }
    
    private void helper(String digits, int idx, String path, ArrayList<String> ret) {
        if (idx == digits.length()) {
            ret.add(path);
            return;
        }
        
        String avai = DICT[digits.charAt(idx) - '0'];
        
        for (char c : avai.toCharArray()) {
            helper(digits, idx + 1, path + String.valueOf(c), ret);
        }
    }
}
