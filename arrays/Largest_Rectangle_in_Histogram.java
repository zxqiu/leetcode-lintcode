/*
Largest Rectangle in Histogram

Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.

解：
Stack

首先需要理解，最大矩形一定由某一个高度和其左右比它高的高度组成。
我们每次如果发现某个高度比栈顶的高度高，那么将该高度直接入队。
如果发现该高度小于等于栈顶高度，那么说明当前栈内一定是一个递增序列。我们只需要依次计算这个递增序列中比当前高度高的值组成的最大的矩形，并将对应值出栈。然后再将当前高度入栈。
当一个高度入栈时，栈不一定为空。但由于所有比当前值更高的已经全部出栈，所以栈内依然是一个递增序列。
*/



public class Solution {
    /**
     * @param height: A list of integer
     * @return: The area of largest rectangle in the histogram
     */
    public int largestRectangleArea(int[] height) {
        Stack<Integer> stack = new Stack<Integer>();
        int max = 0;
        
        for (int i = 0; i <= height.length; i++) {
            int curt = (i == height.length) ? 0 : height[i];
            while (!stack.isEmpty() && curt <= height[stack.peek()]) {
                int h = height[stack.pop()];
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                max = Math.max(max, h * w);
            }
            
            stack.push(i);
        }
        
        return max;
    }
}