/*
从第二个数字开始，把新的（不重复）的数字存到store指向的位置。
store从第二个数字开始，每次存入新的数字便增加1。
重复的数字会不断被覆盖，最后数组最前部分就是不重复的数字。
*/
public class Solution {
    /**
     ** @param A: a array of integers
     ** @return : return an integer
     **/
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int store = 1;
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[store++] = nums[i];
            }
        }
        
        return store;
    }
}
