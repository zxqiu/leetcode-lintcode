/*

Majority Number III



Given an array of integers and a number k, the majority number is the number that occurs more than 1/k of the size of the array.

Find it.
Notice

There is only one majority number in the array.

Example

Given [3,1,2,3,2,3,3,4,4,4] and k=3, return 3.
Challenge

O(n) time and O(k) extra space


解：
O(n) time和O(k) space的要求，可以使用一个HashMap来对每个数字进行统计来满足。
最后只要返回HashMap中统计数量最多的那个数字就可以了。

*/
public class Solution {
    /**
     * @param nums: A list of integers
     * @param k: As described
     * @return: The majority number
     */
    public int majorityNumber(ArrayList<Integer> nums, int k) {
        if (nums == null | nums.size() == 0) {
            return 0;
        }
        
        /* use map to count every number */
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.size(); i++) {
            int num = nums.get(i);
            if (!map.containsKey(num)) {
                map.put(num, 0);
            }
            map.put(num, map.get(num) + 1);
        }
        
        int maxCnt, max = 0;
        maxCnt = Integer.MIN_VALUE;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (maxCnt < entry.getValue()) {
                maxCnt = entry.getValue();
                max = entry.getKey();
            }
        }
        
        return max;
    }
}
