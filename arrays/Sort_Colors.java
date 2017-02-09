/*

Sort Colors



Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
Notice

You are not suppose to use the library's sort function for this problem.
You should do it in-place (sort numbers in the original array).



 Example

Given [1, 0, 1, 2], sort it in-place to [0, 1, 1, 2].


 Challenge

A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.

Could you come up with an one-pass algorithm using only constant space?

解：
two pointers

使用一个left指针指向0保存的位置，right指针指向2保存的位置。
iter指针从右向左遍历数组，遇到0就存到left位置，并右移left，遇到2就存到right位置，并左移right。若iter遇到1，则自己向左移动。直到iter和left相遇。
两个要点：
一，iter不应当与right指向同一个位置，否则就会原地移动。如果iter与right在一个值为2的位置相遇，那么先将iter左移一次。
二，结束条件不是left < iter，因为left指向的是存储下一个0的位置，也就是说left指向的位置不一定为0，所以还需要处理一次。

*/


class Solution {
    /**
     * @param nums: A list of integer which is 0, 1 or 2 
     * @return: nothing
     */
    public void sortColors(int[] nums) {
        int left, right, iter;
        left = 0;
        right = nums.length - 1;
        iter = nums.length - 2;
        
        
        while (left <= iter) {
            if (nums[iter] == 0) {
                int tmp = nums[left];
                nums[left] = nums[iter];
                nums[iter] = tmp;
                left++;
            } else if (nums[iter] == 2) {
                if (iter == right) {
                    iter--;
                    continue;
                }
                int tmp = nums[right];
                nums[right] = nums[iter];
                nums[iter] = tmp;
                right--;
            } else {
                iter--;
            }
        }
    }
}
