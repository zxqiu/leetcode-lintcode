/*

Sliding Window Maximum


Given an array of n integer with duplicate number, and a moving window(size k), move the window at each iteration from the start of the array, find the maximum number inside the window at each moving.

Example
For array [1, 2, 7, 7, 8], moving window size k = 3. return [7, 7, 8]

At first the window is at the start of the array like this

[|1, 2, 7| ,7, 8] , return the maximum 7;

then the window move one step forward.

[1, |2, 7 ,7|, 8], return the maximum 7;

then the window move one step forward again.

[1, 2, |7, 7, 8|], return the maximum 8;

Challenge 
o(n) time and O(k) memory



解：
用一个双端队列来保存最大值序列的index。
如果在nums[i]遇到当前窗口i-k+1到i中的最大值，那么之前保存的任何最大值都没有用处了。
如果Nums[i+1]大于nums[i]，那么当窗口运行到i+1时，之前保存的最大值nums[i]也没有用处了，可以从队列中删除。

故双端队列中只需要保存有用的值，没用的值可以删掉。

1.如果队列头为i-k，说明该值已经在窗口之外，直接删掉。
2.从后向前删除队列中所有指向值小于nums[i]的index。
3.把i入队。
4.如果已经有至少k个数字被处理过，即i>=k-1，将队列头所指向的值(此时应当为队列中的最大值)加入结果队列。

*/

public class Solution {
    /**
     * @param nums: A list of integers.
     * @return: The maximum number inside the window at each moving.
     */
    public ArrayList<Integer> maxSlidingWindow(int[] nums, int k) {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        Deque<Integer> q = new LinkedList<Integer>();
        
        for (int i = 0; i < nums.length; i++) {
            if (!q.isEmpty() && q.peek() == i - k) {
                q.removeFirst();
            }
            
            while (!q.isEmpty() && nums[q.peekLast()] < nums[i]) {
                q.removeLast();
            }
            
            q.offer(i);
            
            if (i >= k - 1) {
                ret.add(nums[q.peek()]);
            }
        }
        
        return ret;
    }
}
