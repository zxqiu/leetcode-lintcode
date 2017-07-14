/*

Expression Evaluation


Given an expression string array, return the final result of this expression

 Notice

The expression contains only integer, +, -, *, /, (, ).

Example
For the expression 2*6-(23+7)/(1+2),
input is

[
  "2", "*", "6", "-", "(",
  "23", "+", "7", ")", "/",
  (", "1", "+", "2", ")"
],
return 2


解：
方法一：
先把输入转换成Reverse Polish Notation，然后再计算新的表达式。
可以利用Convert Expression to Reverse Polish Notation的方法进行转换。
参照：
https://github.com/zxqiu/leetcode-lintcode/blob/master/arrays/Convert_Expression_to_Reverse_Polish_Notation.java

计算Reverse Polish Notation时，遇到数字就先入栈，遇到符号就出栈两个数字并进行计算，然后计算结果入栈。


*/


public class Solution {
    /**
     * @param expression: an array of strings;
     * @return: an integer
     */
    public int evaluateExpression(String[] expression) {
        ArrayList<String> list = convertToRPN(expression);
        Stack<Integer> st = new Stack<Integer>();
        
        for (String s : list) {
            int cat = getCategory(s);
            
            if (cat == 2) {
                st.push(Integer.valueOf(s));
            } else {
                int right = st.pop();
                int left = st.pop();
                
                if (s.equals("+")) {
                    st.push(left + right);
                } else if (s.equals("-")) {
                    st.push(left - right);
                } else if (s.equals("*")) {
                    st.push(left * right);
                } else {
                    st.push(left / right);
                }
            }
        }
        
        return st.isEmpty() ? 0 : st.pop();
    }
    
    private ArrayList<String> convertToRPN(String[] expression) {
        ArrayList<String> q = new ArrayList<String>();
        Stack<String> st = new Stack<String>();
        
        for (String s : expression) {
            int cat = getCategory(s);
            
            if (s.equals("(")) {
                st.push(s);
            } else if (s.equals(")")) {
                while (!st.peek().equals("(")) {
                    q.add(st.pop());
                }
                st.pop(); // pop out "("
            } else if (cat == 2) {
                q.add(s);
            } else {
                if (st.isEmpty() || st.peek().equals("(")) {
                    st.push(s);
                } else {
                    while (!st.isEmpty() && !st.peek().equals("(") && getCategory(st.peek()) <= cat) {
                        q.add(st.pop());
                    }
                    st.push(s);
                }
            }
        }
        
        while (!st.isEmpty()) {
            q.add(st.pop());
        }
        
        return q;
    }
    
    private int getCategory(String s) {
        if (s.equals("*") || s.equals("/")) {
            return 0;
        } else if (s.equals("+") || s.equals("-")) {
            return 1;
        } else {
            return 2;
        }
    }
};


/*

方法二：
分析转换成Reverse Polish Notation的过程，可以发现当某一个符号将要加入队列时，其操作数一定已经准备好。
那么我们可以不把该符号入队，而是把其计算的结果入队。

这里为了操作更快，把队列换成栈。
在之前转换的代码基础上，每一次符号要入队时，就进行一次计算。
注意在处理")"时，也需要进行计算。

*/



public class Solution {
    /**
     * @param expression: an array of strings;
     * @return: an integer
     */
    public int evaluateExpression(String[] expression) {
        Stack<Integer> num = new Stack<Integer>();
        Stack<String> op = new Stack<String>();
        
        for (String s : expression) {
            int cat = getCategory(s);
            
            if (s.equals("(")) {
                op.push(s);
            } else if (s.equals(")")) {
                while (!op.peek().equals("(")) {
                    num.push(calc(num, op.pop()));
                }
                op.pop(); // pop out "("
            } else if (cat == 2) {
                num.push(Integer.valueOf(s));
            } else {
                if (op.isEmpty() || op.peek().equals("(")) {
                    op.push(s);
                } else {
                    while (!op.isEmpty() && !op.peek().equals("(") && getCategory(op.peek()) <= cat) {
                        num.add(calc(num, op.pop()));
                    }
                    op.push(s);
                }
            }
        }
        
        while (!op.isEmpty()) {
            num.add(calc(num, op.pop()));
        }
        
        return num.isEmpty() ? 0 : num.pop();
    }
    
    private int getCategory(String s) {
        if (s.equals("*") || s.equals("/")) {
            return 0;
        } else if (s.equals("+") || s.equals("-")) {
            return 1;
        } else {
            return 2;
        }
    }
    
    private int calc(Stack<Integer> num, String op) {
        int right = num.pop();
        int left = num.pop();
        
        if (op.equals("+")) {
            return (left + right);
        } else if (op.equals("-")) {
            return (left - right);
        } else if (op.equals("*")) {
            return (left * right);
        } else {
            return (left / right);
        }
    }
};
