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
方法一：

要保存水，必须先有一个递减的高度序列，然后再有一个递增的高度序列，组成一个凹形。
用一个stack保存heights中值的index。
每当遇到一个高度低于上一个高度时，将该高度入栈。
否则，将之间所有低于或者等于该高度的值依次出栈，并且计算可以保存的水量。

由于至少需要三个高度才可以保存水，故用floor来保存这三个之中最低的那个值。
由于stack中的值递减，floor应当为本次计算时stack中的上一个值。
而本次能保存的水的高度为floor到这三个高度中左右两个高度里面低的那个的差。
而宽度为左右两个高度的间距减一。

此方法时间复杂度O(n)，空间复杂度O(n)

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



/*

方法二：
可以用两个指针left和right，分别指向heights头和尾。
1.如果left指向值小于right，说明left右边任何小于left的位置的水都可以保存下来。
  于是可以从left向右计算并累加，直到某一个值大于等于left，就将left移动到该位置。
2.如果right指向值小于left，说明right左边任何小于right的位置的水都可以保存下来。
  和left的情况一样，从右向左计算，直到某一个值大于等于right。
  
当left和right相遇时计算完成。

时间复杂度O(n)，空间复杂度O(1)。

*/

public class Solution {
    /**
     * @param heights: an array of integers
     * @return: a integer
     */
    public int trapRainWater(int[] heights) {
        int left, right, ret;
        
        if (heights == null || heights.length == 0) {
            return 0;
        }
        
        left = 0;
        right = heights.length - 1;
        ret = 0;
        
        while (left < right - 1) {
            if (heights[left] < heights[right]) {
                int tmp = left + 1;
                while (tmp <= right && heights[tmp] < heights[left]) {
                    ret += heights[left] - heights[tmp];
                    tmp++;
                }
                left = tmp;
            } else {
                int tmp = right - 1;
                while (tmp >= left && heights[tmp] < heights[right]) {
                    ret += heights[right] - heights[tmp];
                    tmp--;
                }
                right = tmp;
            }
        }
        
        return ret;
    }
}
