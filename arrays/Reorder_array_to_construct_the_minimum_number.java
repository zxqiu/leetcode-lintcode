/*

Reorder array to construct the minimum number


Construct minimum number by reordering a given non-negative integer array. Arrange them such that they form the minimum number.

 Notice

The result may be very large, so you need to return a string instead of an integer.

Example
Given [3, 32, 321], there are 6 possible numbers can be constructed by reordering the array:

3+32+321=332321
3+321+32=332132
32+3+321=323321
32+321+3=323213
321+3+32=321332
321+32+3=321323
So after reordering, the minimum number is 321323, and return it.

Challenge 
Do it in O(nlogn) time complexity.


解：
对于任意两个数字a和b，如果ab大于ba，那么a应当放在b前面，否则b应当放在a前面。
无论a和b之前是否有别的数字，这个条件都成立。

所以按照此规则对数组进行排序，然后组成一个字符串。

*/

public class Solution {
    /**
     * @param nums n non-negative integer array
     * @return a string
     */
    public String minNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }
        
        String ret = "";
        Integer[] in = new Integer[nums.length];
        
        for (int i = 0; i < nums.length; i++) {
            in[i] = nums[i];
        }
        
        Arrays.sort(in, comparator);
        
        for (int i : in) {
            if (ret.length() == 0 && i == 0) {
                continue;
            }
            ret += String.valueOf(i);
        }
        
        return ret.length() == 0 ? "0" : ret;
    }
    
    Comparator<Integer> comparator = new Comparator<Integer>() {
        public int compare(Integer a, Integer b) {
            String sA = String.valueOf(a) + String.valueOf(b);
            String sB = String.valueOf(b) + String.valueOf(a);
            
            return sA.compareTo(sB);
        }
    };
}
