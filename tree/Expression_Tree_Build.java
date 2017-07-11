/*

Expression Tree Build


The structure of Expression Tree is a binary tree to evaluate certain expressions.
All leaves of the Expression Tree have an number string value. All non-leaves of the Expression Tree have an operator string value.

Now, given an expression array, build the expression tree of this expression, return the root of this expression tree.

Clarification
See wiki:
Expression Tree

Example
For the expression (2*6-(23+7)/(1+2)) (which can be represented by ["2" "*" "6" "-" "(" "23" "+" "7" ")" "/" "(" "1" "+" "2" ")"]). 
The expression tree will be like

                 [ - ]
             /          \
        [ * ]              [ / ]
      /     \           /         \
    [ 2 ]  [ 6 ]      [ + ]        [ + ]
                     /    \       /      \
                   [ 23 ][ 7 ] [ 1 ]   [ 2 ] .
After building the tree, you just need to return root node [-].



解:
方法一：
DFS
每次找出当前子数组中的root。
如果start大于end，这个root应当为该子数组中最后一个进行运算的运算符。如果有多个满足条件的运算符，则应当为最右边的那个。
如果start等于end，说明root应当为start指向的数字。

*/

/**
 * Definition of ExpressionTreeNode:
 * public class ExpressionTreeNode {
 *     public String symbol;
 *     public ExpressionTreeNode left, right;
 *     public ExpressionTreeNode(String symbol) {
 *         this.symbol = symbol;
 *         this.left = this.right = null;
 *     }
 * }
 */

public class Solution {
    /**
     * @param expression: A string array
     * @return: The root of expression tree
     */
    public ExpressionTreeNode build(String[] expression) {
        return helper(expression, 0, expression.length - 1);
    }
    
    private ExpressionTreeNode helper(String[] expression, int start, int end) {
        int rootIdx;
        ExpressionTreeNode root;
        
        if (start > end) {
            return null;
        } else if (start == end) {
            rootIdx = start;
        } else {
            rootIdx = findRoot(expression, start, end);
        }
        
        if (rootIdx == -1) {
            start++;
            end--;
            return helper(expression, start, end);
        }
        
        root = new ExpressionTreeNode(expression[rootIdx]);
        
        root.left = helper(expression, start, rootIdx - 1);
        root.right = helper(expression, rootIdx + 1, end);
        
        return root;
    }
    
    private int findRoot(String[] expression, int start, int end) {
        Stack<String> brace = new Stack<String>();
        int ret = -1;
        
        while (end > start) {
            if (expression[end].equals(")")) {
                brace.push(")");
            } else if (expression[end].equals("(")) {
                brace.pop();
            }
            
            if (brace.isEmpty()) {
                if (expression[end].equals("+") || expression[end].equals("-")) {
                    return end;
                } else if (ret == -1 && (expression[end].equals("*") || expression[end].equals("/"))) {
                    ret = end;
                }
            }
            
            end--;
        }
        
        return ret;
    }
}


/*

方法二：
Stack

对每个字符串都计算priority。
规则是乘除号为base+2，加减号为base+1，数字为最大整数。
base初始为0，如果遇到（则加10，遇到）则减10。

基本运算规则是保证priority越小的节点越靠上。

每次循环将stack中大于等于当前字符串priority的节点全部出栈，把其中最后一个也就是仅次于当前priority的节点作为当前节点的左孩子。
分析一下原理，如果stack中只有一个元素，那么这个剩余的元素一定是数字。这是因为任意符号左边一定是数字。
如果stack中有多个元素，那么最后一个元素一定是符号，因为符号的priority小于数字。其之前如果存在数字在计算该符号时就已经成为其左孩子。
而符号一定是某一个子树的根节点，因为叶子节点一定全为数字。
由此可知，经过上面的运算，当前节点的左孩子一定是之前所有节点中最大子树的根节点。

此时stack如果不为空，栈顶节点priority一定小于当前节点，故把当前节点作为栈顶节点的右孩子。
这样设定一次右孩子并不意味着右孩子已经完全确定。如果后面出现了比已经设定好的右孩子priority更高的节点，将会覆盖这次设定。
比如 a+(b*c)*(c*d)，+的右孩子将会先设成b，然后修改成*，最后改成“)*(”中间的那个*。


*/

/**
 * Definition of ExpressionTreeNode:
 * public class ExpressionTreeNode {
 *     public String symbol;
 *     public ExpressionTreeNode left, right;
 *     public ExpressionTreeNode(String symbol) {
 *         this.symbol = symbol;
 *         this.left = this.right = null;
 *     }
 * }
 */

public class Solution {
    /**
     * @param expression: A string array
     * @return: The root of expression tree
     */
    
    private class Node {
        int priority;
        ExpressionTreeNode eNode;
        
        Node(String s, int priority) {
            eNode = new ExpressionTreeNode(s);
            this.priority = priority;
        }
    }
    
    public ExpressionTreeNode build(String[] expression) {
        Stack<Node> st = new Stack<Node>();
        int base = 0;
        
        for (String s : expression) {
            if (s.equals("(")) {
                base += 10;
                continue;
            } else if (s.equals(")")) {
                base -= 10;
                continue;
            }
            
            int priority = calcPriority(s, base);
            Node node = new Node(s, priority);
            
            while (!st.isEmpty() && priority <= st.peek().priority) {
                node.eNode.left = st.pop().eNode;
            }
            
            if (!st.isEmpty()) {
                st.peek().eNode.right = node.eNode;
            }
            
            st.push(node);
        }
        
        if (st.isEmpty()) {
            return null;
        }
        
        while (st.size() > 1) {
            st.pop();
        }
        
        return st.peek().eNode;
    }
    
    private int calcPriority(String s, int base) {
        if (s.equals("+") || s.equals("-")) {
            return base + 1;
        } else if (s.equals("*") || s.equals("/")) {
            return base + 2;
        }
        
        return Integer.MAX_VALUE;
    }
}
