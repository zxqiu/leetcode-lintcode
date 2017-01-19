/*
Evaluate Reverse Polish Notation

Evaluate the value of an arithmetic expression in Reverse Polish Notation.
Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Example:
["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6

解：
遍历整个数组，当遇到数字时，放入栈中。
当遇到操作符号时，从栈中取出两个数字进行运算，再把结果放入栈中。

*/

public class Solution {
    /**
     * @param tokens The Reverse Polish Notation
     * @return the value
     */
    public int evalRPN(String[] tokens) {
        String op = "+-*/";
        Stack<Integer> stack = new Stack<Integer>();
        
        for (String s : tokens) {
            if (op.contains(s)) {
                int b = stack.pop();
                int a = stack.pop();
                switch (s.charAt(0)) {
                    case '+':
                        stack.push(a + b);
                        break;
                    case '-':
                        stack.push(a - b);
                        break;
                    case '*':
                        stack.push(a * b);
                        break;
                    case '/':
                        stack.push(a / b);
                }
            } else {
                stack.push(Integer.valueOf(s));
            }
        }
        
        return stack.pop();
    }
}