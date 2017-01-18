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
