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
