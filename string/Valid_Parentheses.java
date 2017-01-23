/*
Valid Parentheses

Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.


解：
每遇到一个左括号，就放入栈中；每遇到一个右括号，就取出栈顶看是否与其匹配。
若不匹配则返回false，直到整个字符串被遍历。
*/



public class Solution {
    /**
     * @param s A string
     * @return whether the string is a valid parentheses
     */
    public boolean isValidParentheses(String s) {
        Stack<Character> stack = new Stack<Character>();
        
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '<' || c == '[' || stack.isEmpty()) {
                stack.push(c);
            } else {
                char c1 = stack.pop();
                if (c == ')' && c1 != '(') {
                  return false;
                } else if (c == '}' && c1 != '{') {
                  return false;
                } else if (c == ']' && c1 != '[') {
                  return false;
                } else if (c == '>' && c1 != '<') {
                  return false;
                }
            }
        }
        
        return stack.isEmpty() ? true : false;
    }
}
