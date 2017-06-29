/*

Largest Number

Given a list of non negative integers, arrange them such that they form the largest number.

 Notice

The result may be very large, so you need to return a string instead of an integer.

Example
Given [1, 20, 23, 4, 8], the largest formed number is 8423201.

Challenge 
Do it in O(nlogn) time complexity.


解：
首先对输入序列进行某种排序，保证高位的数字尽可能的大，然后把排序好的数组依次加入结果字符串。

对于任意两个数字，谁在高位可以保证两个组合出的数字最大就应当排在前面。
于是比较这两个数字的两种不同组合的大小即可决定谁在前谁在后。

生成结果字符串之后应当去掉最高位的0。

*/


public class Solution {
    /**
     *@param num: A list of non negative integers
     *@return: A string
     */
    public String largestNumber(int[] num) {
        StringBuilder ret = new StringBuilder("0");
        String[] nums = new String[num.length];
        
        for (int i = 0; i < num.length; i++) {
            nums[i] = String.valueOf(num[i]);
        }
        
        Arrays.sort(nums, comp);
        
        for (String s : nums) {
            ret.append(s);
        }
        
        while (ret.length() > 1 && ret.charAt(0) == '0') {
            ret.deleteCharAt(0);
        }
        
        return ret.toString();
    }
    
    private Comparator<String> comp = new Comparator<String>() {
        public int compare(String a, String b) {
            return (b + a).compareTo(a + b);
        }   
    };
}
