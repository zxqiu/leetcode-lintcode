/*

Sort Colors II


Given an array of n objects with k different colors (numbered from 1 to k), sort them so that objects of the same color are adjacent, with the colors in the order 1, 2, ... k.
Notice

You are not suppose to use the library's sort function for this problem.


 Example

Given colors=[3, 2, 2, 1, 4], k=4, your code should sort colors in-place to [1, 2, 2, 3, 4].

 Challenge

A rather straight forward solution is a two-pass algorithm using counting sort. That will cost O(k) extra memory. Can you do it without using extra memory?


解：
这道题可以理解为，要求使用O(1)memory来左counting sort。
为何这样说？
首先counting sort时间复杂度为O(n)，跟它时间复杂度一样的还有bucket sort和radix sort。根据这道题的题意，数字大小有限，且与区间无关，故counting sort最合适。
其他排序算法时间复杂度至少在O(nlog(n))。一道可以用O(n)做的题不可能退步选择O(nlog(n))算法，故直接排除其他排序算法。
那么只剩下优化counting sort空间复杂度的选项。

要降低空间复杂度，最基本的思路是空间重用。
这里需要做的就是把counting sort保存的每个数字的计数信息存在原本的数组中，这样就不需要再申请额外的空间。

基本思路是，需要用到的空间是colors的0到k-1，每个对应一个数字的计数器。计数完毕后从colors最后一个位置开始恢复序列。不用担心恢复出的数字会覆盖计数器，因为如果恢复到的位置是某一个计数器的位置，那这一定是该计数器可以生成的最后一个数字，即该计数器已经使用完毕。

首先，从colors最左边开始遍历数组，遇到一个大于0的数m，就应当在m-1位置计数。
这时有两个问题，一个是需要区别原来的数字和计数的数字。由于任何普通数字都比0大，计数器可以从0开始递减来解决这个问题。没出现过就为0，出现一次就为-1，以此类推。
二是如果一个计数器原本有普通数字，计数时会将这个数字覆盖掉。解决这个问题需要先将原数字保存起来，当前数字计数过后对保存的数字进行计数，直到遇到一个原来数字已经被处理过的计数器。

第二，处理完m之后，要将m的位置填充为0，以表示这个数字已经处理过，这个位置现在是计数器。
最后，从最后一个位置开始恢复排序后的数组即可。要注意，并不是1到k每个数字都有，所以要跳过所有等于0的计数器。也要跳过大于0的计数器，因为可能会遇到某一个数字的最后一个覆盖掉计数器的情况，这时这个计数器已经处理完，应当开始处理下一个计数器。

*/



class Solution {
    /**
     * @param colors: A list of integer
     * @param k: An integer
     * @return: nothing
     */
    public void sortColors2(int[] colors, int k) {
        for (int i = 0; i < colors.length; i++) {
            if (colors[i] <= 0) {
                continue;
            }
            
            int cur = colors[i];
            colors[i] = 0;
            while (cur > 0) {
                int tmp = colors[cur - 1];
                if (tmp > 0) {
                    colors[cur - 1] = -1;
                } else {
                    colors[cur - 1]--;
                }
                cur = tmp;
            }
        }
        
        for (int i = colors.length - 1; i >= 0; i--) {
            while (colors[k - 1] >= 0) {
                k--;
            }
            
            colors[k - 1]++;
            colors[i] = k;
        }
    }
}
