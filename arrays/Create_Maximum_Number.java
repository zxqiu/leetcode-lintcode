/*

Create Maximum Number



Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length k <= m + n from digits of the two. The relative order of the digits from the same array must be preserved. Return an array of the k digits. You should try to optimize your time and space complexity.


Example

Given nums1 = [3, 4, 6, 5], nums2 = [9, 1, 2, 5, 8, 3], k = 5
return [9, 8, 6, 5, 3]

Given nums1 = [6, 7], nums2 = [6, 0, 4], k = 5
return [6, 7, 6, 0, 4]

Given nums1 = [3, 9], nums2 = [8, 9], k = 3
return [9, 8, 9]



解：
这道题我觉得非常难。

基本思路是从nums1中找出长度为len1的最大子序列，从nums2中找出长度为len2的最大子序列，并且len1+len2=k。
然后将两个子序列合并成长度为k的最大子序列。

所以这道题涉及三个问题：
1.在数组中找出长度为len的最大子序列。
2.比较两个数组。
3.合并两个数组成新的最大子序列。


问题1：
Stack
从头遍历数组，将值依次放入栈中。
1.如果在位置i，满足：
  1）栈不为空；
  2）栈中元素数量和未访问的元素数量之和大于等于len；
  3）并且栈顶元素小于nums[i]。
  出栈栈顶直到不满足上述条件。
2.如果栈中元素数量小于k，入栈nums[i]。

以上过程可以保证访问到i时，栈中元素一定是从0到i的最大子序列。
具体可以带入递增，递减等情况进行分析理解。
当然，也可以直接用返回数组代替栈来进行操作。

问题2：
1.从两个数组各自的指定位置开始比较并返回大小情况。
2.如果第二个数组在结束后仍有未访问项，那么之前的数字都与第一个数组相等，这样的情况下应当为第二个数组大于第一个数组。
  这是为了配合解决问题3。

问题3：
1.比较两个数组的当前值，取其中较大的放进结果数组。
2.如果两个数组的当前值相等，那么应当继续向后比较，直到找到一个较大的为止。这里可以利用问题2的方法。
3.如果直到遍历完某一个数组，另一个数组仍有未访问项，那么应当先选择较长的那个数组。
  这样做的原因是如果先选择长的，那么长的这个数组假设选取了与另一个数组相等的全部元素之后，将使用剩余元素与另一个数组继续比较。
  这样可以保证结果一定最大。

解决完上述问题之后，只需要枚举所有可能的从nums1中选取的子序列大小i，并且从nums2中选取长度k-i的子序列与之合并。
再从所有合并得到的序列中选取最大的即可。

如果nums2的长度小于k，那么nums1中值至少需要选取k-nums2.length个元素。否则nums1中可以选择最少0个元素。
如果nums1的长度大于k，那么nums1中最多只需要选择k个元素。否则nums1中最多选择nums1.length个元素。
跟据以上原则，i满足:
max(0, k - nums2.length) <= i <= min(k, nums1.length)

*/

public class Solution {
    /*
     * @param nums1: an integer array of length m with digits 0-9
     * @param nums2: an integer array of length n with digits 0-9
     * @param k: an integer and k <= m + n
     * @return: an integer array
     */
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] max = new int[k];
        
        for (int i = Math.max(0, k - nums2.length); i <= Math.min(k, nums1.length); i++) {
            int[] cur = merge(getMaxSubSeq(nums1, i), getMaxSubSeq(nums2, k - i));
            if (greater(cur, 0, max, 0)) {
                max = cur;
            }
        }
        
        return max;
    }
    
    private boolean greater(int[] a, int idxa, int[] b, int idxb) {
        while (idxa < a.length && idxb < b.length) {
            if (a[idxa] > b[idxb]) {
                return true;
            } else if (a[idxa] < b[idxb]) {
                return false;
            }
            idxa++;
            idxb++;
        }
        
        return idxb == b.length;
    }
    
    private int[] merge(int[] n1, int[] n2) {
        int[] ret = new int[n1.length + n2.length];
        int idx1 = 0;
        int idx2 = 0;
        int idx = 0;
        
        while (idx1 < n1.length || idx2 < n2.length) {
            if (greater(n1, idx1, n2, idx2)) {
                ret[idx++] = n1[idx1++];
            } else {
                ret[idx++] = n2[idx2++];
            }
        }
        
        return ret;
    }
    
    private int[] getMaxSubSeq(int[] nums, int k) {
        Stack<Integer> st = new Stack<Integer>();
        int[] ret = new int[k];
        int idx = k - 1;
        
        if (nums.length < k) {
            return ret;
        }
        
        for (int i = 0; i < nums.length; i++) {
            while (!st.isEmpty() && st.size() + nums.length - i > k && nums[i] > st.peek()) {
                st.pop();
            }
            
            if (st.size() < k) {
                st.push(nums[i]);
            }
        }
        
        while (!st.isEmpty()) {
            ret[idx--] = st.pop();
        }
        
        return ret;
    }
}
