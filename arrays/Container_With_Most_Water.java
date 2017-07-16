/*

Container With Most Water

Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

 Notice

You may not slant the container.

Example
Given [1,3,2], the max area of the container is 2.


解：
双指针。
left从左向右，right从右向左。
heights[left]>heights[right]时，应当right左移。否则如果left右移，下一次计算出的盛水量一定小于本次。
heights[left]<heights[right]时，应当left右移，原因与上面一样。
每次循环比较当前的盛水量与之前的计算结果，取其中大的保存。


*/

public class Solution {
    /**
     * @param heights: an array of integers
     * @return: an integer
     */
    public int maxArea(int[] heights) {
        int left = 0;
        int right = heights.length - 1;
        int ret = 0;
        
        while (left < right) {
            ret = Math.max(ret, (right - left) * Math.min(heights[left], heights[right]));
            
            if (heights[right] > heights[left]) {
                left++;
            } else {
                right--;
            }
        }
        
        return ret;
    }
}
