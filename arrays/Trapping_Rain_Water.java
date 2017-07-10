/*

Trapping Rain Water

Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

Trapping Rain Water

Example
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.

Challenge 
O(n) time and O(1) memory

O(n) time and O(n) memory is also acceptable.


解：
要保存水，必须先有一个递减的高度序列，然后再有一个递增的高度序列，组成一个凹形。
用一个stack保存heights中值的index。
每当遇到一个高度低于上一个高度时，将该高度入栈。
否则，将之间所有低于或者等于该高度的值依次出栈，并且计算可以保存的水量。

由于至少需要三个高度才可以保存水，故用floor来保存这三个之中最低的那个值。
由于stack中的值递减，floor应当为本次计算时stack中的上一个值。
而本次能保存的水的高度为floor到这三个高度中左右两个高度里面低的那个的差。
而宽度为左右两个高度的间距减一。

*/


public class Solution {
    /**
     * @param heights: an array of integers
     * @return: a integer
     */
    public int trapRainWater(int[] heights) {
        Stack<Integer> st = new Stack<Integer>();
        int ret = 0;
        
        if (heights == null || heights.length == 0) {
            return 0;
        }
        
        for (int i = 0; i < heights.length; i++) {
            if (!st.isEmpty() && heights[i] >= heights[i - 1]) {
                int floor = heights[st.pop()];
                while (!st.isEmpty()) {
                    int width = i - st.peek() - 1;
                    int height = Math.min(heights[st.peek()] - floor, heights[i] - floor);
                    ret += width * height;
                    
                    if (heights[st.peek()] > heights[i]) {
                        break;
                    }
                    floor = heights[st.pop()];
                }
            }
            
            st.push(i);
        }
        
        return ret;
    }
}
