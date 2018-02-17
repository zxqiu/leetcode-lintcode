/*

Subset With Target


Give an array and a target. We need to find the number of subsets which meet the following conditions:
The sum of the minimum value and the maximum value in the subset is less than the target.

Notice
The length of the given array does not exceed 50.
target <= 100000.

Example
Give array = [1,5,2,4,3], target = 4, return 2.

Explanation:
Only subset [1],[1,2] satisfy the condition, so the answer is 2.
Give array = [1,5,2,4,3],target = 5, return 5.

Explanation:
Only subset [1],[2],[1,3],[1,2],[1,2,3] satisfy the condition, so the answer is 5.


解：
这道题如果使用DFS，BFS都会超时。
使用two pointers加组合数学计算(n choose m)可以通过。

首先对数组排序。左(l)右(r)两个指针分别指向数组两端。
在包括这两个指针在内的范围内取包含nums[l]的任意非空子集，其最小值一定是nums[l]，最大值一定不会超过nums[r]。

由此：
  1.如果nums[l] + nums[r] < target，则在此范围内，包含nums[l]的组合数量就是该范围内满足条件的子集数量。
    计算完成后l指针向右移动。
  2.如果nums[l] + nums[r] >= target，则在此范围内，包含nums[r]的子集一定不满足条件。
    故r指针向左移动，排除掉可能包含nums[r]的子集。


组合计算公式(n choose m)：
C(n,m) = A(n,m) / m!
       = n! / (m! * (n - m)!)

直接用循环套用公式会导致整数溢出，就算使用long也不能解决问题。
观察公式发现，无论从m为多少，分子都不变，而且分母的变化很有规律:
n*(n-1)*(n-2)* ... *2*1
-----------------------------------------------------------
(m*(m-1)*(m-2)* ... *2*1)*((n-m)*(n-m-1)*(n-m-2)* ... *2*1)

如果m增加1，则有：
n*(n-1)*(n-2)* ... *2*1
-----------------------------------------------------------
((m+1)*m*(m-1)*(m-2)* ... *2*1)*((n-m-1)*(n-m-2)* ... *2*1)

=

n*(n-1)*(n-2)* ... *2*1                                           n - m
-----------------------------------------------------------   *   ------
(m*(m-1)*(m-2)* ... *2*1)*((n-m)*(n-m-1)*(n-m-2)* ... *2*1)       m + 1

= (C(n,m) * (n - m)) / (m + 1)


上述公式需要O(n)即可实现。
再加上排序和two pointers移动的时间，综合复杂度为：
O(nlog(n) + n^2)


*/


public class Solution {
    /**
     * @param nums: the array
     * @param target: the target
     * @return: the number of subsets which meet the following conditions
     */
    public long subsetWithTarget(int[] nums, int target) {
        long cnt = 0;
        int l = 0;
        int r = nums.length - 1;
        
        Arrays.sort(nums);
        
        for (int i : nums) {
            cnt += (i * 2 < target) ? 1 : 0;
        }
        
        while (l < r) {
            if (nums[l] + nums[r] >= target) {
                r--;
            } else {
                long n = r - l;
                cnt += n;
                
                for (int i = 2; i <= r - l; i++) {
                    n = n * (r - l - i + 1) / i;
                    cnt += n;
                }
                
                l++;
            }
        }
        
        return cnt;
    }
}
