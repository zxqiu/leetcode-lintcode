/*

Convert Expression to Reverse Polish Notation


Given an expression string array, return the Reverse Polish notation of this expression. (remove the parentheses)

Example
For the expression [3 - 4 + 5] (which denote by ["3", "-", "4", "+", "5"]), return [3 4 - 5 +] (which denote by ["3", "4", "-", "5", "+"])


解：
参考http://faculty.cs.niu.edu/~hutchins/csci241/eval.htm
     Start with an empty stack.  We scan Q from left to right. 

     While (we have not reached the end of Q)
        If (an operand is found)
           Add it to P
        End-If
        If (a left parenthesis is found) 
           Push it onto the stack
        End-If
        If (a right parenthesis is found) 
           While (the stack is not empty AND the top item is
                  not a left parenthesis)
              Pop the stack and add the popped value to P
           End-While
           Pop the left parenthesis from the stack and discard it 
        End-If
        If (an operator is found)
           If (the stack is empty or if the top element is a left
               parenthesis)
              Push the operator onto the stack
           Else  
              While (the stack is not empty AND the top of the stack 
                     is not a left parenthesis AND precedence of the                  
                     operator <= precedence of the top of the stack)
                 Pop the stack and add the top value to P
              End-While
              Push the latest operator onto the stack     
           End-If  
        End-If
     End-While
     While (the stack is not empty)
        Pop the stack and add the popped value to P
     End-While

思路：
首先数字的顺序不需要修改，只有符号顺序可能需要颠倒。
颠倒顺序可以使用Stack。
当某一个符号的左右操作数都已经放进队列之后，这个符号才应该被放进队列。在此之前，这个符号放在stack中。

所以遇到数字时，直接放进队列。
当遇到符号时：
1. 如果stack为空，这个符号不论是什么直接入栈。
2. 如果stack不为空，且栈顶符号优先级高于或者等于当前符号，说栈顶符号应当先计算，故出栈并入队。
3. 重复2直到栈顶符号优先级低于当前符号。

当遇到括号时，从左括号到右括号之间的所有字符应当优先处理。故如果栈顶是一个"("，应当当作空栈。
如果遇到")"，应当将"("之前的所有符号出栈并入队。

循环结束时，如果stack不为空，则应当把其中所有符号出栈并入队。

*/

public class Solution {
    /**
     * @param expression: A string array
     * @return: The Reverse Polish notation of this expression
     */
    public ArrayList<String> convertToRPN(String[] expression) {
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
}
