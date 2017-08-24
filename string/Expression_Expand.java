/*

Expression Expand


Given an expression s includes numbers, letters and brackets. Number represents the number of repetitions inside the brackets(can be a string or another expression)．Please expand expression to be a string.

Example
s = abc3[a] return abcaaa
s = 3[abc] return abcabcabc
s = 4[ac]dy, return acacacacdy
s = 3[2[ad]3[pf]]xyz, return adadpfpfpfadadpfpfpfadadpfpfpfxyz

Challenge 
Can you do it without recursion?


解：
Stack
用两个stack，分别保存对应的系数和字符串。
1.首先在字串stack中入栈一个空字串。
2.遇到数字时，记入系数。
3.遇到‘[’，将记录的系数时入栈系数stack，在字串stack中入栈空字串，并将保存的系数置零。
4.遇到‘]’，出栈系数stack和字串stack，并根据系数计算得到的字符串，然后将该字串连接在此时的栈顶字串后面。

例：
2[a1[b]2[c]]
        系数stack                  字串stack
          []                          [""]
2         []                          [""]
[         [2]                         ["", ""]
a         [2]                         ["", "a"]
1         [2]                         ["", "a"]
[         [2, 1]                      ["", "a", ""]
b         [2, 1]                      ["", "a", "b"]
]         [2]                         ["", "ab"]
2         [2]                         ["", "ab"]
[         [2, 2]                      ["", "ab", ""]
c         [2, 2]                      ["", "ab", "c"]
]         [2]                         ["", "abcc"]
]         []                          ["abccabcc"]



*/

public class Solution {
    /**
     * @param s  an expression includes numbers, letters and brackets
     * @return a string
     */
    public String expressionExpand(String s) {
        Stack<Integer> factor = new Stack<Integer>();
        Stack<String> value = new Stack<String>();
        int cnt = 0;
        value.push("");
        
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                cnt = cnt * 10 + (c - '0');
            } else if (c == '[') {
                factor.push(cnt);
                value.push("");
                cnt = 0;
            } else if (c == ']') {
                String top = repeat(factor.pop(), value.pop());
                value.push(value.pop() + top);
            } else {
                value.push(value.pop() + String.valueOf(c));
            }
        }
        
        return value.pop();
    }
    
    private String repeat(int times, String s) {
        String ret = "";
        for (int i = 0; i < times; i++) {
            ret += s;
        }
        
        return ret;
    }
}
